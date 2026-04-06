import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { toast } from "react-toastify";
import api from "../services/api";
import QuestionCard from "../components/QuestionCard";

export default function QuestionsPage() {
  const [params] = useSearchParams();
  const [questions, setQuestions] = useState([]);
  const [filters, setFilters] = useState({ keyword: "", topic: "", company: "", difficulty: "", frequentOnly: false });

  const load = async () => {
    try {
      const role = params.get("role");
      const endpoint = role ? `/questions/role/${encodeURIComponent(role)}` : "/questions";
      const { data } = await api.get(endpoint);
      setQuestions(data);
    } catch (err) {
      if (![401, 403].includes(err?.response?.status)) {
        toast.error("Unable to load questions");
      }
    }
  };
  useEffect(() => { load(); }, [params]);

  const onFavorite = async (q) => {
    try {
      await (q.favorite ? api.delete(`/favorites/${q.id}`) : api.post(`/favorites/${q.id}`));
      toast.success(q.favorite ? "Removed favorite" : "Added favorite");
      load();
    } catch (err) {
      if (![401, 403].includes(err?.response?.status)) {
        toast.error("Failed to update favorite");
      }
    }
  };
  const onComplete = async (q) => {
    try {
      await (q.completed ? api.delete(`/completed/${q.id}`) : api.post(`/completed/${q.id}`));
      toast.success(q.completed ? "Marked pending" : "Marked completed");
      load();
    } catch (err) {
      if (![401, 403].includes(err?.response?.status)) {
        toast.error("Failed to update completion");
      }
    }
  };

  const normalize = (value) => (value || "").toLowerCase().trim();

  const filtered = questions.filter((q) =>
    (!filters.keyword || normalize(`${q.title} ${q.answer}`).includes(normalize(filters.keyword))) &&
    (!filters.topic || normalize(q.topic).includes(normalize(filters.topic))) &&
    (!filters.company || normalize(q.company).includes(normalize(filters.company))) &&
    (!filters.difficulty || q.difficulty === filters.difficulty) &&
    (!filters.frequentOnly || q.frequentlyAsked)
  );

  return (
    <>
      <div className="card p-3 mb-3 border-0 shadow-sm">
        <div className="row g-2">
          <div className="col-md-3"><input className="form-control" placeholder="Search keyword" onChange={(e) => setFilters({ ...filters, keyword: e.target.value })} /></div>
          <div className="col-md-2"><input className="form-control" placeholder="Topic" onChange={(e) => setFilters({ ...filters, topic: e.target.value })} /></div>
          <div className="col-md-2"><input className="form-control" placeholder="Company" onChange={(e) => setFilters({ ...filters, company: e.target.value })} /></div>
          <div className="col-md-2"><select className="form-select" onChange={(e) => setFilters({ ...filters, difficulty: e.target.value })}><option value="">All difficulty</option><option>Easy</option><option>Medium</option><option>Hard</option></select></div>
          <div className="col-md-3 d-flex align-items-center"><input type="checkbox" className="form-check-input me-2" onChange={(e) => setFilters({ ...filters, frequentOnly: e.target.checked })} /> Frequently Asked</div>
        </div>
      </div>
      {filtered.length === 0 ? <p>No questions found.</p> : filtered.map((q) => <QuestionCard key={q.id} q={q} onFavorite={onFavorite} onComplete={onComplete} />)}
    </>
  );
}
