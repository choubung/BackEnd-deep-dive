package com.precourse.openMission.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;

/**
 * 모든 컨트롤러(@Controller)의 Model에
 * 공통 속성(Attribute)을 추가하는 클래스
 */
@ControllerAdvice(annotations = Controller.class)
public class GlobalModelAdvice {
    @ModelAttribute("_csrf")
    public CsrfToken csrfToken(HttpServletRequest request) {
        // Spring Security가 이미 request에 저장해 둔 _csrf 토큰을 꺼내서 Model에 담아줌
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
