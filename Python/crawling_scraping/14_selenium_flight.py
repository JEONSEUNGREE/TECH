from typing import cast
from selenium import webdriver
from selenium.webdriver.common.by import By # 로딩용
from selenium.webdriver.support.ui import WebDriverWait # 로딩용
from selenium.webdriver.support import expected_conditions as EC # 로딩용
import time

browser = webdriver.Chrome()

browser.maximize_window() # 창 최대화

url = "https://m-flight.naver.com/"

browser.get(url) # url 로 이동


# 가는날 선택 클릭
elem = browser.find_element_by_xpath("//*[@id='__next']/div/div[1]/div[4]/div/div/div[2]/div[2]/button[1]").click()
# 이번달 27일, 28일 선택

#날짜 선택
time.sleep(2)
browser.find_element_by_xpath("//*[@id='__next']/div/div[1]/div[10]/div[2]/div[1]/div[2]/div/div[2]/table/tbody/tr[5]/td[5]/button").click()

time.sleep(2)
browser.find_element_by_xpath("//*[@id='__next']/div/div[1]/div[10]/div[2]/div[1]/div[2]/div/div[2]/table/tbody/tr[5]/td[7]/button/b").click()

# 도착지 선택
time.sleep(2)
browser.find_element_by_xpath("//*[@id='__next']/div/div[1]/div[4]/div/div/div[2]/div[1]/button[2]/i").click()
time.sleep(2)
browser.find_element_by_xpath("//*[@id='__next']/div/div[1]/div[10]/div[2]/section/section/button[1]").click()
time.sleep(2)
# 제주선택
browser.find_element_by_xpath("//*[@id='__next']/div/div[1]/div[10]/div[2]/section/section/div/button[2]").click()
# 검색하기 
time.sleep(2)
browser.find_element_by_xpath("//*[@id='__next']/div/div[1]/div[4]/div/div/button").click()

# 로딩시간을 고려해야한다.
# 방법 2가지
# 1. sleep 주는 경우 단 시간이 낭비되거나 설정시간보다 오래걸리면 가져오지못한다.
# 2. element가 나올때까지 대기하는 방법
try:
    elements = WebDriverWait(browser, 10).until(EC.presence_of_all_elements_located((By.XPATH, "//*[@id='__next']/div/div[1]/div[5]/div/div[2]/div[3]")))
    # 성공시 수행 동작 실패시 finally
    for elem in elements:
        print(elem)
finally:
    browser.quit()
# 브라우저를 최대10초동안 기다리고 무언가 나오면 그값을 실행한다. By.ID , CLASS_NAME , LINK_TEXT 등 사용가능