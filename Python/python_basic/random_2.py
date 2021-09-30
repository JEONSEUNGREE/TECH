from random import *

print(random()) # 0.0 ~ 1.0 미만의 임의의 값 생성

print(random() * 10) # 0.0 ~ 10.0 미만의 임의의 값 생성

print(int(random() * 10)) # 0.0 ~ 10.0 미만의 임의의 값 생성
print(int(random() * 10) + 1) # 0.0 ~ 10.0 미만의 임의의 값 생성

print(randrange(1, 45)) # 1 ~ 45 미만의 임의의 값 생성

print(randint(1, 45)) # 1~45 이하의 임의의 값 생성


# Quiz
date = randrange(4, 29)

# 숫자이기때문에 str로 스트링 형변환 해주어야 + 연산자 가능 아니면 쉼표 혹은 format
print("오프라인 스터디 모임 날짜는 매월 " + str(date) + "일로 선정되었습니다.")
print("오프라인 스터디 모임 날짜는 매월 {0}일로 선정되었습니다.".format(date))


