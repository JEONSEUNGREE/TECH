python = "Python is Amazing"

print(python.lower()) # 소문자
print(python.upper()) # 대문자
print(python[1].upper()) # 특정값 대문자
print(python[1].isupper()) # 문자 대소문자 여부
print(len(python)) # 글자수

print(python.replace("Python", "Java")) # 특정 문자열을 지정한 문자열로 바꾸기

index = python.index("n")
print(index) # 특정 문자열의 위치

index = python.index("n", index + 1)
print(index) # 첫번째 n위치에 + 1 한 인덱스부터 다음 두번째 n 인덱스

print(python.find("Java")) # find는 원하는 값 찾기 없으면 -1
# print(python.index("Java")) # index는 원하는 값이 없는경우 에러

print(python.count("n")) # n갯수 찾기