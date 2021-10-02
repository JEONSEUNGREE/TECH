import pygame

pygame.init() # 초기화

# 화면 크기 설정
screen_width = 480 # 가로크기
screen_height = 640 # 세로크기
screend = pygame.display.set_mode((screen_width, screen_height))

# 화면 타이틀
pygame.display.set_caption("Ree Game")

# 이벤트 루프
running = True # 게임 플래그
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT: # 창을 닫을때 이벤트
            running = False

# pygame 종료
