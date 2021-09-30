def profile(name, age, address):
    print(name, age, address)

# 파라미터를 직접찍어서 순서 관계없이 넣을수있다.
profile(address="수원", name="승리", age=7)

# def subject(sub1, sub2, sub3):
#     print(sub1, sub2, sub3)

# *파리미터를 통해서 한번의 파라미터로 여러번 받아쓸수있다.
def subject(*sub1):
    for lan in sub1:
        print(lan, sub1)
    

subject("코틀린","스위프트","자바")

# 지역변수 전역변수
총 = 10

def 경계근무(군인수):
    # 총 = 20 # 이 부분이 빠지면 오류가 발생 assignmet라고 초기화오류 지역변수로 인식하기에
    # 따라서 다음과같이 전역변수를 쓰겠다고 global 총으로 설정해줄필요가있다.
    global 총
    총 = 총 - 군인수
    print(총)

# 전역변수를 바꾸는 또 다른 방법
# 이렇게 지역변수지만 리턴해주고 밑에서 받아줌으로써 값을 바꿀수있다.
def 확인(총, 군인수):
    총 = 총 - 군인수
    print(총)
    return 총


경계근무(5)
print(총)

총 = 확인(총, 4)
print(총)
