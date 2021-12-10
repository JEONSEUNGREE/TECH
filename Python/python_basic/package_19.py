from travel import vietnam
import travel.thailand

#  모듈이나 패키지만 import 가능
#  메서드나 클래스를 바로 import불가능 
#  ex) travel.thailand.detail -> No module 오류
#  단 from travel.thiland import ThilandPackage 이런식으로 모듈에서 배운것처럼 사용가능
#  from travel import vietnam 이런식으로 사용
trip_to = travel.thailand.ThailandPackage()
trip_to.detail()

# 아래코드는 될것같지만 오류가 발생한다.
# *모든 것을 다가져오겠다는 뜻이지만 이부분에서는 개발자가 __init__파일에서 공개범위를 설정해줘야한다. 
# init에 __all__ = ["vietnam"] 

from travel import *
trip_to = vietnam.VietnamPackage()
trip_to.detail()


# 모듈 디렉토리 찾기
import inspect
import random
print(inspect.getfile(random)) # C:\Python39\lib\random.py 이런식으로 출력