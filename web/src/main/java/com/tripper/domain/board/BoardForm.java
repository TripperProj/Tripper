package com.tripper.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class BoardForm {

    private String title;
    private String destination;
    private int recruitment;
    private String content;
    
}
