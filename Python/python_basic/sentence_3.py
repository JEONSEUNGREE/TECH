sentence = "나는 소년이다."
print(sentence)

# 줄바꿈
sentence2 = """
줄바꿈 표시
"""

print(sentence2)

print("백문이 불여일견\n불여일타")

# 작은따옴표 
print('저는 "코딩" 입니다.')
print("저는 \"코딩입니다.\"")
print("저는 \'코딩입니다.\'")

# 역슬래쉬의 경우 한번 더 넣어준다.
print("D:\\free\\pythonWorkspace\\strMethod_5.py")

# \r : 커서를 맨 앞으로 이동 앞으로가서 (pine)4칸을 차지한다.
print("Red Apple\rpine")

# \b : 백 스페이스 (한 글자 삭제)
print("Redd\bApple")

# \t : 탭
print("Red\tApple")

# Quiz
sentence = "http://naver.com"
quiz1 = sentence.replace("http://", "")
print(quiz1)

index2 = quiz1.index(".")
quiz2  = quiz1[:index2]
print(quiz2)

print(quiz2[:4] + str(len(quiz2)) + str(quiz2.count("e")) + "!")


