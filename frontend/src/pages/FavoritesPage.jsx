import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import api from "../services/api";
import QuestionCard from "../components/QuestionCard";

export default function FavoritesPage() {
  const [items, setItems] = useState([]);
  const load = async () => {
    try {
      setItems((await api.get("/favorites")).data);
    } catch (err) {
      if (![401, 403].includes(err?.response?.status)) {
        toast.error("Unable to load favorites");
      }
    }
  };
  useEffect(() => { load(); }, []);
  const onFavorite = async (q) => {
    try {
      await api.delete(`/favorites/${q.id}`);
      load();
    } catch (err) {
      if (![401, 403].includes(err?.response?.status)) {
        toast.error("Failed to remove favorite");
      }
    }
  };
  const onComplete = async (q) => {
    try {
      await (q.completed ? api.delete(`/completed/${q.id}`) : api.post(`/completed/${q.id}`));
      load();
    } catch (err) {
      if (![401, 403].includes(err?.response?.status)) {
        toast.error("Failed to update completion");
      }
    }
  };
  return <>{items.length === 0 ? <p>No favorite questions yet.</p> : items.map((q) => <QuestionCard key={q.id} q={q} onFavorite={onFavorite} onComplete={onComplete} />)}</>;
}
