package com.book.springboot;

import com.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class) // JUnit에 내장된 실행자가 아닌 SpringRunner라는 스프링 실행자를 사용, 스프링부트 테스트와 JUnit 사이의 연결자 역할
@WebMvcTest(HelloController.class) // Web에 집중할 수 있는 어노테이션 (Service Component, Repository등은 사용불가)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean을 주입
    private MockMvc mvc; // 웹 API를 테스트할 때 사용. 스프링 MVC테스트의 시작점. HTTP의 GET과 POST 등에 대한 API 테스트

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // /hello 주소로 GET요청
                .andExpect(status().isOk()) // HTTP 헤더의 status를 검증. isOk는 200인지 아닌지 검증 (ex) 200,404,500)
                .andExpect(content().string((hello))); // 응답 본문 내용을 검증 , hello를 리턴하기에 맞는지 아닌지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name) // API를 테스트 할 때 사용될 요청 파라미터 설정, 값은 String만 허용
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) // JSON응답값을 필드별로 검증, $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
