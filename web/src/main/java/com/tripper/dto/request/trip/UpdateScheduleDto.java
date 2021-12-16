package com.tripper.dto.request.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 일정 수정 정보를 담은 dto
 */
@Getter @Setter
@AllArgsConstructor
public class UpdateScheduleDto {

    @NotNull(message = "일정 제목을 입력해주세요.")
    private String name;

    @NotNull(message = "일정 상세 내용을 입력해주세요.")
    private String content;

    @NotNull(message = "일정 시작 날짜와 시간을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "일정 종료 날짜와 시간을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
