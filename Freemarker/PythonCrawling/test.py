
import json
import requests

import selenium
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import urllib.request

urI = "https://www.google.com/search?q=springboot"
driver = webdriver.Chrome()
driver.get("https://www.google.co.kr/imghp?hl=ko&tab=wi&ogbl")
elem = driver.find_element_by_name("q")
# n3VNCb

options = webdriver.ChromeOptions()
options.add_argument('--headless')
options.add_argument('--window-size=1024,768')
options.add_argument('--disable-gpu')


elem.send_keys("프리마커")    
elem.send_keys(Keys.RETURN)    
images = driver.find_elements_by_css_selector(".rg_i.Q4LuWd")
lists = []
count = 1
for image in images:
    image.click()
    imgUrl = driver.find_element_by_css_selector(".n3VNCb").get_attribute("src")
    lists.append()
    count = count + 1


print("테스트용" + lists[0])
