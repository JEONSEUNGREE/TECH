# 모듈이란 : 필요한 부분만 만드는 것
# vue에서도 vuex를 통ㅎ새 module단위로 관리했는데 비슷한것같다.

import theater_module_18_1 # as 별칭으로 변수 지정가능
                           # from theater_module import * 로 바로 price, price_morning 이런식으로 자바의 static 처럼 사용가능
                           # from theater_module import price, price_morning 처럼 특정 메서드만 가져올수도있음 
                           # 또한 이 메서드로 as 로 별칭을 써서 사용 가능

theater_module_18_1.price(3) # 3명이서 영화 보러 갔을 때 가격
theater_module_18_1.price_morning(4) # 4명이서 조조할인 가격
theater_module_18_1.price_soldier(5) # 군인할인 가격 
