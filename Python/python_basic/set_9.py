# 집합 (set)
# 중복 안됨, 순서 없음
# set 중괄호 튜플 소괄호
my_set = {1, 2, 3, 3, 3}
print(my_set)

java = {"유재석", "김태호", "양세형"}
python = set(["유재석", "박명수"])

# 교집합 (java 와 python 을 모두 할수있는 개발자)
print(java & python)
print(java.intersection(python))

# 합집합 (java or python 개발자)
print(java | python)
print(java.union(python))

# 차집합 (java는 하지만 python을 모르는)
print(java - python)
print(java.difference(python))

# python을 할 줄 아는 사람이 늘엄
python.add("김태호")
print(python)

# java를 잊은 경우
print(java.remove("양세형"))