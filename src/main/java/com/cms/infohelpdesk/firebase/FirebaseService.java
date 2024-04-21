package com.cms.infohelpdesk.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    @Autowired
    FirebaseInitializer init;

    public ResponseEntity<?> sendNotification(FirebaseRequest dto) {
        try {
            // Firebase Admin SDK 초기화
            init.init();

            // 메시지 생성
            Message message = Message.builder()
                                     .setToken(dto.getNotification().getToken()) // 수신자의 FCM 토큰
                                     .setNotification(Notification.builder()
                                                                  .setTitle(dto.getNotification().getTitle()) // 알림 제목
                                                                  .setBody(dto.getNotification().getBody()) // 알림 내용
                                                                  .build())
                                     .build();

            // 알림 보내기
            FirebaseMessaging.getInstance().send(message);

            // 성공 시 성공 메시지 반환
            // 반환 시 ResponseEntity로 데이터와 같이 HttpStatus로 응답 상황도 같이 설정
            // 데이터도 DTO로 만들어서 보냄.(추후 구현 예정)
            return new ResponseEntity<>(
                FirebaseResponse.builder()
                                .code(0)
//                                .message("메시지 송신 성공")
                                .build(),
                HttpStatus.OK);
        } catch (Exception e) {

            // 실패 시 오류 로그 찍고
            e.printStackTrace();
            // 메시지 송신 실패 메시지 반환
            return new ResponseEntity<>(
                FirebaseResponse.builder()
                                .code(1)
//                                .message("Failed to send message: " + e.getMessage())
                                .build(),
                HttpStatus.BAD_REQUEST);
        }
    }

}
