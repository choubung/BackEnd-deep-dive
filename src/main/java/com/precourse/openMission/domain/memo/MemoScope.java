package com.precourse.openMission.domain.memo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemoScope {
    ONLY_ME("나만보기"),
    EVERYONE("전체공개");

    private final String scope;
}
