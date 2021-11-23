package com.tripper.controller;

import com.tripper.dto.UserInfoDto;
import com.tripper.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author HanJiyoung
 * 회원 관련 컨트롤러 클래스
 */
@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @CrossOrigin(origins = "http://192.168.0.64:8081/", maxAge = 3600)
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
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());
        return "redirect:/login";
    }

}