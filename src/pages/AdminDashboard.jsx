import { useState } from "react";
import API from "../services/api";

function AdminDashboard() {
  const [result, setResult] = useState("");

  const loadDashboard = async () => {
    try {
      const res = await API.get("/dashboard/admin");
      setResult(JSON.stringify(res.data, null, 2));
    } catch (error) {
      setResult(error.response?.data?.message || "Only admin can access dashboard");
    }
  };

  return (
    <div className="card-box">
      <h3>Admin Dashboard</h3>

      <button className="btn btn-primary" onClick={loadDashboard}>Load Dashboard</button>

      <pre className="mt-3">{result}</pre>
    </div>
  );
}

export default AdminDashboard;