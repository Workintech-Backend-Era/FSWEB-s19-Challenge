// src/components/RetweetItem.jsx
import React, { useState } from "react";
import axios from "axios";
import { useAuth } from "../context/AuthContext";

export default function RetweetItem({ retweet, onUpdated, onDeleted }) {
  const { token } = useAuth();
  const [comment, setComment] = useState(retweet.comment || "");
  const [editing, setEditing] = useState(false);

  const handleUpdate = async () => {
    try {
      const res = await axios.put(
        `http://localhost:3000/api/retweets/${retweet.id}`,
        { tweetId: retweet.tweet.id, comment },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      onUpdated && onUpdated(res.data);
      setEditing(false);
    } catch (err) {
      console.error("Retweet güncellenemedi:", err.response?.data || err);
    }
  };

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:3000/api/retweets/${retweet.id}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      onDeleted && onDeleted(retweet.id);
    } catch (err) {
      console.error("Retweet silinemedi:", err.response?.data || err);
    }
  };

  return (
    <div className="border rounded p-3 mb-3">
      <p className="text-sm text-gray-600">
        Tweet ID: {retweet.tweet.id}
      </p>
      {editing ? (
        <>
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
            className="border p-2 w-full mb-2"
          />
          <button onClick={handleUpdate} className="bg-blue-500 text-white px-3 py-1 rounded mr-2">
            Kaydet
          </button>
          <button onClick={() => setEditing(false)} className="bg-gray-400 text-white px-3 py-1 rounded">
            Vazgeç
          </button>
        </>
      ) : (
        <>
          <p>{retweet.comment || "(Yorum yok)"}</p>
          <div className="flex gap-2 mt-2">
            <button
              onClick={() => setEditing(true)}
              className="text-sm text-blue-600"
            >
              Düzenle
            </button>
            <button
              onClick={handleDelete}
              className="text-sm text-red-600"
            >
              Sil
            </button>
          </div>
        </>
      )}
    </div>
  );
}
