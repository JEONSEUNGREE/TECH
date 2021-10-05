import random
import pygame
########################################
# 기본 초기화
pygame.init() # 초기화

# 화면 크기 설정
screen_width = 480 # 가로크기
screen_height = 640 # 세로크기
screen = pygame.display.set_mode((screen_width, screen_height))

# 화면 타이틀
pygame.display.set_caption("Ree Game")

# 배경 이미지 불러오기
background = pygame.image.load("D:\\java_work\\TECH\\Python\\python_game\\pygame_basic\\background.png")

# FPS
clock = pygame.time.Clock()
########################################


# 캐릭터(스프라이트) 불러오기
character = pygame.image.load("D:\\java_work\\TECH\\Python\\python_game\\pygame_basic\\character.png")
character_size = character.get_rect().size #이미지 크기 구해옴 # rectangular
character_width = character_size[0] # 캐릭터의 가로크기
character_height = character_size[1] # 캐릭터의 세로크기
character_x_pos = (screen_width / 2) - (character_width / 2) 
character_y_pos = screen_height - character_height

# 이동할 좌표
to_x = 0
to_y = 0                            

# 이동 속도
character_speed = 0.6

# 적 enemy 캐릭터
enemy = pygame.image.load("D:\\java_work\\TECH\\Python\\python_game\\pygame_basic\\enemy.png")
enemy_size = enemy.get_rect().size #이미지 크기 구해옴 # rectangular
enemy_width = enemy_size[0] # 캐릭터의 가로크기
enemy_height = enemy_size[1] # 캐릭터의 세로크기
enemy_x_pos = random.randint(0, screen_width - enemy_width)
enemy_y_pos = 0
enemy_speed = 10


# 폰트 정의
game_font = pygame.font.Font(None, 40) # 폰트 객체 생성 (폰트, 크기)

# 총 시간
total_time = 100

# 시간 계산
start_ticks = pygame.time.get_ticks() # 시작 tick 을 받아옴

# 1. 사용자 게임 초기화 (배경 화면, 게임 이미지, 좌표, 폰트 등)


# 이벤트 루프
running = True # 게임 플래그
while running:
    dt = clock.tick(10) # 초당 프레임 설정

    print("fps : " + str(clock.get_fps))

    # 2. 이벤트 처리(키보드, 마우스 등)
    for event in pygame.event.get():
        if event.type == pygame.QUIT: # 창을 닫을때 이벤트
            running = False

        if event.type == pygame.KEYDOWN: # 키가 눌러졌는지 확인
            if event.key == pygame.K_LEFT: # 캐릭터를 왼쪽으로
                to_x -= character_speed
            elif event.key == pygame.K_RIGHT:
                to_x += character_speed
            elif event.key == pygame.K_UP:
                to_y -= character_speed
            elif event.key == pygame.K_DOWN:
                to_y += character_speed

        # 사용자가 키를 떼었을때
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                to_x = 0
            elif event.key == pygame.K_UP or event.key == pygame.K_DOWN:
                to_y = 0

    character_x_pos += to_x * dt
    character_y_pos += to_y * dt

    # 가로 경계값 처리
    if character_x_pos < 0:
        
        character_x_pos = 0
    elif character_x_pos > screen_width - character_width:
        character_x_pos = screen_width - character_width

    # 세로 경계값 처리
    if character_y_pos < 0:
        character_y_pos = 0
    elif character_y_pos > screen_height - character_height:
        character_y_pos = screen_height - character_height

    enemy_y_pos += enemy_speed

    if enemy_y_pos > screen_height:
        enemy_y_pos = 0
        enemy_x_pos = random.randint(0, screen_width - enemy_width)

    #충돌 처리
    character_rect = character.get_rect()
    character_rect.left = character_x_pos
    character_rect.top = character_y_pos

    enemy_rect = enemy.get_rect()
    enemy_rect.left = enemy_x_pos
    enemy_rect.top = enemy_y_pos


    # 충돌 체크
    if character_rect.colliderect(enemy_rect):
        print("충돌 했다.")
        running = False

    # 화면 그리기
    screen.blit(background,(0, 0)) # 배경그리기 맨위가 0,0 x,y좌표를 뜻함   
    screen.blit(character, (character_x_pos, character_y_pos)) # 배경처럼 좌표의 왼쪽 상단부터 시작하기에 밑에 파묻혀있는 상태
    screen.blit(enemy, (enemy_x_pos, enemy_y_pos))

    # 타이머 집어 넣기
    # 경과 시간 계산
    elapsed_time = (pygame.time.get_ticks() - start_ticks / 1000) / 1000
    # 경과시간이 ms라서 초단위로 표시

    timer = game_font.render(str(int(total_time - elapsed_time)), True, (255, 255, 255))
    # 시간 정보, 안티앨리어싱, 글자 색상
    screen.blit(timer, (10, 10))

    # 시간이 0이하이면 게임종료
    if total_time - elapsed_time <= 0:
        print("타임아웃")
        running = False


    pygame.display.update() # 게임 화면을 그리기

# 잠시 대기 (종료시 바로꺼지는거 방지)
pygame.time.delay(2000)

# pygame 종료
