import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function Login() {
  const [form, setForm] = useState({
    username: "",
    password: "",
  });

  const [msg, setMsg] = useState("");
  const navigate = useNavigate();

  const change = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const login = async (e) => {
    e.preventDefault();

    try {
      const res = await API.post("/auth/login", form);

      localStorage.setItem("token", res.data.token);
      localStorage.setItem("role", res.data.role);
      localStorage.setItem("userId", res.data.userId);

      setMsg("Login successful");

      // Redirect to Apply Loan page
      navigate("/apply-loan");

    } catch (error) {
      setMsg("Invalid email or password");
    }
  };

  return (
    <div className="card-box">
      <h3>Login</h3>

      <form onSubmit={login}>
        <input
          className="form-control"
          name="username"
          placeholder="Email"
          onChange={change}
        />

        <input
          className="form-control"
          name="password"
          type="password"
          placeholder="Password"
          onChange={change}
        />

        <button className="btn btn-primary">Login</button>
      </form>

      <p className="mt-3">{msg}</p>
    </div>
  );
}

export default Login;