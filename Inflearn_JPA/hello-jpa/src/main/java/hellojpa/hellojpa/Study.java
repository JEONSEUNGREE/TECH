package hellojpa.hellojpa;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Study {

    @Id
    @GeneratedValue
    private Long id;

    private String name;


//    일대다 혹은 다대일을 구분짓기위한 방법으로 현재 레퍼런스가
//    컬렉션인지 여부를 확인하면 편하다. 컬렉션이 아니니깐 ManyToOne
//    아래와 같이두면 Account PK Foreign key가 현재 테이블에 생성 constraint foreign key owner_id로 생성
//    또한 현재는 Study가 주인이다. 업데이트는 Study에서 된다는 뜻
//
//    @ManyToOne
//    private Account owner;
//    @ManyToOne
//    private Account owner;
}
