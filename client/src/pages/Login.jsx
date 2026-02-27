import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import api from "../api/axios";
import { setToken } from "../utils/auth";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [msg, setMsg] = useState("");
  const nav = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    setMsg("");

    try {
      const res = await api.post("/auth/login", { email, password });
      setToken(res.data.token);
      nav("/home");
    } catch (err) {
      setMsg(err?.response?.data?.message || "Login failed");
    }
  };

  return (
    <div style={{ maxWidth: 420, margin: "60px auto" }}>
      <h2>Login</h2>
      {msg && <p style={{ color: "crimson" }}>{msg}</p>}

      <form onSubmit={submit}>
        <input
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          style={{ width: "100%", padding: 10, margin: "8px 0" }}
        />
        <input
          placeholder="Password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={{ width: "100%", padding: 10, margin: "8px 0" }}
        />
        <button style={{ width: "100%", padding: 10 }}>Login</button>
      </form>

      <p style={{ marginTop: 12 }}>
        New user? <Link to="/register">Register</Link>
      </p>
    </div>
  );
}