class Unit:
    def __init__(self, name, hp):
        self.name = name
        self.hp = hp

class AttackUnit(Unit):
    def __init__(self, name, hp, damage):
        Unit.__init__(self, name, hp)
        self.damage = damage

    def attack(self, location):
        # location은 받은 값 그대로 쓰는경우 self.name 멤버변수 그대로 사용
        print("{0} : {1} 방향으로 적군을 공격".format(self.name, location))

marine = AttackUnit("마린","50","5")
print(marine.hp, marine.name, marine.damage) # 객체지향적인 부분 
marine.attack("북쪽")

# 자바에서는 클래스 다중상속 허용안됐음(클래스 다중상속시 메서드 네임 겹치는 경우 등의 문제 존재했던것같음)
# 찾아보는걸로
# 파이썬의 경우 클래스 다중상속허용

class Flyable:
    def __init__(self, name, flying_speed):
        self.flying_speed = flying_speed
        self.name = name

    def fly(self, location):
        print("{0} : {1} 방향으로 날아갑니다. [속도 {2}]"\
            .format(self.name, location, self.flying_speed))

class FlyableAttackUnit(AttackUnit, Flyable):
    def __init__(self, name, hp, damage, flying_speed):
        AttackUnit.__init__(self, name, hp, damage)
        Flyable.__init__(self, name, flying_speed)

# 발키리
valkyrie = FlyableAttackUnit("발키리", 200, 6, 5)
valkyrie.fly("북쪽")



