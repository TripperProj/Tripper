package com.tripper.controller;

import com.tripper.domain.user.UserInfo;
import com.tripper.dto.UserInfoDto;
import com.tripper.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author HanJiyoung
 * 회원 기능 관련 컨트롤러 클래스
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @ApiOperation(
            value = "회원가입"
            , notes = "회원가입 폼에 입력한 정보로 회원가입을 실행한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String signup(@RequestBody @ApiParam(value = "폼에 입력한 정보를 담고있는 객체") UserInfoDto dto) throws Exception {

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

    @ApiOperation(
            value = "로그아웃"
            , notes = "로그아웃을 실행한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());
        return "redirect:/login";
    }

    @ApiOperation(
            value = "회원 목록 조회"
            , notes = "db에서 회원 목록을 가져온 후 뷰페이지로 넘겨준다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/list")
    public String getUserList(Model model) {
        List<UserInfo> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

}