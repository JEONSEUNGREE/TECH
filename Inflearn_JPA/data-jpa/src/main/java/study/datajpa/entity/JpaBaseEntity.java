package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
// jpa상속관계는 아니고 테이블에서 속성을 사용할수있게해줌
public class JpaBaseEntity {

    @Column(updatable = false)
//    값을 바꿔도 업데이트가 되지않음
    private LocalDateTime createdDate;

    private LocalDateTime updateDate;

    @PrePersist
//    persist전에 발생함
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
//      생성날짜는 위와같이 수정불가로 만든다.
        createdDate = now;
//      null로 두기보다는 now를 설정해둔다.
        updateDate = now;
    }

    //
    @PreUpdate
    public void preUpdate() {
        updateDate = LocalDateTime.now();
    }


}
