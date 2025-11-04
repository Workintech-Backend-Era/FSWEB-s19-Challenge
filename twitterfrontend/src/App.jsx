import { AuthProvider, useAuth } from "./context/AuthContext";
import Login from "./components/Login";
import Register from "./components/Register";
import TweetForm from "./components/TweetForm";
import TweetList from "./components/TweetList";
import RetweetForm from "./components/RetweetForm";

function Content() {
  const { token, logout } = useAuth();

  if (!token) {
    return (
      <div>
        <Login />
        <Register />
      </div>
    );
  }

  return (
    <div>
      <button onClick={logout} className="bg-red-500 text-white p-2 m-4 rounded">
        Çıkış Yap
      </button>
      <TweetForm onTweetPosted={() => window.location.reload()} />
      <TweetList />
      <RetweetForm />
    </div>
  );
}

export default function App() {
  return (
    <AuthProvider>
      <Content />
    </AuthProvider>
  );
}
