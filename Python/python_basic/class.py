class Unit:
    def __init__(self, name, hp, damage):
        self.name = name
        self.hp = hp
        self.damage = damage
        print("{0} 유닛이 생성 되었습니다".format(self.name))
        print("체력 {0}, 공격력 {1}".format(self.hp, self.damage))

    def attack(self, location):
        # location은 받은 값 그대로 쓰는경우 self.name 멤버변수 그대로 사용
        print("{0} : {1} 방향으로 적군을 공격".format(self.name, location))


marine1 = Unit("마린", 40, 5)
marine2 = Unit("마린", 40, 5)

marine1.attack("북쪽")
탱크 = Unit("탱크", 150, 35)

wraith1 = Unit("레이스", 80, 5)



# .을 찍는경우 멤버변수가 자동으로 찍힘(vscode) 매우 객체지향적임
print("유닛 이름 : {0}, 공격력 : {1}".format(wraith1.name, wraith1.damage))

# 레이스 클록킹 업글 넣어주기
wraith2 = Unit("레이스", 80, 5)
# 필드를 자동으로 생성
wraith2.clocking = True

print(wraith2.clocking) #True
# print(wraith1.clocking) # 'Unit' object has no attribute 'clocking'


if wraith2.clocking == True:
    print("{0} 는 현재 클로킹 상태입니다.".format(wraith2.name))
