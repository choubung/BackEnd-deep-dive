package com.precourse.openMission.web;

import com.precourse.openMission.config.auth.LoginUser;
import com.precourse.openMission.config.auth.dto.SessionUser;
import com.precourse.openMission.domain.memo.MemoScope;
import com.precourse.openMission.service.MemoService;
import com.precourse.openMission.web.dto.memo.MemoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MemoService memoService;

    /**
     * 메인 페이지 (목록 조회)
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("memos", memoService.findAllDesc(user));

        if (user != null) {
            model.addAttribute("googleName", user.getName());
        }

        return "index";
    }

    /**
     * 메모 등록 페이지
     */
    @GetMapping("/home/memos/save")
    public String memosSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("googleName", user.getName());
        }
        return "memos-save";
    }

    /**
     * 메모 상세 조회 페이지
     */
    @GetMapping("/home/memos/detail/{memoId}")
    public String memosDetail(@PathVariable Long memoId, Model model, @LoginUser SessionUser user) {
        MemoResponseDto dto = memoService.findById(memoId, user);

        model.addAttribute("memo", dto);

        if (user != null) {
            model.addAttribute("googleName", user.getName());
        }

        return "memos-detail";
    }


    /**
     * 메모 수정 페이지
     */
    @GetMapping("/home/memos/update/{id}")
    public String memosUpdate(@PathVariable Long id, @LoginUser SessionUser user, Model model) {

        MemoResponseDto dto = memoService.findById(id, user);
        model.addAttribute("memo", dto);

        if (user != null) {
            model.addAttribute("googleName", user.getName());
        }

        if (dto.getScope() == MemoScope.PUBLIC) {
            model.addAttribute("isPublic", true);
        } else {
            model.addAttribute("isSecret", true);
        }

        if (dto.getMemoDate() != null) {
            model.addAttribute("memoDateFormatted",
                    dto.getMemoDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        }

        return "memos-update";
    }
}