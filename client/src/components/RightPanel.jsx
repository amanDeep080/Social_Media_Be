const suggestions = [
  { id: 1, name: "mansi123", note: "Suggested for you" },
  { id: 2, name: "rohan.dev", note: "Followed by aman" },
  { id: 3, name: "simran.codes", note: "New to Socialgram" },
  { id: 4, name: "arjun.fit", note: "Popular" },
  { id: 5, name: "neha.design", note: "Suggested for you" },
];

const RightPanel = () => {
  return (
    <aside className="hidden lg:block w-full">
      <div className="sticky top-20 space-y-4">
        <div className="bg-white border rounded-2xl p-4">
          <div className="flex items-center gap-3">
            <div className="w-12 h-12 rounded-full bg-gray-200 flex items-center justify-center font-bold">
              AK
            </div>
            <div className="flex-1">
              <div className="font-semibold text-sm">amanmansi123</div>
              <div className="text-xs text-gray-500">Amandeep Kumar</div>
            </div>
            <button className="text-blue-500 text-sm font-semibold hover:text-blue-600">
              Switch
            </button>
          </div>
        </div>

        <div className="bg-white border rounded-2xl p-4">
          <div className="flex items-center justify-between mb-3">
            <div className="text-sm font-semibold text-gray-600">
              Suggested for you
            </div>
            <button className="text-xs font-semibold hover:text-gray-600">
              See All
            </button>
          </div>

          <div className="space-y-3">
            {suggestions.map((s) => (
              <div key={s.id} className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-full bg-gray-200 flex items-center justify-center text-xs font-bold">
                  {s.name.slice(0, 2).toUpperCase()}
                </div>
                <div className="flex-1">
                  <div className="text-sm font-semibold">{s.name}</div>
                  <div className="text-xs text-gray-500">{s.note}</div>
                </div>
                <button className="text-blue-500 text-sm font-semibold hover:text-blue-600">
                  Follow
                </button>
              </div>
            ))}
          </div>
        </div>

        <div className="text-xs text-gray-400 px-2">
          About • Help • Press • API • Jobs • Privacy • Terms
        </div>
      </div>
    </aside>
  );
};

export default RightPanel;