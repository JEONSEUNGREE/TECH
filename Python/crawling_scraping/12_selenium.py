# pip install selenium
# 또한 웹앱에맞는 드라이버 설치
# 크롬의경우 주소창에 다음과같이 검색 chrome://version/
# 혹은 구글 옵션 -> 도움 -> chrome 정보
# https://chromedriver.chromium.org/downloads 버전에맞는 드라이버 다운
# chromedriver.exe를 파이썬폴더에 넣으면 이 파일을 통해 크롬을 자동제어할수있다.

from selenium import webdriver

browser = webdriver.Chrome() # "./chromedriver.exe" 같은 경로인경우 생략 가능
browser.get("http://naver.com")

# python으로 터미널에서 실행해보자
# 네이버 로그인
# 위와똑같이 인터프리터방식으로 입력해본다.
# 이후 네이버 로그인 태그를 선택하기위해 태그 a href 정보를 가져온다.
# elem = browser.find_element_by_class_name("link_login") 로그인정보를 가져온다음
# elem 요소를 보고 elem.click()을 실해해본다.
# >>> elem.click()
# >>> browser.back()
# >>> browser.forward()
# >>> browser.refresh()
# >>> browser.refresh()

# 검색해보기
# >>> elem = browser.find_element_by_id("query")
# from selenium.webdriver.common.keys import Keys 패키지 대문자 Keys

# elem.send_keys("코로나19")
# >>> elem.send_keys(keys.ENTER)

# 검색시
# elem = browser.find_element_by_xpath("//*[@id='daumSearch']/fieldset/div/div/button[2]") 버튼 정보를 xpath로 가져와서
# elem.click()을 통해서 구현할수도있다.

# 태그 여러개읽시 find_elements
# elem = browser.find_elements_by_tag_name("a")

# >>> elem = browser.find_elements_by_tag_name("a") 
# >>> for e in elem:
# ...     e.get_attribute("href") bs4의 경우 ["href"]로 속성을 가져왔는데 셀레니움은 대괄호 필요X
# 하고 엔터한번더

# borwer.close() 브루우저 탭 끄기
# brower.quit() 브라우저끄기

# 1. 네이버 이름
browser.get("http://naver.com")


