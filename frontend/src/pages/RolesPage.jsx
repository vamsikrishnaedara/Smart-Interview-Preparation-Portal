import { useNavigate } from "react-router-dom";

const roles = [
  ["Java Developer", "Core Java, Spring, JPA"],
  ["React Developer", "Hooks, Routing, State"],
  ["Full Stack Developer", "Frontend + Backend"],
  ["Data Analyst", "SQL, BI, analytics"],
  ["DevOps Engineer", "CI/CD, Docker, Cloud"],
  ["Python Developer", "Core Python, APIs"],
];

export default function RolesPage() {
  const navigate = useNavigate();
  return (
    <div className="row g-3">
      {roles.map(([role, desc]) => (
        <div className="col-md-6 col-xl-4" key={role}>
          <div className="card p-4 h-100 border-0 shadow-sm role-card">
            <h5 className="fw-bold">{role}</h5>
            <p className="text-muted mb-4">{desc}</p>
            <button className="btn btn-primary btn-glow mt-auto" onClick={() => navigate(`/questions?role=${encodeURIComponent(role)}`)}>
              View Questions
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}
