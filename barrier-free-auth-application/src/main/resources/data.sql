
-- 계정

insert into ACCOUNTS(ID, USERNAME, PASSWORD, ACCOUNT_TYPE)
values (10001, 'kim', '0000', 'MAIN');

insert into MAIN_ACCOUNTS(ID)
values (10001);

insert into ACCOUNTS(ID, USERNAME, PASSWORD, ACCOUNT_TYPE)
values (10002, 'user', '0000', 'MAIN');

insert into MAIN_ACCOUNTS(ID)
values (10002);

-- 제품
insert into PRODUCTS(ID, NAME, DESCRIPTION, PRICE)
values (20001, '코카콜라', '청량감있는 멋진 음료', 1500);

insert into PRODUCTS(ID, NAME, DESCRIPTION, PRICE)
values (20002, '감자칩', '바싹바싹 기분 좋은 봉지 과자', 2400);

insert into PRODUCTS(ID, NAME, DESCRIPTION, PRICE)
values (20003, '컵라면', '감칠맛 있는 훌륭한 컵라면', 1700);

-- 주문
insert into ORDERS(ID, USER_ID, PRODUCT_ID, QUANTITY, PRICE, ORDER_DATE, IS_DONE)
values (30001, 10002, 20002, 3, 7200, CURRENT_DATE(), false);

insert into ORDERS(ID, USER_ID, PRODUCT_ID, QUANTITY, PRICE, ORDER_DATE, IS_DONE)
values (30002, 10002, 20001, 10, 15000, '2025-06-30', false);

insert into ORDERS(ID, USER_ID, PRODUCT_ID, QUANTITY, PRICE, ORDER_DATE, IS_DONE)
values (30003, 10002, 20003, 1, 1700, '2022-11-24', false);
