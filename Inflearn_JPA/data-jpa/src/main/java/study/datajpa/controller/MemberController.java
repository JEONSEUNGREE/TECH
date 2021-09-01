package study.datajpa.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
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

//    http://localhost:8080/members?page=0 은 20개
//    http://localhost:8080/members?page=1 은 21~40까지 이런식으로 나옴
//    http://localhost:8080/members?page=2&size=3 이런식으로 사이조절도가능
//    http://localhost:8080/members?page=2&size=3&sort-id,desc&sort=username,desc 여러조건이 다 가능
//    스프링부트가 스프링datajpa 구현체를 자동으로 구현하면서 컨트롤러에서 파라미터가 바인딩될때 pageable이있으면 알아서 인젝션해줌
//    yml에 디폴트값 최대값 글로벌설정가능

    //    아래처럼 로컬로도 가능
//    (@PageableDefault(size = 12, sort = “username”,
// direction = Sort.Direction.DESC) Pageable pageable
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5)Pageable pageable) {
        Page<Member> page = memberRepository.findAll(pageable);
//         외부에 엔티티를 노출하면 안되기에 dto로 변환
//        pagingSortRepo
        Page<MemberDto> memberDtos = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));
        return memberDtos;
    }

    @PostConstruct
    public void intit2() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("user" + i, i));
        }
    }

//    @PostConstruct
//    public void init() {
//        memberRepository.save(new Member("useA"));
//    }
}
