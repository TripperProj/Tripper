package com.tripper.dto.response.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class GetScheduleDto {
    private Long id;
    private String name;
    private String content;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
