# pip install beautifulsoup4 스크래핑용
# pip install lxml 파싱언어

import requests
from bs4 import BeautifulSoup

url = "https://comic.naver.com/index"
res = requests.get(url)
res.raise_for_status()

soup = BeautifulSoup(res.text, "lxml")
# print(soup.title) # 타이틀 태그도존재
# print(soup.title.get_text()) # 태그빼고 타이틀만
# print(soup.a) # 처음발견된 a태그 정보
# print(soup.a.attrs) # 해당태그 속성정보 {'href': '#menu', 'onclick': "document.getElementById('menu').tabIndex=-1;document.getElementById('menu').focus();return false;"}
# print(soup.a["href"]) # 위의 a element의 {'href': '#menu'# #menu 출력

# print(soup.find("a", attrs={"class": "Nbtn_upload"})) # soup객체에서 a Element에서 클래스 정보가 Nbtn_upload인것 
# print(soup.find(attrs={"class": "Nbtn_upload"})) # 혹은 클래스이름이 유니크하면 태그명을 적지않아도 가능
rank1 = soup.find("li", attrs={"class":"rank01"}) # li 정보
# print(rank1.a) # 가져오 li에서 a태그만
print(rank1.a.get_text()) # 글자만 추출
print(rank1.next_sibling.next_sibling) # 태그다음 태그 (개행정보있는경우 한번더 하면된다.)
rank2 = rank1.next_sibling.next_sibling
rank3 = rank2.next_sibling.next_sibling
print(rank3.a.get_text())
rankP = rank2.previous_sibling.previous_sibling # 이전 이전으로 올라오는 것
print(rankP.a.get_text())
print(rank1.parent) # 부모노드의 하위 자식 전부출력

rank1 = rank1.find_next_sibling("li") # 개행정보를 무시하고 nextsibling 두번 들어가는것을 할 필요가없다.
print(rank1.a.get_text())

rank2 = rank1.find_next_sibling("li")
print(rank2.a.get_text())

rank3 = rank2.find_previous_sibling("li")
print(rank3.a.get_text())

rank1 = rank1.find_next_siblings("li") # 뒤에 li정보 모두 가져오기
print(rank1)

webtoon = soup.find("a", text="화산귀환-29화")
print(webtoon.get_text())
