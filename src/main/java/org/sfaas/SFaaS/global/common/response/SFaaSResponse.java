package org.sfaas.SFaaS.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@JsonPropertyOrder({"status", "code", "message", "data"})
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SFaaSResponse<T> {
    private final int status;
    private final int code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static SFaaSResponse<?> success(SuccessType successType) {
        return new SFaaSResponse<>(successType.getHttpStatusCode(), successType.getCode(), successType.getMessage());
    }

    public static <T> SFaaSResponse<T> success(SuccessType successType, T data) {
        return new SFaaSResponse<>(successType.getHttpStatusCode(), successType.getCode(), successType.getMessage(), data);
    }

    public static SFaaSResponse<?> error(ErrorType errorType) {
        return new SFaaSResponse<>(errorType.getHttpStatusCode(), errorType.getCode(), errorType.getMessage());
    }

    public static <T> SFaaSResponse<T> error(ErrorType errorType, T data) {
        return new SFaaSResponse<>(errorType.getHttpStatusCode(), errorType.getCode(), errorType.getMessage(), data);
    }

    public static SFaaSResponse<?> error(ErrorType errorType, String message) {
        return new SFaaSResponse<>(errorType.getHttpStatusCode(), errorType.getCode(), message);
    }

    public static <T> SFaaSResponse<T> error(ErrorType errorType, String message, T data) {
        return new SFaaSResponse<>(errorType.getHttpStatusCode(), errorType.getCode(), message, data);
    }

    public static <T> SFaaSResponse<Exception> error(ErrorType errorType, Exception e) {
        return new SFaaSResponse<>(errorType.getHttpStatusCode(), errorType.getCode(), errorType.getMessage(), e);
    }

}
