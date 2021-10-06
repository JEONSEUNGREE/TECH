# 정규식이란 특정한 규칙을 가진 문자열의 집합을 표현하는 데 사용하는 형식 언어이다.
# 예) 주민등록번호, 이메일 주소와 같이 특정 형식을 갖는 식

import re
# abcd, book, desk
# ca?e
# care, cafe, case, cave

# pattern
p = re.compile("ca.e")  # .의 갯수만큼 파라미터를 받을수있다.
# . (ca.e): 하나의 문자를 의미 > care, cafe, case | caffe X ca와 e사이에 .(한 글자의미)가 들어간다.
# ^ (^de): 문자열의 시작 ? desk, destination | fade (x) de로 시작해야한다.
# $ (se$) : 문자열의 끝 > case, base (o) | face (x)

# m = p.match("case") # 케이스라는것이 위의 정규식과 매칭되어서 출력된다. 만약 정규식을 만족하지 못한다면 NoneType 에러가 발생한다.
# print(m.group())
# 조건문 설정으로 에러방지
def print_match(m):
    if m:
        print("m.group()", m.group()) # 일치하는 문자열 반환
        print("m.string() ", m.string) # 일벽받은 문자열
        print("m.start()", m.start()) # 일치하는 문자열의 시작 index
        print("m.end()", m.end()) # 일치하는 문자열의 끝 index
        print("m.span()", m.span()) # 일치하는 시작 / 끝 index
    else:
        print("매칭되지 않음")

m = p.match("case")
print_match(m)

m = p.search("careless") # search : 주어진 문자열 중에 일차하는게 있는지 확인 search의 경우 통과
                          # match : 주어진 문자열의 처음부터 일치하는지 확인 good care는 match의 경우 에러

print_match(m)
#m.group() care
# m.string()  careless
# m.start() 0
# m.end() 4
# m.span() (0, 4)

lst = p.findall("careless") # findall : 일치하는 모든 것을 리스트 형태로 반환

print(lst)  