package com.tripper.dto.response.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTripDto {
    private Long id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
}
