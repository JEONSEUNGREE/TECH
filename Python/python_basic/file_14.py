score_file = open("score.txt", "w", encoding="utf8")
print("수학 : 0", file=score_file)
print("영어 : 50", file=score_file)
score_file.close()

# w 덮어쓰기 인코딩 정보 utf8
# a 뒤에 이어서쓰기(append)
# 쓰는 방법은 위처럼 file=file 혹은 밑에처럼 write메서드 사용
score_file = open("score.txt", "a", encoding="utf8")
score_file.write("과학 : 80")
score_file.write("\n코딩 : 100")
score_file.close()

# r 읽기모드
score_file = open("score.txt", "r", encoding="utf8")
# print(score_file.read())
print(score_file.readline(), ) # 줄별로 읽기, 한 줄 읽고 커서는 다음줄로 이동
print(score_file.readline(), end="") # 줄바꿈 없앨때
print(score_file.readline())
print(score_file.readline())
score_file.close()

# for문으로 줄바꿈하면서 출력
score_file = open("score.txt", "r", encoding="utf8")
while True:
    line = score_file.readline()
    if not line:
        break
    print(line)
score_file.close()

# 리스트로 담아서 출력
score_file = open("score.txt", "r", encoding="utf8")
lines = score_file.readlines() # list 형태로 저저ㅏㅇ
for list in lines:
    print(list, end="")
score_file.close()


