from selenium import webdriver
# 헤드리스의 주의점
# user agent string을치면 whatisMyBrowser.com에서 userAgent를 개발자 툴로 a태크를 가져오면
# "https://developers.whatismybrowser.com/useragents/parse/?analyse-my-user-agent=yes" - detected_value 아이디 태그
#  https://www.whatismybrowser.com/detect/what-is-my-user-agent
options = webdriver.ChromeOptions()
options.headless = True
options.add_argument("window-size=1920x1080") # 백그라운드에 띄우는 크롬
options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36")
# 크롬을 직접띄우지않아 빠름

browser = webdriver.Chrome(options=options)
browser.maximize_window()

url = "https://www.whatismybrowser.com/detect/what-is-my-user-agent"
browser.get(url)

detected_value = browser.find_element_by_id("detected_value")
print(detected_value.text) # Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) HeadlessChrome/94.0.4606.71 Safari/537.36
                           # 위와같이 출력해보면 HeadlessChrome으로 접속한 정보가 존재하여 서버에서 막을수가 있다.
                           # 따라서 이 옵션을 추가해야한다. options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36")
                           # 옵션 적용 후 Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36
browser.quit()

# 추가적으로 공식문서
# https://selenium-python.readthedocs.io/

# 도메인마다 robots.txt를 쳐서 보자
