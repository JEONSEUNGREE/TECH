import pygame

pygame.init() # 초기화

# 화면 크기 설정
screen_width = 480 # 가로크기
screen_height = 640 # 세로크기
screen = pygame.display.set_mode((screen_width, screen_height))

# 화면 타이틀
pygame.display.set_caption("Ree Game")

# 배경 이미지 불러오기
background = pygame.image.load("D:\\java_work\\TECH\\Python\\python_game\\pygame_basic\\background.png")

# 이벤트 루프
running = True # 게임 플래그
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT: # 창을 닫을때 이벤트
            running = False

    screen.blit(background,(0, 0)) # 배경그리기 맨위가 0,0 x,y좌표를 뜻함    
    # screen.fill((0, 0, 255)) # 참고로 그림불러오지않고 색을 직접 지정할수도있다.

    pygame.display.update() # 게임 화면을 그리기

# pygame 종료

