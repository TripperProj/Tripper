package com.tripper.controller;

import com.tripper.domain.user.Role;
import com.tripper.domain.user.UserInfo;
import com.tripper.dto.EmailDto;
import com.tripper.dto.UserInfoDto;
import com.tripper.service.EmailService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @ApiOperation(
            value = "회원가입"
            , notes = "회원가입 폼에 입력한 정보로 회원가입을 실행한다.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "ok: 회원가입 성공.") })
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String signup(@RequestBody @ApiParam(value = "회원가입 폼에 입력한 정보를 담고있는 객체") UserInfoDto dto) {

        UserInfoDto infoDto = new UserInfoDto();
        infoDto.setMemId(dto.getMemId());
        infoDto.setPassword(dto.getPassword());
        infoDto.setName(dto.getName());
        infoDto.setPhone(dto.getPhone());
        infoDto.setEmail(dto.getEmail());
        infoDto.setNickname(dto.getNickname());
        infoDto.setAuth(Role.ROLE_NOTCERTIFIED);
        userService.save(infoDto);

        return "ok";
    }

    @ApiOperation(
            value = "아이디 중복 체크"
            , notes = "회원가입 폼에서 아이디 중복 체크를 실행한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok: 사용 가능한 아이디입니다. \t\n fail: 이미 사용중인 아이디입니다.")})
    @GetMapping(value = "/checkExists")
    public String checkMemIdExists(@ApiParam(value = "중복체크를 실행할 아이디") @Param("memId") String memId) {

        UserInfo userInfo = new UserInfo();
        userInfo = userService.checkMemIdExists(memId);

        if(userInfo != null) { // 아이디 중복이면
            return "fail";
        }

        return "ok";
    }

    @ApiOperation(
            value = "로그아웃"
            , notes = "로그아웃을 실행한다.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "ok: 로그아웃 성공.") })
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());

        return "ok";
    }

    @ApiOperation(
            value = "회원 목록 조회"
            , notes = "db에서 회원 목록을 가져온 후 뷰페이지로 넘겨준다.")
    @GetMapping("/list")
    public List<UserInfo> getUserList() {

        List<UserInfo> users = userService.findAllUsers();

        return users;

    }

}