// src/components/RetweetForm.jsx
import React, { useState } from "react";
import axios from "axios";
import { useAuth } from "../context/AuthContext";

export default function RetweetForm({ onCreated }) {
  const { token } = useAuth();
  const [tweetId, setTweetId] = useState("");
  const [comment, setComment] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post(
        "http://localhost:3000/api/retweets",
        { tweetId: Number(tweetId), comment },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      onCreated && onCreated(res.data);
      setTweetId("");
      setComment("");
    } catch (err) {
      console.error("Retweet oluşturulamadı:", err.response?.data || err);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="border rounded p-4 mb-6">
      <h3 className="font-semibold mb-2">Yeni Retweet</h3>
      <input
        type="number"
        placeholder="Tweet ID"
        value={tweetId}
        onChange={(e) => setTweetId(e.target.value)}
        className="border p-2 w-full mb-2"
        required
      />
      <textarea
        placeholder="Yorum (opsiyonel)"
        value={comment}
        onChange={(e) => setComment(e.target.value)}
        className="border p-2 w-full mb-2"
      />
      <button
        type="submit"
        className="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600"
      >
        Retweetle
      </button>
    </form>
  );
}
