export default function StatCard({ title, value }) {
  return (
    <div className="col-md-6 col-xl-3">
      <div className="stat-card p-3 p-lg-4 h-100">
        <small className="text-uppercase stat-title">{title}</small>
        <h3 className="mt-2 mb-0 fw-bold">{value}</h3>
      </div>
    </div>
  );
}
