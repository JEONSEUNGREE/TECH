package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    List<Member> findByUsername(String username);

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query(name = "Member.findByUsername")
//    namedQuery :username -> @Pararm("username") 위에 @Query가 없어도 실행됨 알아서 named쿼리를 먼저찾고 없으면 쿼리메서드로 작성하기때문
//    네임드쿼리는 jpql로 써서 잘안쓰지만 애플리케이션 로딩시점에 오류를 알려줌

    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m where m.username = :username")
    String findUsernameList(@Param("username") String username);

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> name);

    //      optional은 empty = nullX
//      dto의경우는 = null
//    스프링데이터JPA는 OPTIONAL사용시 값이 없으면 optional은 NoresultException을 발생시켜 empty가나옴
//    만약에 단건조회로쓰던 optional에서 값이 하나가아닌 여러개인 경우 NonUniqueException발생 -> IncorrectResultSizeDataAccessException발생

    List<Member> findListByUsername(String username); //컬렉션

    Optional<Member> findOptionalByUsername(String username);// 단건

    Member findMemberListByUsername(String username);// 단건

//    Page<Member> findByAge(int age, Pageable pageable);

//    Slice<Member> findByAge(int age, Pageable pageable);

//    paging에서 카운트 쿼리는 조인할필요가 없는데 조인을해서 성능이 저하된다.
//    그리고 dto로 변환해야한다.
    @Query(value = "select m from Member m left join m.team t"
                    ,countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

}
