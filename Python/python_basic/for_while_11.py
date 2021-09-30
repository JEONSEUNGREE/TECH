
# randrange()
for waiting_no in [0, 1, 2, 3, 4]: # 0, 1, 2, 3, 4
    print("대기번호 : {0}".format(waiting_no))

for i in range(1, 6):
    print(i)


starbucks = ["아메리카노", "커피", "콜드브루"]

# foreach
for i in starbucks:
    print(i)

index = 5
while index >= 1:
    print(index)
    index -= 1
    if index == 0:
        print("종료")

absent = [2,5]
no_book = [7]
for student in range(1, 11):
    if student in absent: # student 번호에 absent가 포함되어있으면 continue
        continue
    elif student in no_book:
        print("{0}".format(no_book))
        break
    print("{0}, 당번".format(student))

# 각 배열에 더하기
student = [1,2,3,4,5]
print(student)
student = [i+100 for i in student] 
print(student)

#이름을 길이로 변경
student = ["Iron man", "Thor", "groot"]
print(student)
student = [len(i) for i in student]
print(student)

# Quiz
from random import *
# condition1 = range(5, 51)
# condition2 = range(5, 16)

# condition1 = list(condition1)
# cnt = 0
# shuffle(condition1)
# for i in condition1:
#     if i > 15:
#         print("[O] ", i, "소요시간 손님")
#         print(cnt)
#     else:
#         print("[] ",i, " 번째 손님")
#         cnt += 1
#         print(cnt)

# print(cnt)        

# 다른 풀이
cnt = 0
for i in range(1, 51):
    time = randrange(1, 51)
    if 5 <= time <= 15:
        print("[0] {0}번째 손님 (소요시간 : {1}분)".format(i, time))
        cnt += 1
    else:
        print("[] {0}번째 손님 (소요시간 : {1}분)".format(i, time))






