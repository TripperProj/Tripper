package com.tripper.controller;

import com.tripper.domain.user.Role;
import com.tripper.dto.response.user.GetUserListDto;
import com.tripper.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "관리자 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @ApiOperation(
            value = "회원 목록 조회"
            , notes = "회원 전체 목록을 조회한다.")
    @GetMapping("/users")
    public ResponseEntity<GetUserListDto> getUserList() {

        GetUserListDto users = userService.findAllUsers();
        return ResponseEntity.ok().body(users);

    }

    @ApiOperation(
            value = "매니저 권한 부여"
            , notes = "user 회원에게 manager 권한을 부여한다.")
    @PutMapping("/auth/user/{userId}")
    public ResponseEntity updateAuth(@ApiParam(value = "권한 부여할 회원의 고유 id") @PathVariable("userId") Long userId) {

        userService.updateAuth(userId, Role.ROLE_MANAGER);
        return ResponseEntity.ok().build();

    }
}
