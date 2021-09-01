package study.datajpa.repository;

public class UsernameonlyDto {

//    파라미터이름으로 매칭
    private final String username;

    public UsernameonlyDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
