package kr.co.infohelpdesk.framework.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FirebaseInitializer {

    // 패스에 firebase 콘솔에서 발급받은 비공개 키 파일 이름을 적어준다.
    // 비공개 키 파일이 resources에 바로 밑에 있어야 정상 작동 하는 걸로 알고있다.
    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);
    private static final String path = "firebase-adminsdk-d5db6f1507.json";
    private boolean initialized = false;

    @PostConstruct
    public void init() {
        try {
            if (!initialized) {
                // 위에서 입력한 path를 기반으로 인증 정보 세팅
                // 여기서 deprecated가 뜨지만, 실행은 된다.
                FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(path).getInputStream()))
                    .build();

                // 이미 초기화가 되어있는 지 확인 후
                if (FirebaseApp.getApps().isEmpty()) {
                    // 비어있다면 초기화
                    FirebaseApp.initializeApp(options);
                    initialized = true;
                    logger.info("Firebase 애플리케이션이 초기화되었습니다.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
