package com.precourse.openMission.service;

import com.precourse.openMission.config.auth.dto.SessionUser;
import com.precourse.openMission.exception.RestApiException;
import com.precourse.openMission.exception.CustomErrorCode;
import org.springframework.transaction.annotation.Transactional;
import com.precourse.openMission.domain.user.User;
import com.precourse.openMission.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void deleteUser(SessionUser sessionUser) {
        if (sessionUser == null) {
            throw new RestApiException(CustomErrorCode.LOGIN_REQUIRED);
        }
        User user = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new RestApiException(CustomErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }
}
