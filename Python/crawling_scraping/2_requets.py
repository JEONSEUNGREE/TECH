# pip3 install requests
import requests

res = requests.get("http://google.com")
print("응답코드 : ", res.status_code)
res.raise_for_status() # 웹스크래핑하기위해 html문서를 가져오지못한경우 에러를 발생시키고 프로그램종료

print(len(res.text))
print(res.text)

with open("mygoogle.html", "w", encoding="utf8") as f:
    f.write(res.text)

# if res.status_code == requests.codes.ok:
#     print("정상입니다.")
# else:
#     print("문제가 생겼습니다. [에러코드", res.status_code, "]")

#     res.raise_for_status() # 웹스크래핑하기위해 html문서를 가져오지못한경우 에러를 발생시키고 프로그램종료
#                            #
#     print("웹 스크랩핑을 진행합니다.")