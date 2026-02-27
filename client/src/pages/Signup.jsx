import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import api from "../api/axios";

const Signup = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    fullName: "",
    username: "",
    email: "",
    password: "",
  });

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/auth/register", form);
      localStorage.setItem("token", res.data.token);
      navigate("/");
      window.location.reload();
    } catch (err) {
      alert(err.response?.data?.message || "Signup failed ❌");
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center px-4 pb-24">
      <div className="w-full max-w-md bg-white border rounded-2xl p-6 shadow-sm">
        <h1 className="text-2xl font-extrabold text-center">
          Social<span className="text-gray-500">gram</span>
        </h1>
        <p className="text-center text-sm text-gray-500 mt-2">Create your account</p>

        <form onSubmit={handleSubmit} className="mt-6 space-y-3">
          <input name="fullName" value={form.fullName} onChange={handleChange}
            className="w-full border rounded-xl px-3 py-2 text-sm outline-none" placeholder="Full Name" />
          <input name="username" value={form.username} onChange={handleChange}
            className="w-full border rounded-xl px-3 py-2 text-sm outline-none" placeholder="Username" />
          <input name="email" value={form.email} onChange={handleChange}
            className="w-full border rounded-xl px-3 py-2 text-sm outline-none" placeholder="Email" />
          <input type="password" name="password" value={form.password} onChange={handleChange}
            className="w-full border rounded-xl px-3 py-2 text-sm outline-none" placeholder="Password" />

          <button className="w-full bg-black text-white rounded-xl py-2 text-sm font-semibold hover:opacity-90">
            Signup
          </button>
        </form>

        <div className="mt-5 text-center text-sm text-gray-600">
          Already have an account?{" "}
          <Link className="font-semibold text-blue-500 hover:text-blue-600" to="/login">
            Login
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Signup;