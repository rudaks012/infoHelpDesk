importScripts("https://www.gstatic.com/firebasejs/5.9.2/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/5.9.2/firebase-messaging.js");

// Initialize Firebase
const firebaseConfig = {

};

firebase.initializeApp(firebaseConfig);

self.addEventListener('push', (event) => {
  const options = {
    body: event.data.json(),
  };
  const title = options.body.notification.title;
  const body = options.body.notification.body;
  console.log(title);
//   console.log(body);

  event.waitUntil(
      self.registration.showNotification(title, options.body.notification)
  );
});

const messaging = firebase.messaging();
