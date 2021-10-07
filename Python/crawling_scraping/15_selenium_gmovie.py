import requests
from bs4 import BeautifulSoup

url = "https://play.google.com/store/movies/top"
# 구글무비를 다음과같이 가져오면 default가 미국접속으로 되어있어서 한국형식으로 출력되지 않는다. 
# 따라서 헤더와 언어를 선택해야한다.

headers = {
    "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36",
    "Accept-Language":"ko-KR,ko"
    }

res = requests.get(url, headers=headers)
res.raise_for_status()

soup = BeautifulSoup(res.text, "lxml")

movies = soup.find_all("div", attrs={"class":"ImZGtf mpg5gc"})

print(len(movies))

# 페이지 접속시 10개만 처음에 가져오기때문에 영화정보를 10개씩만 가져온다.

for movie in movies:
    title = movie.find("div", attrs={"class":"WsMG1c nnK0zc"}).get_text()
    print(title)


# with open("movie.html", "w", encoding="utf8") as f:
#     # f.write(res.text) 
#     f.write(soup.prettify()) # html 문서를 보기 좋게 출력
