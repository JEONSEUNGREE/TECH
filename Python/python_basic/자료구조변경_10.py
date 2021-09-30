menu = { "커피", "우유", "주스"}

print(menu, type(menu))

menu = list(menu)
print(menu, type(menu))

menu = tuple(menu)
print(menu, type(menu))

menu = set(menu)
print(menu, type(menu))



# Quiz

from random import *

num = range(1, 21)
# print(type(num))
num = list(num)
# print(type(num))
shuffle(num)
print(num)

receiver = sample(num, 4)

print("치킨 당첨자 : {}".format(receiver[0]))
print("커피 당첨자 : {}".format(receiver[1:]))

