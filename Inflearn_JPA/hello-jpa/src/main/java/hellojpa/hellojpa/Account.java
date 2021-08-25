package hellojpa.hellojpa;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

//    아래와같이 설정시 테이블이 3개로 생성된다. jointable로 accountid와 studies_id 두개의 컬럼을 갖는 테이블
//    참고로 아래의 경우는 study나 account나 둘다 관계정보를 갖고있지않는다 새로 생성된 조인테이블에만 관계정보가 들어가있다.
//    양방향관계에서 mappedBy를 쓰면 FK를 가진 쪽이 주인이고 mappedBY를 쓴쪽이 종속이된 부분이라고 보면됨
//    필요없는 스키마 생성을 막고 주인을 정하기위한부분
//    그렇기때문에 관계정보를 Study에다가 업데이트 하지않는다면 null값 발
//    @OneToMany(mappedBy = "owner")
//    private Set<Study> studies = new HashSet<>();
//
//
//    @Transient
//    private String no;

//    한쪽만해줘도 되지만 객체지향적인관점에서 양쪽을 매핑해주는것이 맞기때문에아래와같이 설정한다.
//    public void addStudy(Study study) {
//        this.getStudies().add(study);
//        study.setOwner(this);
//    }
//
//    public void remove(Study study) {
//        this.getStudies().remove(study);
//        study.setOwner(null);
//    }


    //    밸류타입 매핑
//    @Embedded
//    private Address homeAddress;
//
//    //    여러번 Embedded를 하는 경우
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "street", column = @Column(name = "home_street")),
//            @AttributeOverride(name = "city", column = @Column(name = "city"))
//    })
//    private Address address;


}
