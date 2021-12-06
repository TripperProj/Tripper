package com.tripper.controller;

import com.tripper.domain.user.Role;
import com.tripper.domain.user.User;
import com.tripper.dto.request.user.CreateEmailDto;
import com.tripper.dto.request.user.CreateUserDto;
import com.tripper.dto.request.user.UpdateUserDto;
import com.tripper.dto.response.user.GetUserListDto;
import com.tripper.service.EmailService;
import com.tripper.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "회원 API")
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
    public String signup(@RequestBody @ApiParam(value = "회원가입 폼에 입력한 정보를 담고있는 객체") CreateUserDto dto) {

        dto.setAuth(Role.ROLE_NOTCERTIFIED);
        userService.save(dto);

        return "ok";
    }

    @ApiOperation(
            value = "아이디 중복 체크"
            , notes = "회원가입 폼에서 아이디 중복 체크를 실행한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok: 사용 가능한 아이디입니다. \t\n fail: 이미 사용중인 아이디입니다.")})
    @GetMapping(value = "/checkExists")
    public String checkMemIdExists(@ApiParam(value = "중복체크를 실행할 아이디") @Param("memId") String memId) {

        String rslt = userService.checkMemIdExists(memId);

        return rslt;
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
    public ResponseEntity<GetUserListDto> getUserList() {

        GetUserListDto users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);

    }

    @ApiOperation(
            value = "이메일 인증"
            , notes = "사용자 db에 저장돼있는 이메일로 인증 코드를 전송한다.")
    @PostMapping("/sendEmail")
    public @ResponseBody void sendEmail(Authentication authentication) {

        /* 현재 로그인한 사용자 정보 가져오기 */
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        log.info("getusername: " + userDetails.getUsername());
        User user = userService.findUserByMemId(userDetails.getUsername());

        /* 해당 사용자의 db에 저장돼있는 이메일로 메일 전송 */
        CreateEmailDto dto = emailService.createMailAndChangeAuth(user.getEmail(), user.getMemId());
        emailService.mailSend(dto);

    }

    @ApiOperation(
            value = "이메일 인증 코드 일치 확인"
            , notes = "입력한 이메일 인증 코드의 일치 여부를 확인한다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ok: 이메일 인증 완료.")})
    @GetMapping(value = "/checkEmailAuthCode/{authCode}")
    public String checkEmailAuthCode(Authentication authentication,
                                     @ApiParam(value = "입력한 인증 코드") @PathVariable("authCode") String authCode) {
        log.info("인증코드: " + authCode);

        /* 현재 로그인한 사용자 정보 가져오기 */
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findUserByMemId(userDetails.getUsername());

        /* 일치 여부 확인 */
        String rslt = emailService.checkEmailAuthCode(authCode, user.getMemId());

        return rslt;
    }

    @ApiOperation(
            value = "회원 정보 수정"
            , notes = "회원 정보 수정을 실행한다.")
    @PostMapping("/{user_id}/update")
    public ResponseEntity updateUser(@ApiParam(value = "수정할 회원의 고유 id") @PathVariable("user_id") Long user_id,
                                     @ApiParam(value = "회원 정보 수정 폼에 입력한 정보를 갖고있는 객체") @RequestBody UpdateUserDto updateUserDto) {

        userService.updateUser(user_id, updateUserDto);
        return ResponseEntity.ok().build();

    }

    @ApiOperation(
            value = "회원 탈퇴"
            , notes = "회원 정보 삭제를 실행한다.")
    @GetMapping("/{user_id}/delete")
    public ResponseEntity deleteUser(@ApiParam(value = "삭제할 회원의 고유 id") @PathVariable("user_id") Long user_id) {

        userService.deleteUserById(user_id);
        return ResponseEntity.ok().build();

    }

}