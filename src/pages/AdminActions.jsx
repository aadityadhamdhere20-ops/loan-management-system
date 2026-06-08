import { useState } from "react";
import API from "../services/api";

function AdminActions() {
  const [applicationId, setApplicationId] = useState("");
  const [approvedAmount, setApprovedAmount] = useState("");
  const [interestRate, setInterestRate] = useState("");
  const [remarks, setRemarks] = useState("");
  const [reason, setReason] = useState("");
  const [msg, setMsg] = useState("");

  const approveLoan = async () => {
    try {
      const data = {
        approvedAmount: Number(approvedAmount),
        interestRate: Number(interestRate),
        remarks: remarks,
      };

      const res = await API.post("/admin/loan/approve/" + applicationId, data);
      setMsg(res.data.message + " | Sanction ID: " + res.data.data);
    } catch (error) {
      setMsg(error.response?.data?.message || "Approval failed");
    }
  };

  const rejectLoan = async () => {
    try {
      const res = await API.post("/admin/loan/reject/" + applicationId + "?reason=" + encodeURIComponent(reason));
      setMsg(res.data.message);
    } catch (error) {
      setMsg(error.response?.data?.message || "Reject failed");
    }
  };

  const disburseLoan = async () => {
    try {
      const res = await API.post("/admin/loan/disburse/" + applicationId);
      setMsg(res.data.message + " | Loan Account ID: " + res.data.data);
    } catch (error) {
      setMsg(error.response?.data?.message || "Disbursement failed");
    }
  };

  return (
    <div className="card-box">
      <h3>Admin Loan Actions</h3>

      <input className="form-control" placeholder="Application ID" onChange={(e) => setApplicationId(e.target.value)} />
      <input className="form-control" placeholder="Approved Amount" onChange={(e) => setApprovedAmount(e.target.value)} />
      <input className="form-control" placeholder="Interest Rate" onChange={(e) => setInterestRate(e.target.value)} />
      <input className="form-control" placeholder="Remarks" onChange={(e) => setRemarks(e.target.value)} />

      <button className="btn btn-success" onClick={approveLoan}>Approve Loan</button>

      <hr />

      <input className="form-control" placeholder="Reject Reason" onChange={(e) => setReason(e.target.value)} />

      <button className="btn btn-danger" onClick={rejectLoan}>Reject Loan</button>
      <button className="btn btn-warning ms-2" onClick={disburseLoan}>Disburse Loan</button>

      <p className="mt-3">{msg}</p>
    </div>
  );
}

export default AdminActions;