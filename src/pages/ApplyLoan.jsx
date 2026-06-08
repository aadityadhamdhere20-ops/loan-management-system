import { useState } from "react";
import API from "../services/api";

function ApplyLoan() {
  const [form, setForm] = useState({
    userId: localStorage.getItem("userId") || "",
    loanType: "PERSONAL",
    requestedAmount: "",
    tenureMonths: "",
  });

  const [msg, setMsg] = useState("");

  const change = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const submitLoan = async (e) => {
    e.preventDefault();

    try {
      const data = {
        userId: Number(form.userId),
        loanType: form.loanType,
        requestedAmount: Number(form.requestedAmount),
        tenureMonths: Number(form.tenureMonths),
      };

      const res = await API.post("/loan/apply", data);
      setMsg(res.data.message + " | Application ID: " + res.data.data);
    } catch (error) {
      setMsg(error.response?.data?.message || "Loan application failed");
    }
  };

  return (
    <div className="card-box">
      <h3>Apply Loan</h3>

      <form onSubmit={submitLoan}>
        <input className="form-control" name="userId" value={form.userId} onChange={change} placeholder="User ID" />

        <select className="form-control" name="loanType" onChange={change}>
          <option value="PERSONAL">Personal Loan</option>
          <option value="HOME">Home Loan</option>
          <option value="VEHICLE">Vehicle Loan</option>
          <option value="EDUCATION">Education Loan</option>
          <option value="BUSINESS">Business Loan</option>
        </select>

        <input className="form-control" name="requestedAmount" type="number" placeholder="Requested Amount" onChange={change} />
        <input className="form-control" name="tenureMonths" type="number" placeholder="Tenure Months" onChange={change} />

        <button className="btn btn-primary">Apply Loan</button>
      </form>

      <p className="mt-3">{msg}</p>
    </div>
  );
}

export default ApplyLoan;