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

# 캐릭터(스프라이트) 불러오기
character = pygame.image.load("D:\\java_work\\TECH\\Python\\python_game\\pygame_basic\\character.png")
character_size = character.get_rect().size #이미지 크기 구해옴 # rectangular
character_width = character_size[0] # 캐릭터의 가로크기
character_height = character_size[1] # 캐릭터의 세로크기
character_x_pos = (screen_width / 2) - (character_size[0] / 2)  # 화면 가로의 절반 크기에 해당하는 곳에 위치 
                                                                # 정중앙에서 시작하기에 캐릭터 사이즈의 절반 크기를 빼준다.
character_y_pos = screen_height - character_size[1] # 화면 세로 크기 가장아래에 해당하는 곳에 위치
                                                    # 밑에 파묻혀있기 캐릭터 세로크기만큼 뺴준다.

# 이동할 좌표
to_x = 0
to_y = 0                            

# 이벤트 루프
running = True # 게임 플래그
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT: # 창을 닫을때 이벤트
            running = False

        if event.type == pygame.KEYDOWN: # 키가 눌러졌는지 확인
            if event.key == pygame.K_LEFT: # 캐릭터를 왼쪽으로
                to_x -= 1
            elif event.key == pygame.K_RIGHT:
                to_x += 1
            elif event.key == pygame.K_UP:
                to_y -= 1
            elif event.key == pygame.K_DOWN:
                to_y += 1

        # 사용자가 키를 떼었을때
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                to_x = 0
            elif event.key == pygame.K_UP or event.key == pygame.K_DOWN:
                to_y = 0

    character_x_pos += to_x
    character_y_pos += to_y

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

    screen.blit(background,(0, 0)) # 배경그리기 맨위가 0,0 x,y좌표를 뜻함   

    screen.blit(character, (character_x_pos, character_y_pos)) # 배경처럼 좌표의 왼쪽 상단부터 시작하기에 밑에 파묻혀있는 상태

    pygame.display.update() # 게임 화면을 그리기

# pygame 종료
