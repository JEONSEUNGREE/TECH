package study.datajpa.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members1/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }
//  위와 아래는 같은결과다 쿼리도같다 이것이 도메인클래스컨버트
//    id를 받았지만 도메인 클래스 컨버터가 중간에 동작해서 회원 엔티티객체를 반환
//    도메인 클래스 컨버터도 레포에서 엔티티를 찾음
//    하지만 단순조회용으로 사용해야한다.(트랜잭션이 없는범위라서)
    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    @PostConstruct
    public void init() {
        memberRepository.save(new Member("useA"));
    }
}
