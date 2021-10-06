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
    # 만일 로켓 배송 제품을 제외한다면
    # ad_badge = item.find("span", attrs={"class":"badge rocket"})
    # if ad_badge:
    #     print(" <로켓배송 상품입니다>")
    #     continue
    
    # 제품명
    name = item.find("div", attrs={"class":"name"}).get_text()

    # 마이크로소프트 제품 제외
    if "Apple" in name:
        print("애플제품 제외합니다.", name)
        continue
    # 가격
    price = item.find("strong", attrs={"class":"price-value"}).get_text()

    # 리뷰 100개 이상, 평점 4.5이상 되는것만 조회
    review = 100
    rate_score = 4.5

    # 평점 #평점이 없는 상품이있을수있음 그러면 오류 발생 (null값처리필요)
    rate = item.find("em", attrs={"class":"rating"})
    if rate:
         rate = rate.get_text()
    else:
        print("평점없는 상품제외합니다.")
        continue

    # 평점수 이부분도 없으면 오류 발생 
    rate_cnt = item.find("span", attrs={"class":"rating-total-count"})

    if rate_cnt:
        rate_cnt = rate_cnt.get_text()
        rate_cnt = rate_cnt[1:-1]
    else:
        print("리뷰가 없습니다.")
        continue

    if float(rate) >= 4.5 and int(rate_cnt) >= 100:
        print(name, price, rate, rate_cnt)
    else:
        pass
        
        