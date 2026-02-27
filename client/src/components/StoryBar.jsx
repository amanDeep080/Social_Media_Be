const stories = [
  "aman",
  "mansi",
  "rohan",
  "simran",
  "kiran",
  "tanya",
  "arjun",
  "neha",
  "raj",
  "sakshi",
].map((name, i) => ({ id: i + 1, name }));

const StoryBar = () => {
  return (
    <div className="bg-white border rounded-2xl p-3 overflow-x-auto">
      <div className="flex gap-4">
        {stories.map((s) => (
          <div key={s.id} className="flex flex-col items-center min-w-[64px]">
            <div className="w-16 h-16 rounded-full bg-gradient-to-tr from-pink-500 via-orange-400 to-yellow-300 p-[2px]">
              <div className="w-full h-full rounded-full bg-white flex items-center justify-center font-bold text-sm">
                {s.name.slice(0, 2).toUpperCase()}
              </div>
            </div>
            <div className="text-xs mt-1 text-gray-600 truncate w-16 text-center">
              {s.name}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default StoryBar;