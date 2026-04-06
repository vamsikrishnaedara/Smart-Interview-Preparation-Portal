import { useEffect, useState } from "react";
import { Bar, BarChart, Cell, Pie, PieChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from "recharts";
import { toast } from "react-toastify";
import api from "../services/api";

export default function ProgressPage() {
  const [p, setP] = useState(null);
  useEffect(() => {
    api.get("/progress")
      .then((r) => setP(r.data))
      .catch((err) => {
        if (![401, 403].includes(err?.response?.status)) {
          toast.error("Unable to load progress");
        }
      });
  }, []);
  if (!p) return <p>Loading progress...</p>;
  const pieData = [{ name: "Completed", value: p.completedQuestions }, { name: "Remaining", value: p.remainingQuestions }];
  const topicData = Object.entries(p.topicWiseCompleted).map(([name, value]) => ({ name, value }));
  return (
    <div className="row g-3">
      <div className="col-lg-6"><div className="card p-3 border-0 shadow-sm"><h6>Completed vs Remaining</h6><ResponsiveContainer width="100%" height={260}><PieChart><Pie data={pieData} dataKey="value">{pieData.map((_, i) => <Cell key={i} fill={i ? "#64748b" : "#6366f1"} />)}</Pie><Tooltip /></PieChart></ResponsiveContainer></div></div>
      <div className="col-lg-6"><div className="card p-3 border-0 shadow-sm"><h6>Topic-wise completion</h6><ResponsiveContainer width="100%" height={260}><BarChart data={topicData}><XAxis dataKey="name" /><YAxis /><Tooltip /><Bar dataKey="value" fill="#7c3aed" /></BarChart></ResponsiveContainer></div></div>
    </div>
  );
}
