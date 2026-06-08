import { useState } from "react";
import API from "../services/api";

function Register() {
  const [form, setForm] = useState({
    fullName: "",
    email: "",
    mobileNumber: "",
    password: "",
    age: "",
    monthlyIncome: "",
  });

  const [msg, setMsg] = useState("");

  const change = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const register = async (e) => {
    e.preventDefault();

    try {
      const data = {
        ...form,
        age: Number(form.age),
        monthlyIncome: Number(form.monthlyIncome),
      };

      const res = await API.post("/auth/register", data);

      setMsg(res.data.message || "Registered successfully");
    } catch (error) {
  console.log(error);
  setMsg(error.response?.data?.message || error.message || "Registration failed");
}
  };

  return (
    <div className="card-box">
      <h3>Customer Register</h3>

      <form onSubmit={register}>
        <input
          className="form-control"
          name="fullName"
          placeholder="Full Name"
          onChange={change}
        />

        <input
          className="form-control"
          name="email"
          placeholder="Email"
          onChange={change}
        />

        <input
          className="form-control"
          name="mobileNumber"
          placeholder="Mobile Number"
          onChange={change}
        />

        <input
          className="form-control"
          name="password"
          type="password"
          placeholder="Password"
          onChange={change}
        />

        <input
          className="form-control"
          name="age"
          type="number"
          placeholder="Age"
          onChange={change}
        />

        <input
          className="form-control"
          name="monthlyIncome"
          type="number"
          placeholder="Monthly Income"
          onChange={change}
        />

        <button className="btn btn-success">Register</button>
      </form>

      <p className="mt-3">{msg}</p>
    </div>
  );
}

export default Register;