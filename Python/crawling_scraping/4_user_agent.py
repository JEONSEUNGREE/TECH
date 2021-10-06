import requests
url = "https://nadocoding.tistory.com"
# useragent는 접속 브라우저에 따라서 각각 다르다.
# 헤더정보에따라서 데스크탑,모바일의경우를 구분
# requests를 이용해서 특정티스토리에 접속하면 봇으로 requests 인식하기에 거부당한다.
# 따라서 헤더정보에 사용브라우저 정보를 넣어주면 정보를 가져올수있게된다.
headers = {"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36"}
res = requests.get(url, headers=headers)
print("응답코드 : ", res.status_code)
res.raise_for_status() 

with open("user_agent.html", "w", encoding="utf8") as f:
    f.write(res.text)
