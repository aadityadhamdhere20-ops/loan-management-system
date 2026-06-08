import { useState } from "react";
import API from "../services/api";

function PaymentHistory() {
  const [loanAccountId, setLoanAccountId] = useState("");
  const [result, setResult] = useState("");

  const getHistory = async () => {
    try {
      const res = await API.get("/repayment/" + loanAccountId);
      setResult(JSON.stringify(res.data, null, 2));
    } catch (error) {
      setResult(error.response?.data?.message || "Payment history not found");
    }
  };

  return (
    <div className="card-box">
      <h3>Payment History</h3>

      <input className="form-control" placeholder="Loan Account ID" onChange={(e) => setLoanAccountId(e.target.value)} />

      <button className="btn btn-primary" onClick={getHistory}>View History</button>

      <pre className="mt-3">{result}</pre>
    </div>
  );
}

export default PaymentHistory;