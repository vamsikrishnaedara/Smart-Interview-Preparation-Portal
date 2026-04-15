import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import api from "../services/api";
import { useAuth } from "../context/AuthContext";

function errorMessage(err) {
  const data = err?.response?.data;
  if (typeof data?.message === "string") return data.message;
  if (typeof data === "string") return data;
  return err?.message || "Registration failed";
}

export default function RegisterPage() {
  const [form, setForm] = useState({ name: "", email: "", password: "" });
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const { login } = useAuth();

  const onSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    const payload = {
      name: form.name.trim(),
      email: form.email.trim(),
      password: form.password,
    };
    try {
      await api.post("/auth/register", payload);
      toast.success("Registration successful! Please login to continue.");
      navigate("/login");
    } catch (err) {
      toast.error(errorMessage(err));
    } finally { setLoading(false); }
  };

  return (
    <div className="auth-wrap">
      <form className="auth-card auth-glass" onSubmit={onSubmit}>
        <p className="auth-tag">Create your account</p>
        <h3 className="mb-3">Join Smart Interview Portal</h3>
        <input className="form-control mb-2" placeholder="Name" required onChange={(e) => setForm({ ...form, name: e.target.value })} />
        <input className="form-control mb-2" placeholder="Email" type="email" required onChange={(e) => setForm({ ...form, email: e.target.value })} />
        <input className="form-control mb-3" placeholder="Password" type="password" required minLength={6} onChange={(e) => setForm({ ...form, password: e.target.value })} />
        <button className="btn btn-primary btn-glow w-100" disabled={loading}>{loading ? "Creating..." : "Register"}</button>
        <p className="mt-3 mb-0">Already have an account? <Link to="/login">Login</Link></p>
      </form>
    </div>
  );
}
