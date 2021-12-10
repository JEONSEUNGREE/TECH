try:
    print("나누기 계산기")
    num1 = int(input("1번쨰 숫자 : "))
    num2 = int(input("2번쨰 숫자 : "))
    print("{0} / {1} = {2}".format(num1, num2, int(num1 / num2)))

except ValueError:
    print("에러! 잘못된 값을 입력하였습니다.")

except ZeroDivisionError as err:
    print(err)

# 위의 예외를 통과한경우
except Exception as err: # 에러메시지 알고싶은경우
    print("알수없는 에러") 
    print(err)

# 로그
# 나누기 계산기
# 1번쨰 숫자 : 25
# 2번쨰 숫자 : 상
# 에러! 잘못된 값을 입력하였습니다.

# 예외 발생시키기
try:
    print("한자리 숫자만 입력")
    num1 = int(input("첫번째숫자"))
    num2 = int(input("두번째숫자"))
    if num1 > 10 or num2 >= 10:
        raise ValueError # 두자리 수인경우 예외를 발생하여 바로 except로 이동
    print("{0} / {1} = {2}".format(num1, num2, (num1 / num2)))

except ValueError:
    print("잘못된 값입니다.")


# exception 상속받아서 예외 클래스 만들기
class BigNumberError(Exception):
    pass # 패스는 그냥 코드 없다는 것

try:
    print("한자리 숫자만 입력")
    num1 = int(input("첫번째숫자"))
    num2 = int(input("두번째숫자"))
    if num1 > 10 or num2 >= 10:
        raise BigNumberError # 두자리 수인경우 예외를 발생하여 바로 except로 이동
    print("{0} / {1} = {2}".format(num1, num2, (num1 / num2)))

except BigNumberError:
    print("잘못된 값입니다.")

# exception 메서드에 메시지 넣기
class BigNumberError(Exception):
    # pass # 패스는 그냥 코드 없다는 것
    def __init__(self, msg):
        self.msg = msg

    def str(self):
        return self.msg


try:
    print("한자리 숫자만 입력")
    num1 = int(input("첫번째숫자"))
    num2 = int(input("두번째숫자"))
    if num1 > 10 or num2 >= 10:
        raise BigNumberError("입력값: {}, {1}".format(num1, num2)) # 두자리 수인경우 예외를 발생하여 바로 except로 이동
    print("{0} / {1} = {2}".format(num1, num2, (num1 / num2)))

except BigNumberError:
    print("잘못된 값입니다.")

finally:
    print("프로그램을 종료합니다.")
