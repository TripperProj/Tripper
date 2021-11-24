package com.tripper.controller;

import com.tripper.domain.user.UserInfo;
import com.tripper.dto.UserInfoDto;
import com.tripper.service.MemberService;
import com.tripper.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author HanJiyoung
 * 회원 관련 컨트롤러 클래스
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {

    private final UserService userService;
    private final MemberService memberService;

    /**
     * 회원가입을 실행하는 함수
     * @param dto 폼에서 입력했던 데이터를 담고있는 객체
     * @return 로그인 페이지로 리다이렉트
     * @throws Exception
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String signup(@RequestBody UserInfoDto dto) throws Exception {

        UserInfoDto infoDto = new UserInfoDto();
        infoDto.setMemId(dto.getMemId());
        infoDto.setPassword(dto.getPassword());
        infoDto.setName(dto.getName());
        infoDto.setPhone(dto.getPhone());
        infoDto.setEmail(dto.getEmail());
        infoDto.setNickname(dto.getNickname());
        infoDto.setAuth(dto.getAuth());
        userService.save(infoDto);

        return "redirect:/";
    }

    /**
     * 로그아웃을 실행하는 함수
     * @param request
     * @param response
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());
        return "redirect:/login";
    }

    /**
     * 회원 목록을 가져온 후 model에 담아서 뷰페이지로 넘겨주는 함수
     * @param model 뷰로 넘겨줄 모델 객체
     * @return 회원 목록 페이지
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/list")
    public String getUserList(Model model) {
        List<UserInfo> users = memberService.findAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

}