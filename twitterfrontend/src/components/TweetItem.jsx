import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import axios from "axios";

export default function TweetItem({ tweet, retweetData: initialRetweetData }) {
  const { token } = useAuth();
  const [retweetData, setRetweetData] = useState(initialRetweetData);
  const [showComments, setShowComments] = useState(false);
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState("");

  useEffect(()=>{
    setRetweetData(initialRetweetData);
  },[initialRetweetData]);

  const headers = { Authorization: `Bearer ${token}` };

  const like = async () => {
    try {
      await axios.post(
        `http://localhost:3000/api/likes/tweet/${tweet.id}`,
        {},
        { headers }
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
          { headers }
        );
        setRetweetData(null);
      } else {
        const res = await axios.post(
          "http://localhost:3000/api/retweets",
          { tweetId: tweet.id, comment: null },
          { headers }
        );
        setRetweetData(res.data);
      }
    } catch (err) {
      console.error("Retweet error:", err.response?.data || err);
    }
  };

  const fetchComments = async () => {
    try {
      const res = await axios.get(
        `http://localhost:3000/api/comments/tweet/${tweet.id}`,
        { headers }
      );

      console.log("response data:::======>", res);
      setComments(res.data.slice(0, 3));
    } catch (err) {
      console.error("Fetch comments error:", err.response ?? err);
    }
  };

  const postComment = async () => {
    try {
      const res = await axios.post(
        `http://localhost:3000/api/comments`,
        { tweet: { id: tweet.id }, text: newComment },
        { headers }
      );
      setComments([res.data, ...comments]);
      setNewComment("");
    } catch (err) {
      console.error("Post comment error:", err.response ?? err);
    }
  };

  const toggleCommentLike = async (commentId) => {
    try {
      const res = await axios.post(
        `http://localhost:3000/api/likes/comment/${commentId}`,
        {},
        { headers }
      );
      console.log("comment liked/unliked:", res.data);
    } catch (err) {
      console.error("Comment like error:", err.response ?? err);
    }
  };


  console.log("initialRetweetData::::::=>", initialRetweetData, "retweetData => ", retweetData );
  console.log("TweeeeeeeeeeeeeetDaaaaaaaaaaaaaaataaaaaaaaaaaaa=== >",tweet.id);
  return (
  <div className="border rounded p-3 mb-2">
      <p>{tweet.content}</p>

      <div className="flex gap-3 mt-2">
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

        <button
          onClick={() => {
            setShowComments((prev) => !prev);
            if (!showComments) fetchComments();
          }}
          className="text-sm text-gray-700"
        >
          {showComments ? "Yorumları Gizle" : "Yorumları Gör"}
        </button>
      </div>

      {showComments && (
        <div className="mt-3 border-t pt-2">
          <div className="flex gap-2 mb-2">
            <input
              type="text"
              value={newComment}
              onChange={(e) => setNewComment(e.target.value)}
              placeholder="Yorum yaz..."
              className="border rounded px-2 py-1 flex-grow"
            />
            <button
              onClick={postComment}
              disabled={!newComment.trim()}
              className="text-sm text-green-600"
            >
              Gönder
            </button>
          </div>

          {comments.map((comment) => (
            <div key={comment.id} className="border rounded p-2 mb-1">
              <p>{comment.text}</p>
              <button
                onClick={() => toggleCommentLike(comment.id)}
                className="text-xs text-blue-600"
              >
                {comment.likedByUser ? "Unlike" : "Like"}
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
