package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
//싱글테이블에서 DB에 저장할때 구분용
@DiscriminatorValue("A")
@Entity
public class Album extends Item{

    private String artist;
    private String etc;

}
