package com.precourse.openMission.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),

    INVALID_USER(HttpStatus.BAD_REQUEST, "권한이 유효하지 않은 사용자입니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다."),
    MEMO_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 메모가 존재하지 않습니다."),

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 파라미터 요청입니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "자원이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
