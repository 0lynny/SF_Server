package org.sfaas.SFaaS.global.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum SuccessType {

    /**
     * 200 OK (2000 ~ 2099)
     */
    OK(HttpStatus.OK, 2000, "성공");

    /**
     * 201 CREATED (2100 ~ 2199)
     */

    /**
     * 204 NO CONTENT (2400 ~ 2499)
     */

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}


