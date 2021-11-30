package com.tripper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class AddItemDto {
    @NotBlank(message = "항목은 공백이 아닌 문자만 가능합니다.")
    private String content;

    @Positive(message = "지출 내역은 양수만 가능합니다.")
    private int expenditure;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
}
