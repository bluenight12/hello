package com.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // Getter 메소드 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성 , final이 없으면 생성자에 포함 X
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
