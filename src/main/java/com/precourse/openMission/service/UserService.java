package com.precourse.openMission.service;

import com.precourse.openMission.config.auth.dto.SessionUser;
import com.precourse.openMission.domain.memo.MemoRepository;
import com.precourse.openMission.domain.user.User;
import com.precourse.openMission.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void deleteUser(SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        userRepository.delete(user);
    }
}
