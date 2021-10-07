import csv
import requests
from bs4 import BeautifulSoup
url = "https://finance.naver.com/sise/sise_market_sum.nhn?sosok=0&page="

filename = "시가총액1-200.csv"
f = open(filename, "w", encoding="utf-8-sig", newline="") # 줄바꿈 없애기위해서 newline="" 엑셀에서 글자깨짐시 utf-8-sig

writer = csv.writer(f)

title = "N	종목명	현재가	전일비	등락률	액면가	시가총액	상장주식수	외국인비율	거래량	PER	ROE".split("\t")
# 타이틀 넣기 ["N", "종목명", "현재가", ....]
print(type(title))
writer.writerow(title)

for page in range(1, 5):
    res = requests.get(url + str(page))
    res.raise_for_status()
    soup = BeautifulSoup(res.text, "lxml")

    data_rows =  soup.find("table", attrs={"class":"type_2"}).find("tbody").find_all("tr")

    for row in data_rows:
        columns = row.find_all("td")
        if len(columns) <= 1: #의미없는데이터 스킵
            continue
        data = [column.get_text().strip() for column in columns] # 컬럼을 가져와서 각 테스트를 추출하고 데이터변수에 담는다. strip으로 공백제거
        print(data)
        writer.writerow(data) # 리스트형태로 넣으면 된다.







