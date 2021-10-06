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
cartoons = soup.find_all("td", attrs={"class":"title"})

title = cartoons[0].a.get_text()
print(title) # 마음의 소리 50화 <격렬한 나의 하루>

link = cartoons[0].a["href"]
print(title) 
print("https://comic.naver.com"+link) # /webtoon/detail?titleId=20853&no=50&weekday=tue 앞에 빠진 주소를 더해준다.

for cartoon in cartoons:
    print(cartoon.a.get_text())
    print("https://comic.naver.com" + cartoon.a["href"])



