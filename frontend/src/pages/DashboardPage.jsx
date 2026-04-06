import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";
import api from "../services/api";
import StatCard from "../components/StatCard";

export default function DashboardPage() {
  const [progress, setProgress] = useState(null);
  useEffect(() => {
    api.get("/progress")
      .then((r) => setProgress(r.data))
      .catch((err) => {
        if (![401, 403].includes(err?.response?.status)) {
          toast.error("Unable to load dashboard");
        }
      });
  }, []);
  if (!progress) return <p>Loading dashboard...</p>;
  return (
    <>
      <div className="hero-banner mb-4">
        <h4 className="mb-1 fw-bold">Your interview prep is on track</h4>
        <p className="mb-0 text-white-50">Build momentum every day with focused practice.</p>
      </div>
      <div className="row g-3 mb-4">
        <StatCard title="Total Questions" value={progress.totalQuestions} />
        <StatCard title="Completed" value={progress.completedQuestions} />
        <StatCard title="Favorites" value={progress.favoriteQuestions} />
        <StatCard title="Remaining" value={progress.remainingQuestions} />
      </div>
      <div className="card p-4 border-0 shadow-sm soft-card">
        <h6 className="fw-bold">Progress: {progress.completionPercentage.toFixed(1)}%</h6>
        <div className="progress progress-premium">
          <div className="progress-bar progress-bar-premium" style={{ width: `${progress.completionPercentage}%` }} />
        </div>
        <div className="mt-3 d-flex gap-2">
          <Link className="btn btn-primary btn-glow" to="/questions">Browse Questions</Link>
          <Link className="btn btn-outline-primary" to="/favorites">View Favorites</Link>
          <Link className="btn btn-outline-dark" to="/progress">Track Progress</Link>
        </div>
      </div>
    </>
  );
}
