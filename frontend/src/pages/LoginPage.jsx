import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import api from "../services/api";
import { useAuth } from "../context/AuthContext";

export default function LoginPage() {
  const [form, setForm] = useState({ email: "", password: "" });
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const { login } = useAuth();

  const onSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const { data } = await api.post("/auth/login", form);
      login(data);
      toast.success("Login successful");
      navigate("/dashboard");
    } catch (err) {
      toast.error(err.response?.data?.message || "Login failed");
    } finally { setLoading(false); }
  };

  return (
    <div className="auth-wrap">
      <form className="auth-card auth-glass" onSubmit={onSubmit}>
        <p className="auth-tag">Welcome back</p>
        <h3 className="mb-3">Sign in to continue</h3>
        <input className="form-control mb-2" placeholder="Email" type="email" required onChange={(e) => setForm({ ...form, email: e.target.value })} />
        <input className="form-control mb-3" placeholder="Password" type="password" required onChange={(e) => setForm({ ...form, password: e.target.value })} />
        <button className="btn btn-primary btn-glow w-100" disabled={loading}>{loading ? "Signing in..." : "Login"}</button>
        <p className="mt-3 mb-0">No account? <Link to="/register">Register</Link></p>
      </form>
    </div>
  );
}
