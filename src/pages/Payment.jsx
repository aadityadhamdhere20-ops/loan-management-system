import { useState } from "react";
import API from "../services/api";

function Payment() {
  const [form, setForm] = useState({
    loanAccountId: "",
    emiId: "",
    paidAmount: "",
    paymentMode: "UPI",
  });

  const [msg, setMsg] = useState("");

  const change = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const payEmi = async (e) => {
    e.preventDefault();

    try {
      const data = {
        loanAccountId: Number(form.loanAccountId),
        emiId: Number(form.emiId),
        paidAmount: Number(form.paidAmount),
        paymentMode: form.paymentMode,
      };

      const res = await API.post("/repayment/pay", data);
      setMsg(res.data.message + " | Transaction ID: " + res.data.data);
    } catch (error) {
      setMsg(error.response?.data?.message || "Payment failed");
    }
  };

  return (
    <div className="card-box">
      <h3>Pay EMI</h3>

      <form onSubmit={payEmi}>
        <input className="form-control" name="loanAccountId" placeholder="Loan Account ID" onChange={change} />
        <input className="form-control" name="emiId" placeholder="EMI ID" onChange={change} />
        <input className="form-control" name="paidAmount" placeholder="Paid Amount" onChange={change} />

        <select className="form-control" name="paymentMode" onChange={change}>
          <option value="UPI">UPI</option>
          <option value="CARD">CARD</option>
          <option value="NET_BANKING">NET BANKING</option>
          <option value="CASH">CASH</option>
        </select>

        <button className="btn btn-success">Pay EMI</button>
      </form>

      <p className="mt-3">{msg}</p>
    </div>
  );
}

export default Payment;