cabinet = {3:"유재석", 100:"김태호"}
print(cabinet[3])
print(cabinet[100])

print(cabinet.get(3)) # == print(cabinet[3])
# 참고로 get의 경우는 없는 키값의경우 에러 발생하지않고 None이 나옴
# 일반 배열의 경우는 없는 키인경우 에러 발생

print(cabinet.get(5, "사용가능")) # 없는경우 이처럼 지정한 스트링출력가능

# 캐비넷에 특정키가 있는지 여부
print(3 in cabinet)
print(5 in cabinet)

# 중복키값의 경우 밸류 업데이트
cabinet[3] = "바보"
print(cabinet)
# 1. 키 값만 출력 2. 밸류값만 출력 3. 비우기
# 1. cabinet.keys() 2. cabinet.values 3. cabinet.clear
