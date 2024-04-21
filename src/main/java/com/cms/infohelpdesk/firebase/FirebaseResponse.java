package com.cms.infohelpdesk.firebase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
// 메세지와 코드를 같이 보내기 위해 사용
public class FirebaseResponse<T> {

    private Integer code;
    private String message;
    private T data;
}
