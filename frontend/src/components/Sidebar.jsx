import { NavLink, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const links = [
  ["Dashboard", "/dashboard"],
  ["Questions", "/questions"],
  ["Favorites", "/favorites"],
  ["Progress", "/progress"],
  ["Roles", "/roles"],
];

export default function Sidebar() {
  const { logout } = useAuth();
  const navigate = useNavigate();
  return (
    <aside className="sidebar p-3">
      <div className="fw-bold fs-5 mb-4">SIP Portal</div>
      {links.map(([label, path]) => (
        <NavLink key={path} to={path} className="sidebar-link d-block mb-2 p-3 rounded-3">
          {label}
        </NavLink>
      ))}
      <button className="btn btn-light w-100 mt-3 fw-semibold" onClick={() => { logout(); navigate("/login"); }}>
        Logout
      </button>
    </aside>
  );
}
