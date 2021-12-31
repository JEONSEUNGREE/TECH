import time # 기다리는 시간을 주기위해 로그인 실패시 자동입력방지 창

from selenium import webdriver

browser = webdriver.Chrome("/home/ree/Downloads/chromedriver")


# 1. 네이버 이름
browser.get("http://naver.com")

# 2. 로그인 버튼 클릭
elem = browser.find_element_by_class_name("link_login")
elem.click()

# 3. id, pw 입력
browser.find_element_by_id("id").send_keys("naver_id")
browser.find_element_by_id("pw").send_keys("password")

# 4. 로그인 버튼 클릭
browser.find_element_by_id("log.login").click()

time.sleep(3)

# 5. id 를 새로 입력
# browser.find_element_by_id("id").send_keys("my_id") # 이전에 작성한 아이디가 남아있다.

browser.find_element_by_id("id").clear()
browser.find_element_by_id("id").send_keys("my_id")

# 6. html 정보 출력
print(browser.page_source)


# 7. 브라우저 종료
# browser.quit() # 전체 브라우저 종료

time.sleep(10)