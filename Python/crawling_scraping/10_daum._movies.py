# 다음영화 크롤링 최근 5년간 관객수별
import requests
from bs4 import BeautifulSoup


for year in range(2015, 2020):
    url = "https://search.daum.net/search?w=tot&q={}%EB%85%84%EC%98%81%ED%99%94%EC%88%9C%EC%9C%84&DA=MOR&rtmaxcoll=MOR".format(year)
    res = requests.get(url)
    res.raise_for_status()
    soup = BeautifulSoup(res.text, "lxml")

    images = soup.find_all("img", attrs={"class":"thumb_img"})

    
    for idx, image in enumerate(images): # 똑같이 루프를 돌지만 idx를 줄수있는 방법 enumerate
        print(image["src"])
        image_url = image["src"]
        # if image_url.startswith("//"): 만일 https가없는경우 시작이 //인 위치에서 https를 넣겠다는 뜻
            # image_url = "https:" + image_url

        print(image_url)
        image_res = requests.get(image_url)
        image_res.raise_for_status()

        with open("moive_{}_{}.jpg".format(year, idx + 1), "wb") as f: # 인코딩은 따로 필요없음
            f.write(image_res.content) # content 이미지

        if idx >= 4: # 상위 이미지 5개만 다운로드
            break

        print(image_url)