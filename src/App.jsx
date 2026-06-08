import { BrowserRouter, Routes, Route, Link, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import ApplyLoan from "./pages/ApplyLoan";
import LoanDetails from "./pages/LoanDetails";
import DocumentUpload from "./pages/DocumentUpload";
import EmiList from "./pages/EmiList";
import Payment from "./pages/Payment";
import PaymentHistory from "./pages/PaymentHistory";
import AdminDashboard from "./pages/AdminDashboard";
import AdminActions from "./pages/AdminActions";

function App() {
  const logout = () => {
    localStorage.clear();
    window.location.href = "/login";
  };

  return (
    <BrowserRouter>
      <div className="app">
        <aside className="sidebar">
          <h3>Loan System</h3>

          <Link to="/login">Login</Link>
          <Link to="/register">Register</Link>
          <Link to="/apply-loan">Apply Loan</Link>
          <Link to="/loan-details">Loan Details</Link>
          <Link to="/documents">Documents</Link>
          <Link to="/emi">EMI List</Link>
          <Link to="/payment">Pay EMI</Link>
          <Link to="/history">Payment History</Link>
          <Link to="/admin-dashboard">Admin Dashboard</Link>
          <Link to="/admin-actions">Admin Actions</Link>

          <button className="btn btn-danger mt-3" onClick={logout}>
            Logout
          </button>
        </aside>

        <main className="content">
          <Routes>
            <Route path="/" element={<Navigate to="/login" />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/apply-loan" element={<ApplyLoan />} />
            <Route path="/loan-details" element={<LoanDetails />} />
            <Route path="/documents" element={<DocumentUpload />} />
            <Route path="/emi" element={<EmiList />} />
            <Route path="/payment" element={<Payment />} />
            <Route path="/history" element={<PaymentHistory />} />
            <Route path="/admin-dashboard" element={<AdminDashboard />} />
            <Route path="/admin-actions" element={<AdminActions />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

export default App;