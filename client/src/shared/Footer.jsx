import { Link, useLocation } from "react-router-dom";
import { Home, Search, PlusSquare, Heart, User } from "lucide-react";

const Footer = () => {
  const { pathname } = useLocation();

  const Item = ({ to, children }) => (
    <Link
      to={to}
      className={`flex-1 flex justify-center py-3 ${
        pathname === to ? "text-black" : "text-gray-500"
      }`}
    >
      {children}
    </Link>
  );

  return (
    <footer className="md:hidden fixed bottom-0 left-0 right-0 bg-white border-t z-50">
      <div className="flex items-center">
        <Item to="/"><Home size={24} /></Item>
        <Item to="/search"><Search size={24} /></Item>
        <Item to="/create"><PlusSquare size={24} /></Item>
        <Item to="/activity"><Heart size={24} /></Item>
        <Item to="/profile"><User size={24} /></Item>
      </div>
    </footer>
  );
};

export default Footer;