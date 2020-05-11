package com.hoon.foodrocket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Builder    // builder 패턴을 사용
@Getter     // getter 메소드 생성
@NoArgsConstructor  // 파라미터가 없는 빈 생성자 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 생성
public class User {

    private Long id;

    @NotEmpty   // 해당 요소는 null 이거나 비어서는 안 됨
    private String email;

    @NotEmpty
    private String name;

    @Setter
    @NotEmpty
    private String password;

    /**
     * address 는 유저의 주소를 담는 변수이다.
     * 유저는 주소를 선택하고, 상세주소를 입력한다.
     * <p>
     * 시도 시군구 도로명 건물번호본번 -> 경기도 의정부시 부용로 49
     * 상세주소 -> 101동 1004호
     */
    @NotEmpty
    private String address;

    /**
     * region 은 지역을 표시한 정보로 가게의 배달 가능 지역에 포함될 경우 해당 가게를 보여준다.
     * 유저가 입력한 address 중 동에 해당하는 정보를 담고있다.
     * <p>
     * 법정동명 -> 금오동
     */
    @NotEmpty
    private String region;

    public boolean isNotMatchPassword(String password) {
        return !this.password.equals(password);
    }

}
