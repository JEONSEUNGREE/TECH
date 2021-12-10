class Unit:
    def __init__(self, name, hp):
        self.name = name
        self.hp = hp

class BuildingUnit(Unit):
    
    def __init__(self, name, hp, location):
        # Unit.__init__(self, name, hp)
        super().__init__(name, hp, 0) # super로 초기화 괄호붙이고,self가 없음
        self.location = location
        # 또한 이것의 경우에는 명시적으로 쓰는것이아니라서 다중상속의경우 문제있음
        # 부모의 경우 각가의 파라미터가 다르기에 super는 먼저 상속받은 클래스에만 적용됨
        # 여러클래스의 생성자 호출하기위해서는 각각 초기화해줘야함다.


class Unit:
    def __init__(self):
        print("Unit 생성자")


class Flyable:
    def __init__(self):
        print("Flyable 생성자")

class FlyableUnit(Unit, Flyable):
    def __init__(self):
        super().__init__()

# 다중상속의 경우 super사용시 앞에 생성자만 초기화한다.
# 따라서 명시적으로 초기화해야한다.
dropship = FlyableUnit()


