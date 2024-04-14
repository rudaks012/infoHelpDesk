let firebaseConfig = {

};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();
// RequestPermission 첫 어플 시작 시 알림 허용 or 불허를 사용자에게 안내합니다.
messaging
.requestPermission()
.then(function () {
  // 알람이 허용되었을 때 토큰을 반환합니다.
  return messaging.getToken();
})
.then(async function (token) {
  console.log(token);
  messaging.onMessage((payload) => {
    console.log(payload);
  });
});

const sendNotification = () => {
  const reqFcmNotificationDTO = {
    notification: {
      token: document.querySelector("#token").value,
      title: document.querySelector("#title").value,
      body: document.querySelector("#body").value,
    },
  };

  console.log(reqFcmNotificationDTO.notification);

  fetch("/api/v1/firebase/sendNotification", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(reqFcmNotificationDTO),
  })
  .then((response) => response.json())
  .then((data) => {
    console.log(data);
    alert(data.message);
    if (data.code === 0) {
      location.reload();
    }
  })
  .catch((error) => {
    console.error("Error:", error);
  });
};
