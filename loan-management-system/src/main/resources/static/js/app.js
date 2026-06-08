const state = {
  token: localStorage.getItem("token"),
  role: localStorage.getItem("role"),
  userId: localStorage.getItem("userId")
};

const $ = id => document.getElementById(id);
const qsa = sel => [...document.querySelectorAll(sel)];

function showAlert(msg, ok = true) {
  const box = $("alert");
  box.textContent = msg;
  box.className = "alert " + (ok ? "ok" : "err");
  setTimeout(() => box.classList.add("hidden"), 4000);
}

function headers(json = true) {
  const h = {};
  if (json) h["Content-Type"] = "application/json";
  if (state.token) h["Authorization"] = "Bearer " + state.token;
  return h;
}

async function request(url, options = {}) {
  const res = await fetch(url, options);
  const text = await res.text();
  let data;
  try { data = text ? JSON.parse(text) : {}; } catch { data = text; }
  if (!res.ok) throw new Error(data.message || text || "Request failed");
  return data;
}

function formDataToJson(form) {
  return Object.fromEntries(new FormData(form).entries());
}

function switchView(id) {
  qsa(".view").forEach(v => v.classList.toggle("active", v.id === id));
  qsa(".nav").forEach(n => n.classList.toggle("active", n.dataset.view === id));
}

function refreshNav() {
  const logged = !!state.token;
  $("userInfo").textContent = logged ? `User ID: ${state.userId} | Role: ${state.role}` : "Not logged in";
  $("logoutBtn").classList.toggle("hidden", !logged);
  qsa(".auth-only").forEach(e => e.classList.toggle("hidden", !logged));
  qsa(".admin-only").forEach(e => e.classList.toggle("hidden", !(logged && state.role === "ADMIN")));
}

qsa(".nav").forEach(btn => {
  btn.addEventListener("click", () => switchView(btn.dataset.view));
});

$("logoutBtn").addEventListener("click", () => {
  localStorage.clear();
  state.token = null;
  state.role = null;
  state.userId = null;
  refreshNav();
  switchView("loginView");
});

$("registerForm").addEventListener("submit", async e => {
  e.preventDefault();

  const body = formDataToJson(e.target);
  body.age = Number(body.age);
  body.monthlyIncome = Number(body.monthlyIncome);

  try {
    const data = await request("/api/auth/register", {
      method: "POST",
      headers: headers(),
      body: JSON.stringify(body)
    });

    showAlert(data.message || "Registered successfully");
    e.target.reset();
    switchView("loginView");
  } catch (err) {
    showAlert(err.message, false);
  }
});

$("loginForm").addEventListener("submit", async e => {
  e.preventDefault();

  try {
    const data = await request("/api/auth/login", {
      method: "POST",
      headers: headers(),
      body: JSON.stringify(formDataToJson(e.target))
    });

    state.token = data.token;
    state.role = data.role;
    state.userId = data.userId;

    localStorage.setItem("token", data.token);
    localStorage.setItem("role", data.role);
    localStorage.setItem("userId", data.userId);

    refreshNav();
    showAlert("Login successful");

    if (data.role === "ADMIN") {
      switchView("adminView");
      loadAdmin();
    } else {
      switchView("applyView");
      loadMyLoans();
    }
  } catch (err) {
    showAlert(err.message, false);
  }
});

$("loanForm").addEventListener("submit", async e => {
  e.preventDefault();

  const body = formDataToJson(e.target);
  body.userId = Number(state.userId);
  body.requestedAmount = Number(body.requestedAmount);
  body.tenureMonths = Number(body.tenureMonths);

  try {
    const data = await request("/api/loan/apply", {
      method: "POST",
      headers: headers(),
      body: JSON.stringify(body)
    });

    showAlert(data.message || "Loan application submitted");
    e.target.reset();
    loadMyLoans();
    switchView("myLoansView");
  } catch (err) {
    showAlert(err.message, false);
  }
});

$("refreshLoans").addEventListener("click", loadMyLoans);

async function loadMyLoans() {
  if (!state.userId) return;

  const box = $("loanList");
  box.innerHTML = "<div class='card'>Loading...</div>";

  try {
    const data = await request(`/api/loan/user/${state.userId}`, {
      headers: headers(false)
    });

    const loans = data.data || [];

    if (loans.length === 0) {
      box.innerHTML = "<div class='card'>No applications found.</div>";
      return;
    }

    box.innerHTML = loans.map(l => `
      <div class="loan-card">
        <h3>${l.loanType} Loan</h3>
        <p><b>Application ID:</b> ${l.id}</p>
        <p><b>Amount:</b> ₹${l.requestedAmount}</p>
        <p><b>Tenure:</b> ${l.tenureMonths} months</p>
        <p><b>Status:</b> ${l.applicationStatus}</p>
        <p><b>EMI:</b> ₹${l.calculatedEmi || "-"}</p>
      </div>
    `).join("");
  } catch (err) {
    box.innerHTML = `<div class='card'>${err.message}</div>`;
  }
}

$("emiSearchForm").addEventListener("submit", async e => {
  e.preventDefault();

  const loanAccountId = new FormData(e.target).get("loanAccountId");
  const box = $("emiList");

  box.innerHTML = "Loading EMI schedule...";

  try {
    const data = await request(`/api/emi/${loanAccountId}`, {
      headers: headers(false)
    });

    const list = data.data || [];

    if (list.length === 0) {
      box.innerHTML = "No EMI records found.";
      return;
    }

    box.innerHTML = `
      <table class="table">
        <thead>
          <tr>
            <th>EMI ID</th>
            <th>No</th>
            <th>Due Date</th>
            <th>Amount</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          ${list.map(e => `
            <tr>
              <td>${e.id}</td>
              <td>${e.emiNumber}</td>
              <td>${e.dueDate}</td>
              <td>₹${e.totalPayableAmount || e.emiAmount}</td>
              <td>${e.emiStatus}</td>
            </tr>
          `).join("")}
        </tbody>
      </table>
    `;
  } catch (err) {
    box.innerHTML = err.message;
  }
});

$("refreshAdmin").addEventListener("click", loadAdmin);

async function loadAdmin() {
  try {
    const dash = await request("/api/dashboard/admin", {
      headers: headers(false)
    });

    $("dashboardCards").innerHTML = `
      <div class="stat">Total Users <h2>${dash.totalUsers}</h2></div>
      <div class="stat">Applications <h2>${dash.totalApplications}</h2></div>
      <div class="stat">Pending <h2>${dash.pendingApprovals}</h2></div>
      <div class="stat">Disbursed <h2>₹${dash.totalDisbursedAmount || 0}</h2></div>
    `;

    const apps = await request("/api/loan/all", {
      headers: headers(false)
    });

    const loans = apps.data || [];

    $("adminLoans").innerHTML = `
      <table class="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Loan Type</th>
            <th>Amount</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          ${loans.map(l => `
            <tr>
              <td>${l.id}</td>
              <td>${l.loanType}</td>
              <td>₹${l.requestedAmount}</td>
              <td>${l.applicationStatus}</td>
              <td>
                <button class="btn success" onclick="approveLoan(${l.id}, ${l.requestedAmount})">Approve</button>
                <button class="btn danger" onclick="rejectLoan(${l.id})">Reject</button>
              </td>
            </tr>
          `).join("")}
        </tbody>
      </table>
    `;
  } catch (err) {
    showAlert(err.message, false);
  }
}

window.approveLoan = async function(id, amount) {
  const approvedAmount = prompt("Approved Amount:", amount);
  if (!approvedAmount) return;

  const interestRate = prompt("Interest Rate:", "12");
  if (!interestRate) return;

  try {
    const data = await request(`/api/admin/loan/approve/${id}`, {
      method: "POST",
      headers: headers(),
      body: JSON.stringify({
        approvedAmount: Number(approvedAmount),
        interestRate: Number(interestRate),
        remarks: "Approved"
      })
    });

    showAlert(data.message || "Approved");
    loadAdmin();
  } catch (err) {
    showAlert(err.message, false);
  }
};

window.rejectLoan = async function(id) {
  const reason = prompt("Reason:", "Rejected by admin");
  if (!reason) return;

  try {
    const data = await request(`/api/admin/loan/reject/${id}?reason=${encodeURIComponent(reason)}`, {
      method: "POST",
      headers: headers(false)
    });

    showAlert(data.message || "Rejected");
    loadAdmin();
  } catch (err) {
    showAlert(err.message, false);
  }
};

refreshNav();

if (state.token) {
  if (state.role === "ADMIN") {
    switchView("adminView");
    loadAdmin();
  } else {
    switchView("applyView");
  }
}