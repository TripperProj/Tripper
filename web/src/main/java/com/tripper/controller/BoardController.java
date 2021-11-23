package com.tripper.controller;

import com.tripper.domain.board.BoardForm;
import com.tripper.domain.board.BoardInfo;
import com.tripper.domain.board.BoardStatus;
import com.tripper.domain.user.UserInfo;
import com.tripper.service.BoardService;
import com.tripper.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/board/create")
    public String goBoardForm(Authentication authentication, Model model) {
        /* 현재 로그인한 사용자 정보 가져오기 */
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        String username = userDetails.getUsername();

        List<UserInfo> users = userService.findByMemId(username);
        UserInfo user = users.get(0);
        model.addAttribute("user", user);

        return "boardForm";
    }

    @PostMapping("/board/create")
    public String create(@Valid BoardForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "boardForm";
        }

        /* 현재 로그인한 사용자 정보 가져오기 */
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String memId = ((UserDetails) principal).getUsername();
        log.info("현재 사용자 아이디: " + memId);

        boardService.registerPost(form, memId);
        return "redirect:/";
    }
}
