import requests
from bs4 import BeautifulSoup

url = "https://comic.naver.com/webtoon/list?titleId=20853"
res = requests.get(url)
res.raise_for_status()

soup = BeautifulSoup(res.text, "lxml")

#가져오고자 하는 html의 구조
# <td class="title">
# <a href="/webtoon/detail?titleId=20853&amp;no=50&amp;weekday=tue" onclick="nclk_v2(event,'lst.title','20853','50')">마음의 소리 50화 &lt;격렬한 나의 하루&gt;</a>
 # </td>
# cartoons = soup.find_all("td", attrs={"class":"title"})

# title = cartoons[0].a.get_text()
# print(title) # 마음의 소리 50화 <격렬한 나의 하루>

# link = cartoons[0].a["href"]
# print(title) 
# print("https://comic.naver.com"+link) # /webtoon/detail?titleId=20853&no=50&weekday=tue 앞에 빠진 주소를 더해준다.

# 만화 제목 + 링크 구하기
# for cartoon in cartoons:
#     print(cartoon.a.get_text())
#     print("https://comic.naver.com" + cartoon.a["href"])

# 평점 구하기
total_rates = 0
cartoons = soup.find_all("div",attrs={"class":"rating_type"})
for cartoon in cartoons:
    rate = cartoon.find("strong").get_text()
    print(rate)
    total_rates += float(rate)

print("전체 점수 : ", total_rates)
print("전체 점수 : ", total_rates / len(cartoons)) # 전체 점수 :  9.889
# 참고 파이썬은 인터프리터 형식이기때문에 콘솔창에 python으로 친다면 한줄씩 실행시킬수있다.
# exit() 하면 터미널 빠져나옴



