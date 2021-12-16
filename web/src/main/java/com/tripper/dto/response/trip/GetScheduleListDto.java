package com.tripper.dto.response.trip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetScheduleListDto {
    List<GetScheduleDto> schedules = new ArrayList<>();
}
