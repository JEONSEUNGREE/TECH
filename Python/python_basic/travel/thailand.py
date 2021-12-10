class ThailandPackage:
    def detail(self):
        print("[태국 패키지 3박 5일] 방콕, 파타야 여행 45만원")

# 실제 코드가 내부,외부 어느쪽에서 사용되지는 알고싶을때

if __name__ == "__main__":
    print("Thailand 모듈을 직접 실행")
    print("이 문장은 모듈을 직접 실행할 때만 실행된다.")
    trip_to = ThailandPackage()
    trip_to.detail()

else:
    print("Thailand 외부에서 모듈 호출")