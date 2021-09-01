package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{

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
            , countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    //    bulkupdate jpa executeUpdate를 실행하기위해 @Modifying을 붙여준다.
    @Modifying
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    //    엔티티그래프는 아래와같은 반복해서 쓰게될 쿼리문을 줄여준다.
    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    // 다음과같이
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    //   응용해서 jpql에 entitygraph도 적용가능
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    //    @EntityGraph(attributePaths = {"team"})
    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    // 아래코드는 읽기전용이되서 snapshot을 생성하지 않음 따라서 save되지않음 (조회전용)
//    변경감지 적용X
    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

//  Lock거는 것....실시간트래픽이 많은경우는 지양하는 경우
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    //    projection
//    List<UsernameonlyDto> findProjectionsByUsername(@Param("username") String username);

    <T>List<T> findProjectionsByUsername(@Param("username") String username, Class<T> type);

    //nativeQuery
    @Query(value = "select * from member where username ?", nativeQuery = true)
    Member findByNativeQuery(String username);

    @Query(value = "select m.member_id as id, m.username, tm.name as teamName"
    + "from member m left join team t", countQuery = "select count(*) t", nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
