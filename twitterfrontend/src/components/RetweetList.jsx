// src/components/RetweetList.jsx
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../context/AuthContext";
import RetweetItem from "./RetweetItem";
import RetweetForm from "./RetweetForm";

export default function RetweetList() {
  const { token } = useAuth();
  const [retweets, setRetweets] = useState([]);

  const loadRetweets = async () => {
    try {
      const res = await axios.get("http://localhost:3000/api/retweets", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setRetweets(res.data);
    } catch (err) {
      console.error("Retweetler yüklenemedi:", err.response?.data || err);
    }
  };

  useEffect(() => {
    loadRetweets();
  }, [token]);

  return (
    <div className="max-w-xl mx-auto mt-10">
      <RetweetForm onCreated={loadRetweets} />
      <h2 className="text-xl font-semibold mb-4">Retweetlerim</h2>
      {retweets.length === 0 ? (
        <p>Henüz retweet yok.</p>
      ) : (
        retweets.map((r) => (
          <RetweetItem
            key={r.id}
            retweet={r}
            onUpdated={loadRetweets}
            onDeleted={loadRetweets}
          />
        ))
      )}
    </div>
  );
}
