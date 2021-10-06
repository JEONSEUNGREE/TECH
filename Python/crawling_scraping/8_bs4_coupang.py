import requests

from bs4 import BeautifulSoup
import re
url = "https://www.coupang.com/np/search?q=%EB%85%B8%ED%8A%B8%EB%B6%81&channel=user&component=&eventCategory=SRP&trcid=&traid=&sorter=scoreDesc&minPrice=&maxPrice=&priceRange=&filterType=&listSize=36&filter=&isPriceRange=false&brand=&offerCondition=&rating=0&page=5&rocketAll=false&searchIndexingToken=1=6&backgroundColor="
# 접속안될시 user-agent 변경
headers = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36"}
res = requests.get(url, headers=headers)
res.raise_for_status()
soup = BeautifulSoup(res.text, "lxml")

# print(res.text)
items = soup.find_all("li", attrs={"class":re.compile("^search-product")})
print(items[0].find("div", attrs={"class":"name"}).get_text())

for item in items:
    # 제품명
    name = item.find("div", attrs={"class":"name"}).get_text()
    # 가격
    price = item.find("strong", attrs={"class":"price-value"}).get_text()

    # 평점 #평점이 없는 상품이있을수있음 그러면 오류 발생 (null값처리필요)
    rate = item.find("em", attrs={"class":"rating"})
    if rate:
        rate = rate.get_text()
    else:
        rate = "평점 없음"

    # 평점수 이부분도 없으면 오류 발생 
    rate_cnt = item.find("span", attrs={"class":"rating-total-count"})
    if rate_cnt:
        rate_cnt = rate_cnt.get_text()
    else:
        rate_cnt = "평점 없음"
    print(name, price, rate, rate_cnt)