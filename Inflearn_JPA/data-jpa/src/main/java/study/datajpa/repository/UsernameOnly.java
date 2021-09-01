package study.datajpa.repository;


import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

//    오픈 프로젝션 다가져와서 정리함 value가없으면 정확한 값만 찍어서 가져옴 클로즈 프로젝션
    @Value("#{target.username + ' ' + target.age}")
    String getUsername();
}
