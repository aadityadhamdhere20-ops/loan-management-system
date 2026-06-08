import { useState } from "react";
import API from "../services/api";

function LoanDetails() {
  const [id, setId] = useState("");
  const [result, setResult] = useState("");

  const getLoan = async () => {
    try {
      const res = await API.get("/loan/" + id);
      setResult(JSON.stringify(res.data, null, 2));
    } catch (error) {
      setResult(error.response?.data?.message || "Loan not found");
    }
  };

  return (
    <div className="card-box">
      <h3>Loan Details</h3>

      <input className="form-control" placeholder="Loan Application ID" onChange={(e) => setId(e.target.value)} />

      <button className="btn btn-primary" onClick={getLoan}>Get Loan</button>

      <pre className="mt-3">{result}</pre>
    </div>
  );
}

export default LoanDetails;