subway = [ "유재석", "박명수", "노홍철"]

print(subway)

print(subway.index("노홍철"))

subway.append("하하")
print(subway)

subway.insert(1, "정형돈")
print(subway)

# 한명씩 꺼내기
print(subway.pop())
print(subway)

# 리스트 합치기
num_list = [1,2,3,4,5]
name_list = ["조세호", "유재석"]

num_list.extend(name_list)
print(num_list)

print(type(num_list.index("조세호"))) # 타입이 인트임