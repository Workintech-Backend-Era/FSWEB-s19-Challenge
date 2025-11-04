// src/components/Profile.jsx
import React, { useEffect, useState, useContext } from "react";
import axios from "axios";
import { AuthContext } from "../context/AuthContext";

const Profile = () => {
  const { token } = useContext(AuthContext);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const loadProfile = async () => {
      try {
        const res = await axios.get("http://localhost:3000/api/users/me", {
          headers: { Authorization: `Bearer ${token}` },
        });
        setUser(res.data);
      } catch (err) {
        console.error("Profil yüklenemedi:", err);
      }
    };
    loadProfile();
  }, [token]);

  if (!user) return <p>Profil yükleniyor...</p>;

  return (
    <div className="p-6">
      <h2 className="text-2xl font-semibold mb-4">Profil Bilgileri</h2>
      <p><strong>Kullanıcı Adı:</strong> {user.username}</p>
      <p><strong>E-posta:</strong> {user.email}</p>
      <p><strong>Oluşturulma Tarihi:</strong> {new Date(user.createdAt).toLocaleDateString()}</p>
    </div>
  );
};

export default Profile;
