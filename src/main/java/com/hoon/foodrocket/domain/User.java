package com.hoon.foodrocket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity     // entity 클래스를 지정하고 테이블과 매핑
@Builder    // builder 패턴을 사용
@Getter     // getter 메소드 생성
@NoArgsConstructor  // 파라미터가 없는 빈 생성자 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 생성
public class User {

    @Id // 기본키 설정
    @GeneratedValue // 기본키의 전략을 설정(default = AUTO)
    private Long id;

    @NotEmpty   // 해당 요소는 null 이거나 비어서는 안 됨
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;
}
