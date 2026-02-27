import { Heart, MessageCircle, Send, Bookmark } from "lucide-react";
import { useState } from "react";

const PostCard = ({ post }) => {
  const [liked, setLiked] = useState(false);

  return (
    <div className="bg-white border rounded-2xl overflow-hidden">
      <div className="flex items-center justify-between p-4">
        <div className="flex items-center gap-3">
          <div className="w-10 h-10 rounded-full bg-gradient-to-tr from-pink-500 via-orange-400 to-yellow-300 p-[2px]">
            <div className="w-full h-full rounded-full bg-white flex items-center justify-center font-bold text-xs">
              {post.user.name.slice(0, 2).toUpperCase()}
            </div>
          </div>
          <div>
            <div className="font-semibold text-sm">{post.user.name}</div>
            <div className="text-xs text-gray-500">{post.location}</div>
          </div>
        </div>
        <button className="px-2 py-1 rounded-lg hover:bg-gray-100 text-gray-500 hover:text-black">
          •••
        </button>
      </div>

      <div className="w-full bg-gray-100">
        <img
          src={post.imageUrl}
          alt="post"
          className="w-full max-h-[520px] object-cover"
          onError={(e) => {
            e.currentTarget.src = "https://picsum.photos/seed/fallback/1200/900";
          }}
          loading="lazy"
        />
      </div>

      <div className="px-4 pt-3">
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2">
            <button
              onClick={() => setLiked(!liked)}
              className={`p-2 rounded-full hover:bg-gray-100 ${
                liked ? "text-red-500" : "text-gray-900"
              }`}
              title="Like"
            >
              <Heart size={24} fill={liked ? "currentColor" : "none"} />
            </button>

            <button
              className="p-2 rounded-full hover:bg-gray-100 text-gray-900"
              title="Comment"
            >
              <MessageCircle size={24} />
            </button>

            <button
              className="p-2 rounded-full hover:bg-gray-100 text-gray-900"
              title="Share"
            >
              <Send size={24} />
            </button>
          </div>

          <button
            className="p-2 rounded-full hover:bg-gray-100 text-gray-900"
            title="Save"
          >
            <Bookmark size={24} />
          </button>
        </div>

        <div className="mt-2 text-sm font-semibold">
          {(post.likes + (liked ? 1 : 0)).toLocaleString()} likes
        </div>

        <div className="mt-1 text-sm">
          <span className="font-semibold">{post.user.name}</span>{" "}
          <span className="text-gray-800">{post.caption}</span>
        </div>

        <button className="mt-2 text-sm text-gray-500 hover:text-gray-700">
          View all {post.comments} comments
        </button>

        <div className="mt-3 border-t py-3 flex gap-3">
          <input className="flex-1 outline-none text-sm" placeholder="Add a comment..." />
          <button className="text-sm font-semibold text-blue-500 hover:text-blue-600">
            Post
          </button>
        </div>
      </div>
    </div>
  );
};

export default PostCard;