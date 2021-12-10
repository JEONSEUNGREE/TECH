# 일반적인 메서드드들 input, print

# dir : 어떤 객체를 넘겨줬을때 그 객체가 어떤 변수와 함수를 가지고 있는지 표시
# import random # 외장함수
# print(dir(random)) # 현재 random 객체가 가지고있는 변수, 함수 표시

print(dir()) # ['__builtins__', '__cached__', '__doc__', '__file__', '__loader__', '__name__', '__package__', '__spec__']
import pickle, random
print(dir()) # ['__builtins__', '__cached__', '__doc__', '__file__', '__loader__', '__name__', '__package__', '__spec__', 'pickle', 'random']

name = "python"
print(dir(name))
# 이 방법 외에도 구글에 list of python builtins를 검색하면 나온다.

# glob : 경로 내의 폴더 / 파일 목록 조회 (윈도우 dir)
import glob
print(glob.glob("*.py")) # 확장자가 py인 모든 파일 

import os
print(os.getcwd()) # 현재 디렉토리 # D:\free\pythonWorkspace

# 응용해서 현 디렉토리에 폴더라는 이름의 디렉토리의 존재여부에 따라 생성한다.
folder = "sample_dir"
if os.path.exists(folder):
    print("이미 존재하는 폴더입니다.")
    # os.rmdir(folder) 다시 삭제하는 기능
    # print(folder), "폴더를 삭제하였습니다."
else:
    os.makedirs(folder)
    print(folder, "폴더를 생성하였습니다.") # sample_dir 폴더를 생성하였습니다

# 시간 관련 함수
import time 
print(time.localtime()) # time.struct_time(tm_year=2021, tm_mon=10, tm_mday=2, tm_hour=14, tm_min=57, tm_sec=29, tm_wday=5, tm_yday=275, tm_isdst=0)
print(time.strftime("%y-%m-%d %H:%M:%S")) # 포맷 지정 21-10-02 14:57:29

import datetime
print("오늘 날짜는 ", datetime.date.today()) # 오늘 날짜는  2021-10-02

# timedelta : 두 날짜 사이의 간격
today = datetime.datetime.today() # 오늘 날짜
td = datetime.timedelta(days=100) # 100 days, 0:00:00
print("우리가 만난지 100일은 ", today + td) # 오늘 부터 백일 후  우리가 만난지 100일은  2022-01-10 14:59:43.075100