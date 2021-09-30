# 방법 1
print("나는 %d살입니다." % 20) # 정수값
print("나는 %s을 좋아해요."% "파이썬") # str 문자열
print("Apple은 %c로 시작해요." % "A") # char 문자한개

# 정수를 문자열로도 출력가능 %s 에 정수값넣기 
print("나는 %s색과 %s색을 좋아해요." % ("파란", "빨간"))

# 방법 2
print("나는 {0}{1}살입니다.".format(20,30))
print("나는 {}{}살입니다.".format(20,30))

# 방법 3 
# 변수처럼 사용
print("나는 {age}살이며, {color}색을 좋아한다.".format(age = 20, color = "빨간"))

# 방법 4 (v 3.6 이상)
age = 20
color = "빨간"
print(f"나는 {age}살이며, {color}색을 좋아한다.")