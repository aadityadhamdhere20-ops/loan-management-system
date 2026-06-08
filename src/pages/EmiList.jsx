import { useState } from "react";
import API from "../services/api";

function EmiList() {
  const [loanAccountId, setLoanAccountId] = useState("");
  const [result, setResult] = useState("");

  const getEmi = async () => {
    try {
      const res = await API.get("/emi/" + loanAccountId);
      setResult(JSON.stringify(res.data, null, 2));
    } catch (error) {
      setResult(error.response?.data?.message || "EMI not found");
    }
  };

  return (
    <div className="card-box">
      <h3>EMI List</h3>

      <input className="form-control" placeholder="Loan Account ID" onChange={(e) => setLoanAccountId(e.target.value)} />

      <button className="btn btn-primary" onClick={getEmi}>Get EMI List</button>

      <pre className="mt-3">{result}</pre>
    </div>
  );
}

export default EmiList;