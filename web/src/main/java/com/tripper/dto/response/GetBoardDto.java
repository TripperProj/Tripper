package com.tripper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class GetBoardDto {
    private Long id;
    private String title;
    private String destination;
    private int recruitment;
    private String startDate;
    private String endDate;
    private String content;
}
