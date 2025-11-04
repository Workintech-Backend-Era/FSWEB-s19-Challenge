// src/components/TweetForm.jsx
import React, { useState, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../context/AuthContext";

const TweetForm = ({ onTweetPosted }) => {
  const { token } = useContext(AuthContext);
  const [content, setContent] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!content.trim()) {
      setError("Tweet boş olamaz!");
      return;
    }

    try {
      const res = await axios.post(
        "http://localhost:3000/api/tweets",
        { content },
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      // Tweet başarıyla gönderilirse formu sıfırla
      setContent("");
      setError("");
      if (onTweetPosted) onTweetPosted(res.data);
    } catch (err) {
      console.error("Tweet gönderilemedi:", err);
      setError("Tweet gönderilirken bir hata oluştu.");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="bg-white border rounded-lg shadow p-4 mb-6"
    >
      <textarea
        value={content}
        onChange={(e) => setContent(e.target.value)}
        placeholder="Neler oluyor?"
        className="w-full border-none outline-none resize-none text-lg"
        rows={3}
      />
      {error && <p className="text-red-500 text-sm mb-2">{error}</p>}
      <div className="flex justify-end">
        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded-full hover:bg-blue-600 transition"
        >
          Tweetle
        </button>
      </div>
    </form>
  );
};

export default TweetForm;
