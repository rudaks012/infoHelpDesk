package kr.co.infohelpdesk.framework.firebase;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/firebase")
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/sendNotification")
    public ResponseEntity<?> sendNotification(@Valid @RequestBody FirebaseRequest dto) {

        // 테스트로 한 번 찍어본다.
        System.out.println("테스트 : " + dto.getNotification().getToken());
        System.out.println("테스트 : " + dto.getNotification().getTitle());
        System.out.println("테스트 : " + dto.getNotification().getBody());

        return firebaseService.sendNotification(dto);
    }

}
