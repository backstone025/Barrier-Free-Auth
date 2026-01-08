package com.BaeBrother.barrier_free_auth_application.barrier_free_auth_application.security.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MAIN")
public class MainAccount extends Account {
    /*
    정보
    - 상속 받은 정보
        - id
        - user name
        - password
     - mian 계정 고유 정보
        - 보유 금액
        - 임시 계정 id list !! -> 이건 토큰 관리자에서 관리하는 걸로 할지 고민
     */

    /*
    권한 종류
    - 권한 조회
        - 임시 계정 요청 로그 조회
        - 계정간 mapping 정보 조회
    - Order
        - order 추가
        - order 수정(orderDate와 isDone는 변경 불가))
        - order 삭제
        - order 조회
        - 결제 권한(isDone을 변경할 조건)
    - Product
        - product 추가
        - product 수정
        - product 삭제
        - product 조회
     */
}
