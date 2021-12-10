from selenium import webdriver
from bs4 import BeautifulSoup
from selenium.webdriver.common.keys import Keys
import time
import urllib.request
import re

options = webdriver.ChromeOptions()
# options.headless = True
# options.add_argument("window-size=1920x1080") # 백그라운드에 띄우는 크롬
options.add_argument("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36")

browser = webdriver.Chrome(options=options)
browser.maximize_window()


url = "https://www.google.com"

browser.get(url)
elem = browser.find_element_by_link_text("이미지").click()

elem = browser.find_element_by_name("q")

elem.click()

elem.send_keys("강아지")

elem.send_keys(Keys.ENTER)

elements = browser.find_elements_by_css_selector(".rg_i.Q4LuWd") # css의 경우 빈공간 . 채우기
count = 1
for image in elements:
    image.click()
    imgUrl = browser.find_element_by_xpath("//*[@id='Sva75c']/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[2]/div/a/img").get_attribute("src")
    print(imgUrl)
    urllib.request.urlretrieve(imgUrl, "image" + str(count) + ".jpg")
    count = count + 1






# for elem in elements:
#     i =+ 1
#     elem.click()
#     imgUrl = browser.find_element_by_css_selector(".n3VNCb").get_attribute("src")

#     text = imgUrl.split(".")
#     tail = text.pop()
#     urllib.request.urlretrieve(imgUrl, str(i) + "test."  + tail)