package study.querydsl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.querydsl.entity.MemberSearchCondition;
import study.querydsl.entity.MemberTeamDto;
import study.querydsl.repository.MemberJpaRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberConroller {

    private final MemberJpaRepository memberJpaRepository;
// api요청했을때 값이 잘나오는 것을 확인
// http://localhost:8080/v1/members?teamName=teamB&ageGoe=31&ageLoe=35&username=member31
    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
        return memberJpaRepository.search(condition);
    }
}
