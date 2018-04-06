importScripts('/css/js/serviceworker-cachepolyfill.js');

self.addEventListener('install', function(e) {
    e.waitUntil(
        caches.open('CleanStream').then(function(cache) {
            return cache.addAll([
                '/',
                '/css/css/bootstrap.css',
                '/css/js/bootstrap.js',
                '/css/master.css',
                '/css/js/jquery.js'
            ]);
        })
    );
});

self.addEventListener('fetch', function(event) {

    console.log(event.request.url);

    event.respondWith(

        caches.match(event.request).then(function(response) {

            return response || fetch(event.request);

        })

    );

});

