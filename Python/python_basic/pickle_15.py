# pickle은 프로그램상에서 우리가 사용하는 데이터를 파일형태로 저장
import pickle

profile_file = open("profile.pickle", "wb") # w : 쓰기 목적 b타입 : binary file
profile = {"이름": "박명수", "나이":30, "취미": ["축구", "골프", "코딩"]}
print(profile)
pickle.dump(profile, profile_file) # profile 에 있는 정보를 file 에 저장
profile_file.close()

# 파일을 가져와서 특정 변수에 넣는다.
profile_file = open("profile.pickle", "rb")
profile = pickle.load(profile_file)
print(profile)
profile_file.close()

# 위 코드를 아래와 같이 쓸수 있다 열면서 동시에 profile_file이라는 변수라도 담고 출력
# 또한 자동으로 종료해줌
with open("profile.pickle", "rb") as profile_file:
    print(pickle.load(profile_file))

with open("study.txt", "w", encoding="utf8") as study_file:
    study_file.write("파이썬을 열심히 공부하고있어요")

with open("study.txt", "r", encoding="utf8") as studing:
    print(studing.readline())

#quiz 주간 보고서 4개 생성하기
for i in range(1, 5):
    with open("week_report" + str(i), "w", encoding="utf8") as week_report:
        week_report.write("부서 : " + "이름 : " + "업무 요약 : ")
        week_report.write("\ntest용" + str(i))
        

for i in range(1, 5):
    with open("week_report" + str(i), "r", encoding="utf8") as week_report:
        print(week_report.read())
