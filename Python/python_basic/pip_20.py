# https://pypi.org/ 다양한 패키지가 존재함 필요한부분ㅇ을 사용할수있다.
# 예제로 beautifulsoup4 4.10.0 를 설치한다.
# 설치 : pip install beautifulsoup4
# 설치내역 확인 : pip list
# 상세 정보 : pip show beautifulsoup4
# 업그레이드 : pip install -upgrage beautifulsoup4 
# 삭제: pip uninstall beautifulsoup4 

from bs4 import BeautifulSoup
soup = BeautifulSoup("<p>Some<b>bad<i>HTML")
print(soup.prettify())

# soup = BeautifulSoup("<p>Some<b>bad<i>HTML")
# <p>
#  Some
#  <b>
#   bad
#   <i>
#    HTML
#   </i>
#  </b>
# </p>

