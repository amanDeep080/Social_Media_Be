import { Link, useNavigate } from "react-router-dom";
import { Heart, MessageCircle, PlusSquare, Search } from "lucide-react";
import { useEffect, useState } from "react";

const Header = () => {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    setIsLoggedIn(!!localStorage.getItem("token"));
  }, []);

  const logout = () => {
    localStorage.removeItem("token");
    setIsLoggedIn(false);
    navigate("/login");
  };

  return (
    <header className="w-full sticky top-0 z-50 bg-white/90 backdrop-blur border-b">
      <div className="max-w-6xl mx-auto px-4 h-14 flex items-center justify-between">
        <Link to="/" className="font-extrabold text-xl tracking-tight">
          Social<span className="text-gray-500">gram</span>
        </Link>

        <div className="hidden md:flex items-center gap-2 bg-gray-100 rounded-full px-3 py-2 w-[360px]">
          <Search size={18} className="text-gray-500" />
          <input
            className="bg-transparent outline-none w-full text-sm"
            placeholder="Search"
          />
        </div>

        <div className="flex items-center gap-2">
          {isLoggedIn ? (
            <>
              <button className="p-2 rounded-full hover:bg-gray-100 text-gray-800">
                <PlusSquare size={22} />
              </button>
              <button className="p-2 rounded-full hover:bg-gray-100 text-gray-800">
                <Heart size={22} />
              </button>
              <button className="p-2 rounded-full hover:bg-gray-100 text-gray-800">
                <MessageCircle size={22} />
              </button>

              <div className="w-8 h-8 rounded-full bg-gradient-to-tr from-pink-500 via-orange-400 to-yellow-300 p-[2px] ml-1">
                <div className="w-full h-full rounded-full bg-white flex items-center justify-center text-xs font-bold">
                  AK
                </div>
              </div>

              <button
                onClick={logout}
                className="ml-3 text-sm text-red-500 hover:text-red-600"
              >
                Logout
              </button>
            </>
          ) : (
            <div className="hidden md:flex items-center gap-3">
              <Link className="text-sm font-semibold hover:text-gray-600" to="/login">
                Login
              </Link>
              <Link
                className="text-sm font-semibold bg-black text-white px-3 py-1 rounded-full hover:opacity-90"
                to="/signup"
              >
                Signup
              </Link>
            </div>
          )}
        </div>
      </div>
    </header>
  );
};

export default Header;