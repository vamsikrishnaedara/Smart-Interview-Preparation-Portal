import { useAuth } from "../context/AuthContext";

export default function Navbar() {
  const { user } = useAuth();
  return (
    <div className="navbar-main d-flex justify-content-between align-items-center px-4 py-3">
      <h5 className="m-0 fw-bold text-gradient">Smart Interview Preparation Portal</h5>
      <span className="welcome-pill">Welcome, {user.name || "User"}</span>
    </div>
  );
}
