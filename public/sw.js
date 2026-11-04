const CACHE = "freewave-shell-v5";
const SHELL = ["./", "./index.html", "./manifest.webmanifest", "./icon.svg"];

self.addEventListener("install", event => {
  event.waitUntil(caches.open(CACHE).then(cache => cache.addAll(SHELL)).then(() => self.skipWaiting()));
});

self.addEventListener("activate", event => {
  event.waitUntil(
    caches.keys()
      .then(keys => Promise.all(keys.filter(key => key !== CACHE).map(key => caches.delete(key))))
      .then(() => self.clients.claim())
  );
});

self.addEventListener("fetch", event => {
  const request = event.request;
  if (request.method !== "GET") return;
  const url = new URL(request.url);
  if (url.origin !== self.location.origin) return;

  const name = url.pathname.split("/").pop();
  if (request.mode === "navigate" || name === "index.html") {
    event.respondWith(
      fetch(request, {cache:"no-store"})
        .then(response => {
          if (response.ok) {
            const copy = response.clone();
            caches.open(CACHE).then(cache => cache.put(request, copy));
          }
          return response;
        })
        .catch(() => caches.match(request).then(cached => cached || Response.error()))
    );
    return;
  }

  const liveData = name === "metrics.json" || name === "history.json" || name === "health.json" || name === "STATUS.md" || name.startsWith("freewave-");
  if (liveData) {
    event.respondWith(
      fetch(request, {cache:"no-store"})
        .then(response => {
          if (response.ok) {
            const copy = response.clone();
            const cacheKey = new Request(url.origin + url.pathname);
            caches.open(CACHE).then(cache => cache.put(cacheKey, copy));
          }
          return response;
        })
        .catch(() => {
          const cacheKey = new Request(url.origin + url.pathname);
          return caches.match(cacheKey).then(cached => cached || Response.error());
        })
    );
    return;
  }

  event.respondWith(
    caches.match(request).then(cached => cached || fetch(request).then(response => {
      if (response.ok && response.type === "basic") {
        const copy = response.clone();
        caches.open(CACHE).then(cache => cache.put(request, copy));
      }
      return response;
    }))
  );
});