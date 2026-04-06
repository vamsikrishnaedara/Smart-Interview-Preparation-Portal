import { useState } from "react";

export default function QuestionCard({ q, onFavorite, onComplete }) {
  const [show, setShow] = useState(false);
  return (
    <div className="card question-card border-0 shadow-sm mb-3">
      <div className="card-body">
        <div className="d-flex justify-content-between">
          <h6 className="fw-semibold">{q.title}</h6>
          <button className="btn btn-sm favorite-btn" onClick={() => onFavorite(q)}>{q.favorite ? "❤️" : "🤍"}</button>
        </div>
        <div className="d-flex gap-2 flex-wrap mb-2">
          <span className="badge badge-chip badge-topic">{q.topic}</span>
          <span className="badge badge-chip badge-difficulty">{q.difficulty}</span>
          <span className="badge badge-chip badge-company">{q.company}</span>
          {q.frequentlyAsked && <span className="badge badge-chip badge-frequent">Frequently Asked</span>}
        </div>
        <button className="btn btn-link p-0 fw-semibold" onClick={() => setShow(!show)}>{show ? "Hide Answer" : "Show Answer"}</button>
        {show && <p className="mt-2 mb-2">{q.answer}</p>}
        <div className="form-check">
          <input className="form-check-input" type="checkbox" checked={q.completed} onChange={() => onComplete(q)} />
          <label className="form-check-label">Mark Completed</label>
        </div>
      </div>
    </div>
  );
}
