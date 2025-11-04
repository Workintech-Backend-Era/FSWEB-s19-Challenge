import React, { useState } from "react";
import TweetForm from "./TweetForm";
import TweetList from "./TweetList";

const Home = () => {
  const [tweets, setTweets] = useState([]);

  const handleNewTweet = (newTweet) => {
    setTweets((prev) => [newTweet, ...prev]);
  };

  return (
    <div className="max-w-xl mx-auto mt-10">
      <TweetForm onTweetPosted={handleNewTweet} />
      <TweetList tweets={tweets} />
    </div>
  );
};

export default Home;
