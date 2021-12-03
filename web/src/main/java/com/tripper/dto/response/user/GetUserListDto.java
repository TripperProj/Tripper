package com.tripper.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetUserListDto {
    List<GetUserDto> users = new ArrayList<>();
}
