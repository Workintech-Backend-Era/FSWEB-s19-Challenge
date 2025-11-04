import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import axios from "axios";

export default function TweetItem({ tweet, retweetData: initialRetweetData }) {
  const { token } = useAuth();
  const [retweetData, setRetweetData] = useState(initialRetweetData);

  useEffect(()=>{
    setRetweetData(initialRetweetData);
  },[initialRetweetData]);

  const like = async () => {
    try {
      await axios.post(
        `http://localhost:3000/api/likes/tweet/${tweet.id}`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      console.log("Liked");
    } catch (err) {
      console.error("Like error:", err.response ?? err);
    }
  };

  const retweet = async () => {
    try {
      if (retweetData) {
        await axios.delete(
          `http://localhost:3000/api/retweets/${retweetData.id}`,
          { headers: { Authorization: `Bearer ${token}` } }
        );
        setRetweetData(null);
      } else {
        const res = await axios.post(
          "http://localhost:3000/api/retweets",
          { tweetId: tweet.id, comment: null },
          { headers: { Authorization: `Bearer ${token}` } }
        );
        setRetweetData(res.data);
      }
    } catch (err) {
      console.error("Retweet error:", err.response?.data || err);
    }
  };


  console.log("initialRetweetData::::::=>", initialRetweetData, "retweetData => ", retweetData );

  return (
    <div className="border rounded p-3 mb-2">
      <p>{tweet.content}</p>
      <div className="flex gap-2 mt-2">
        <button onClick={like} className="text-sm text-blue-600">
          Beğen
        </button>
        <button
          onClick={retweet}
          className={`text-sm ${
            retweetData ? "text-red-500" : "text-green-600"
          }`}
        >
          {retweetData ? "Retweet'i Geri Al" : "Retweet"}
        </button>
      </div>
    </div>
  );
}
