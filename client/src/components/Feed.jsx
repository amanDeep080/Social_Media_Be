import React, { useEffect, useState } from "react";
import { api } from "../api";
import PostCard from "./PostCard";

export default function Feed() {
  const [caption, setCaption] = useState("");
  const [imageUrl, setImageUrl] = useState("");
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(false);

  // ✅ Change these if your backend endpoints differ
  const GET_POSTS_URL = "/posts";        // GET all posts
  const CREATE_POST_URL = "/posts";      // POST create post

  const loadPosts = async () => {
    try {
      setLoading(true);
      const res = await api.get(GET_POSTS_URL);
      // backend returns array
      setPosts(res.data || []);
    } catch (err) {
      console.log(err);
      alert(err?.response?.data?.message || "Failed to load posts");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadPosts();
  }, []);

  const handleCreatePost = async (e) => {
    e.preventDefault();

    if (!caption.trim()) {
      alert("Caption required");
      return;
    }

    try {
      const payload = {
        caption: caption.trim(),
        imageUrl: imageUrl.trim() || null,
      };

      const res = await api.post(CREATE_POST_URL, payload);

      // ✅ If backend returns created post object
      const created = res.data;

      // instantly add on UI
      setPosts((prev) => [created, ...prev]);

      setCaption("");
      setImageUrl("");
    } catch (err) {
      console.log(err);
      alert(err?.response?.data?.message || "Failed to create post");
    }
  };

  return (
    <div className="feed">
      {/* ✅ Create Post Box */}
      <form className="create-box" onSubmit={handleCreatePost}>
        <input
          className="input"
          placeholder="What's on your mind?"
          value={caption}
          onChange={(e) => setCaption(e.target.value)}
        />

        <input
          className="input"
          placeholder="Image URL (optional)"
          value={imageUrl}
          onChange={(e) => setImageUrl(e.target.value)}
        />

        {/* ✅ Button always visible */}
        <button type="submit" className="primary-btn">
          Post
        </button>
      </form>

      {/* ✅ Posts */}
      {loading ? (
        <div className="muted">Loading...</div>
      ) : posts.length === 0 ? (
        <div className="muted">No posts yet</div>
      ) : (
        posts.map((p) => (
          <PostCard
            key={p.id}
            username={p?.user?.username || p?.username || "user"}
            caption={p.caption || ""}
            imageUrl={p.imageUrl}
          />
        ))
      )}
    </div>
  );
}