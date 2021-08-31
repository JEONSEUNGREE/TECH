package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Rollback(value = false)
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    TeamRepository teamRepository;


    @Test
    public void testMember() {
        Member member = new Member("memberA");

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);

    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();

        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo(m2.getUsername());
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testNamedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void testQieru() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA",10);
        assertThat(result.get(0)).isEqualTo(m1);


        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void findUsernameList() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        String result = memberRepository.findUsernameList("AAA");

        System.out.println(result);
    }

    @Test
    public void findMemberDto() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }

    }

    @Test
    public void findByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));

        for (Member member : result) {
            System.out.println("member : " + member);
        }
    }

    @Test
    public void returnType() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

//        List<Member> aaa = memberRepository.findListByUsername("AAA");
        Optional<Member> aaa = memberRepository.findOptionalByUsername("AAA");
//      optional은 empty = nullX
//      dto의경우는 = null

        assertThat(aaa.get().getUsername()).isEqualTo(m1.getUsername());
    }
    @Test
    public void paging() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));
        memberRepository.save(new Member("member6", 9));

        int age = 10;
        int offset = 0;
        int limit = 3;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
//        totalcount쿼리도 자동으로 보냄
//        이 부분에서 성능저하 문제 발생함
//        long totalCount = memberRepository.totalCount(age);
//        따라서 분리하는 방법이있음

//        Slice<Member> pageslice = memberRepository.findByAge(age, pageRequest);
//        슬라이스는 가져오라는 갯수 + 1개 더가져옴 인터페이스도 slice타입으로 설정
        
//          꼭아래와같이 Dto를 통해 api전달
//        Page<MemberDto> memberDtos = page.map(member -> new MemberDto(member.getId(), member.getUsername(), member.getTeam().getName()));

        //then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

        for (Member member : content) {
            System.out.println("member = " + member);
        }
        System.out.println("totalElements = " + totalElements);

        assertThat(content.size()).isEqualTo(3);
//        페이즈 갯수
        assertThat(page.getTotalElements()).isEqualTo(5);
//        페이지 토탈합
        assertThat(page.getNumber()).isEqualTo(0);
//        현재 페이지
        assertThat(page.getTotalPages()).isEqualTo(2);
//        전체 페이지 사이즈 나누기 전체
        assertThat(page.isFirst()).isTrue();
//        현재페이지 여부
        assertThat(page.hasNext()).isTrue();
//        다음페이지 여부
    }
    @Test
    public void bulkUpdate() {
//        벌크수정의 경우 발생할수있는문제
//        보통 영속성 컨텍스트 내에서 작동하는 일반 쿼리와 달리 update를 바로 db에 던진다. 그래서 문제가 발생할수있다.
//
        memberRepository.save(new Member("member1", 12));
        memberRepository.save(new Member("member2", 12));
        memberRepository.save(new Member("member3", 22));
        memberRepository.save(new Member("member4", 25));
        memberRepository.save(new Member("member5", 33));
        memberRepository.save(new Member("member6", 40));

        //when
        int resultCount = memberRepository.bulkAgePlus(20);
        em.clear();

//        바로아래와같은 문제가 발생하는데 아래부분은 쿼리를 날라지않고 1차캐시에서 가져오는 더티체킹때문에
//        나이가 40으로 찍힌다 하지만 실제 db는 41로 반영되있다. 따라서 벌크수정뒤에는 clear를 해줄필요가있다. clear는 영속성컨텍스트안에 데이터를 없앰
//        혹은 modifying(clearAutomoatically = true)옵션을 적용
//        jpql로 업데이트쿼리를하면 미리 flush가 작동한다.
        List<Member> result = memberRepository.findByUsername("member6");
        Member member = result.get(0);

        //then
        assertThat(resultCount).isEqualTo(4);
    }

    @Test
    public void findMemberLazy() {
        //given

        //member1 -> teamA

        //member2 -> teamB

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        //when
//        List<Member> members = memberRepository.findMemberFetchJoin();
        List<Member> members = memberRepository.findEntityGraphByUsername("member1");
        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
//            System.out.println("member = " + member.getTeam().getClass()); proxy객체
            System.out.println("member = " + member.getTeam().getName());
        }
    }

    @Test
    public void queryHint() {
//      다음과같은 코드에서는 변경감지가 작동중이기때문에 변화를 감지해서 굳이 save하지않아도 알아서 트랜잭션이 끝나는 시점에 db동기화가 실행되는데
//        문제는 객체가 두개가 생성되어있다는 점이다.
//        무슨의미냐면 원본객체와 수정한 객체 두개가 있다는 점이다. 메모리 낭비
//        크게 성능에 영향이있는지 여부는 직접 성능 테스트를 해봐야 알 수 있다.

        //given
        Member member1 = memberRepository.save(new Member("member1", 10));

        em.flush();
        em.clear();

        //when
//        Member findMember = memberRepository.findById(member1.getId()).get();
//        아래코드는 읽기전용이되서 snapshot을 생성하지 않음 따라서 save되지않음 (조회전용)
//        변경감지 적용X
        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");

        em.flush();
    }

    @Test
    public void lock() {
        //given
        Member member1 = memberRepository.save(new Member("member1", 10));
        em.flush();
        em.clear();

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");
        em.flush();
    }

//    커스텀 레포 쓰면됨 레포가 지저분해지니깐 커스텀으로 뺴주면 좀더 깔끔한것같음(ex) queryDsl쓸때,jdbcTemplate,myBatis,entityManager...등등)
//    항상필요한건 아닌데 핵심 비즈니스로직과 view에 fit하게 맞춘 (dto) 로직을 나누는건 확실히 좋다 이런 방면에서 사용한다면 좋은것같다.
//    아니면 그냥 클래스로 레포를 하나 생성해도 된다.
    @Test
    public void findMemeberCustom() {
        memberRepository.findMemberCustom();
    }

    @Test
    public void JpaEventBaseEntity() throws Exception {
        //given
        Member member = new Member("member1");
        memberRepository.save(member); // @PrePersist

        Thread.sleep(100);
        member.setUsername("member2");

        em.flush();
        em.clear();

        //when

        Member memberResult = memberRepository.findById(member.getId()).get();

        //then

        System.out.println(memberResult.getCreatedDate());
        System.out.println(memberResult.getLastModifiedDate());
        System.out.println(memberResult.getCreateBy());
        System.out.println(memberResult.getLastModifiedBy());
    }


}