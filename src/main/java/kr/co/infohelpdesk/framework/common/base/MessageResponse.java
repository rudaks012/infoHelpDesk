package kr.co.infohelpdesk.framework.common.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 생성자를 만들어줌
public class MessageResponse {

    private String message;
    private String redirectUrl;

    public static MessageResponse save(String redirectUrl) {
        return new MessageResponse("글이 작성되었습니다.", redirectUrl);
    }

    public static MessageResponse updated(String redirectUrl) {
        return new MessageResponse("글이 수정되었습니다.", redirectUrl);
    }

    public static MessageResponse deleted(String redirectUrl) {
        return new MessageResponse("글이 삭제되었습니다.", redirectUrl);
    }

    public static MessageResponse replied(String redirectUrl) {
        return new MessageResponse("답글을 달았습니다.", redirectUrl);
    }

}
