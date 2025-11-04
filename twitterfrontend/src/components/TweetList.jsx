import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../context/AuthContext";
import TweetItem from "./TweetItem";

const TweetList = () => {
  const { token } = useContext(AuthContext);
  const [tweets, setTweets] = useState([]);
  const [retweets, setRetweets] = useState([]);

  useEffect(() => {
    const loadData = async () => {
      try {
        // Tüm tweetleri al
        const tweetsRes = await axios.get("http://localhost:3000/api/tweets", {
          headers: { Authorization: `Bearer ${token}` },
        });
        setTweets(tweetsRes.data);

        // Kullanıcının retweetlerini al
        const retweetsRes = await axios.get("http://localhost:3000/api/retweets", {
          headers: { Authorization: `Bearer ${token}` },
        });
        setRetweets(retweetsRes.data);
      } catch (err) {
        console.error("Veriler yüklenemedi:", err.response?.data || err);
      }
    };
    loadData();
  }, [token]);

  const findRetweetForTweet = (tweetId) =>
    retweets.find((r) => r.originalTweetId === tweetId);

  console.log("retweets: ", retweets);

  return (
    <div className="p-6">
      <h2 className="text-xl font-semibold mb-4">Tweetler</h2>
      {tweets.map((tweet) => {
        const retweet = findRetweetForTweet(tweet.id);
        return (
          <TweetItem
            key={tweet.id}
            tweet={tweet}
            retweetData={retweet ? { id: retweet.id } : null}
          />
        );
      })}
    </div>
  );
};

export default TweetList;
