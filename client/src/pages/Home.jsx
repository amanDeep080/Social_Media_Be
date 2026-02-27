import { useEffect, useState } from "react";
import api from "../api/axios";
import { clearToken } from "../utils/auth";
import { useNavigate } from "react-router-dom";

export default function Home() {
  const [posts, setPosts] = useState([]);
  const [users, setUsers] = useState([]);
  const [content, setContent] = useState("");
  const [imageFile, setImageFile] = useState(null);
  const [msg, setMsg] = useState("");
  const nav = useNavigate();

  // ✅ Load posts (likedByMe accurate)
  const loadPosts = async () => {
    const res = await api.get("/posts/me");
    setPosts(res.data);
  };

  // ✅ Load users + follow status (Follow/Unfollow realtime)
  const loadUsers = async () => {
    const res = await api.get("/users");

    const withStatus = await Promise.all(
      res.data.map(async (u) => {
        const st = await api.get(`/users/${u.id}/is-following`);
        return { ...u, isFollowing: st.data.active };
      })
    );

    setUsers(withStatus);
  };

  useEffect(() => {
    loadPosts();
    loadUsers();
  }, []);

  const logout = () => {
    clearToken();
    nav("/login");
  };

  // ✅ Upload image (optional)
  const uploadImageIfAny = async () => {
    if (!imageFile) return null;

    const form = new FormData();
    form.append("file", imageFile);

    const res = await api.post("/upload/image", form, {
      headers: { "Content-Type": "multipart/form-data" },
    });

    return res.data.imageUrl;
  };

  // ✅ Create post
  const createPost = async () => {
    setMsg("");

    if (!content.trim()) {
      setMsg("Content required");
      return;
    }

    try {
      const imageUrl = await uploadImageIfAny();

      await api.post("/posts", {
        content: content.trim(),
        imageUrl: imageUrl || null,
      });

      setContent("");
      setImageFile(null);

      await loadPosts(); // create ke baad reload ok
    } catch (err) {
      setMsg(err?.response?.data?.message || "Create post failed");
    }
  };

  // ✅ LIKE toggle (no full reload)
  const toggleLike = async (postId) => {
    try {
      const res = await api.post(`/posts/${postId}/like`);
      const active = res.data.active;

      setPosts((prev) =>
        prev.map((p) =>
          p.id === postId
            ? {
                ...p,
                likedByMe: active,
                likeCount: active ? p.likeCount + 1 : Math.max(0, p.likeCount - 1),
              }
            : p
        )
      );
    } catch (err) {
      alert(err?.response?.data?.message || "Like failed");
    }
  };

  // ✅ COMMENT add (no full reload; only count++)
  const addComment = async (postId, text) => {
    if (!text.trim()) return false;

    try {
      await api.post(`/comments/post/${postId}`, { content: text.trim() });

      setPosts((prev) =>
        prev.map((p) =>
          p.id === postId ? { ...p, commentCount: p.commentCount + 1 } : p
        )
      );

      return true;
    } catch (err) {
      alert(err?.response?.data?.message || "Comment failed");
      return false;
    }
  };

  // ✅ FOLLOW toggle (UI instantly updates Follow/Unfollow)
  const toggleFollow = async (userId) => {
    try {
      const res = await api.post(`/users/${userId}/follow`);
      const active = res.data.active;

      setUsers((prev) =>
        prev.map((u) => (u.id === userId ? { ...u, isFollowing: active } : u))
      );
    } catch (err) {
      alert(err?.response?.data?.message || "Follow failed");
    }
  };

  return (
    <div
      style={{
        maxWidth: 1100,
        margin: "30px auto",
        display: "grid",
        gridTemplateColumns: "2fr 1fr",
        gap: 20,
      }}
    >
      {/* LEFT: FEED */}
      <div>
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <h2>Home</h2>
          <button onClick={logout}>Logout</button>
        </div>

        {msg && <p style={{ color: "crimson" }}>{msg}</p>}

        {/* Create Post */}
        <div style={{ border: "1px solid #ddd", padding: 12, borderRadius: 10 }}>
          <textarea
            placeholder="What's happening?"
            value={content}
            onChange={(e) => setContent(e.target.value)}
            rows={3}
            style={{ width: "100%", padding: 10 }}
          />
          <input
            type="file"
            accept="image/*"
            onChange={(e) => setImageFile(e.target.files?.[0] || null)}
            style={{ marginTop: 8 }}
          />
          <button onClick={createPost} style={{ marginTop: 8 }}>
            Post
          </button>
        </div>

        {/* Posts */}
        <div style={{ marginTop: 20 }}>
          {posts.map((p) => (
            <PostCard
              key={p.id}
              post={p}
              onLike={() => toggleLike(p.id)}
              onComment={addComment}
            />
          ))}
        </div>
      </div>

      {/* RIGHT: SUGGESTIONS */}
      <div style={{ border: "1px solid #eee", borderRadius: 10, padding: 12, height: "fit-content" }}>
        <h3>Suggestions</h3>

        {users.length === 0 && <p style={{ color: "#666" }}>No users yet</p>}

        {users.map((u) => (
          <div
            key={u.id}
            style={{
              display: "flex",
              justifyContent: "space-between",
              alignItems: "center",
              padding: "10px 0",
              borderBottom: "1px solid #f3f3f3",
            }}
          >
            <div>
              <div style={{ fontWeight: 600 }}>{u.fullName}</div>
              <div style={{ color: "#666" }}>@{u.username}</div>
            </div>

            <button onClick={() => toggleFollow(u.id)}>
              {u.isFollowing ? "Unfollow" : "Follow"}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

// ✅ PostCard component
function PostCard({ post, onLike, onComment }) {
  const [commentText, setCommentText] = useState("");
  const [comments, setComments] = useState([]);

  const loadComments = async () => {
    const res = await api.get(`/comments/post/${post.id}`);
    setComments(res.data);
  };

  useEffect(() => {
    loadComments();
  }, [post.id]);

  const submitComment = async () => {
    const ok = await onComment(post.id, commentText);
    if (ok) {
      setCommentText("");
      await loadComments(); // ✅ only this post's comments reload
    }
  };

  return (
    <div style={{ border: "1px solid #eee", padding: 12, borderRadius: 10, marginTop: 12 }}>
      <div style={{ fontWeight: 600 }}>
        {post.fullName} <span style={{ color: "#666" }}>@{post.username}</span>
      </div>

      <p style={{ marginTop: 8 }}>{post.content}</p>

      {post.imageUrl && (
        <img src={post.imageUrl} alt="" style={{ width: "100%", borderRadius: 10 }} />
      )}

      <div style={{ display: "flex", gap: 10, marginTop: 10 }}>
        <button onClick={onLike}>
          {post.likedByMe ? "💔 Unlike" : "❤️ Like"} ({post.likeCount})
        </button>
        <span>💬 {post.commentCount}</span>
      </div>

      <div style={{ marginTop: 10 }}>
        <input
          placeholder="Write a comment..."
          value={commentText}
          onChange={(e) => setCommentText(e.target.value)}
          style={{ width: "100%", padding: 10 }}
        />
        <button onClick={submitComment} style={{ marginTop: 8 }}>
          Comment
        </button>

        <div style={{ marginTop: 10 }}>
          {comments.map((c) => (
            <div key={c.id} style={{ padding: "6px 0", borderBottom: "1px solid #f3f3f3" }}>
              <b>@{c.username}</b>: {c.content}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}