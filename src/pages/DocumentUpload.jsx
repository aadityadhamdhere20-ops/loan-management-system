import { useState } from "react";
import API from "../services/api";

function DocumentUpload() {
  const [applicationId, setApplicationId] = useState("");
  const [type, setType] = useState("PAN");
  const [file, setFile] = useState(null);
  const [documentId, setDocumentId] = useState("");
  const [remarks, setRemarks] = useState("");
  const [msg, setMsg] = useState("");

  const upload = async () => {
    try {
      const formData = new FormData();
      formData.append("applicationId", applicationId);
      formData.append("type", type);
      formData.append("file", file);

      const res = await API.post("/document/upload", formData);
      setMsg(res.data.message + " | Document ID: " + res.data.data);
    } catch (error) {
      setMsg(error.response?.data?.message || "Document upload failed");
    }
  };

  const approve = async () => {
    const res = await API.post("/document/approve/" + documentId);
    setMsg(res.data.message);
  };

  const reject = async () => {
    const res = await API.post("/document/reject/" + documentId + "?remarks=" + encodeURIComponent(remarks));
    setMsg(res.data.message);
  };

  const returnDoc = async () => {
    const res = await API.post("/document/return/" + documentId + "?remarks=" + encodeURIComponent(remarks));
    setMsg(res.data.message);
  };

  return (
    <div className="card-box">
      <h3>Document Upload</h3>

      <input className="form-control" placeholder="Application ID" onChange={(e) => setApplicationId(e.target.value)} />

      <select className="form-control" onChange={(e) => setType(e.target.value)}>
        <option value="PAN">PAN</option>
        <option value="AADHAAR">AADHAAR</option>
        <option value="SALARY_SLIP">SALARY SLIP</option>
        <option value="BANK_STATEMENT">BANK STATEMENT</option>
        <option value="ADDRESS_PROOF">ADDRESS PROOF</option>
      </select>

      <input className="form-control" type="file" onChange={(e) => setFile(e.target.files[0])} />

      <button className="btn btn-primary" onClick={upload}>Upload</button>

      <hr />

      <h4>Admin Document Action</h4>

      <input className="form-control" placeholder="Document ID" onChange={(e) => setDocumentId(e.target.value)} />
      <input className="form-control" placeholder="Remarks" onChange={(e) => setRemarks(e.target.value)} />

      <button className="btn btn-success" onClick={approve}>Approve</button>
      <button className="btn btn-danger ms-2" onClick={reject}>Reject</button>
      <button className="btn btn-warning ms-2" onClick={returnDoc}>Return</button>

      <p className="mt-3">{msg}</p>
    </div>
  );
}

export default DocumentUpload;