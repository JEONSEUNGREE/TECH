package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    //    autowired시 test할때 접근하기 어렵다
//    @Autowired
    private final MemberRepository memberRepository;
    //   제일 안전한 생성사 주입 아래와같이 혹은 @AllArgsConstructor, final로 RequiredArgsConstructor
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    //    setter인젝션방법 하지만 중간에 잘못set하는경우 문제 발생
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
//        EXCEPTION 실무에서는 여기서도 동시에 같은 이름의 회원이 가입하면 validate 메서드를 같이 통과하기에
//        문제가 발생해서 name을 유니크제약을 둔다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회 readOnly로 읽기전용임을 알려줌 성능 최적화 (쓰기에는 쓰면X)
//    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

//        @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
