print("Python", "Java", "Javascript", sep=" vs ") #Python vs Java vs Javascript 각 파라미터사이에 들어오는 값 sep
print("Python", "Java", "Javascript", end=" ? ")
print("무엇이 더 재밌나요") # Python Java Javascript ? 무엇이 더 재밌나요 끝에 특정 문자를 넣고 줄바꿈 실행 X

import sys
print("Python", "Java", file=sys.stdout)
print("Python", "Java", file=sys.stderr)

scores = {"수학": 0, "영어": 50, "코딩": 100}

# key value
for subject, score in scores.items():
    # print(subject, score)
    # print(subject.ljust(8), score) # 왼쪽으로 7칸 띄고 8칸 부터 score 출력
    print(subject.ljust(8), str(score).rjust(4), sep=":")  #왼쪽으로 7칸 띄고 8번째부터 우측으로 3칸 띄고 4번째부터 작성


# 은행 대기 순번표
# 001, 002, 003, ....
for num in range(1, 21):
    print("대기번호 : " + str(num).zfill(3)) # 3만큼의 공간확보하고 없는 공간은 0으로 채운다는 뜻

# answer = input("아무 값이나 입력하세요 : ") # 이 값의 타입은 str이다.

print("{0: > 10}".format(500)) # > 는 오른쪽 정렬을 뜻함, 10은 공간확보

# 양수 일땐 +로 표시, 음수일 땐 - 로 표시
print("{0: >+10}".format(500)) 
print("{0: >+10}".format(-500)) # 부호를 넣음으로서 -혹은 + 출력

# 왼쪽 정렬하고, 빈칸으로 _로 채움
print("{0:_<+10}".format(500))

# 3자리 마다 콤마를 찍어주기
print("{0:,}".format(100000000))
# 3자리, 부호 추가
print("{0:+,}".format(-10000000))

# 3자리 마다 콤마를 찍어주기, 부호도 붙이고, 자리수 확보하기
# 빈자를 ^표시로채우기
print("{0:^<+30,}".format(10000000000000))

# 소수점 출력 3째자리에서 반올림
print("{0:.2f}".format(5/3))