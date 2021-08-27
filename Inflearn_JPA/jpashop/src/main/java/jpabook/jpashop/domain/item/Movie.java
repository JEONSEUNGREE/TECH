package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
//싱글테이블에서 DB에 저장할때 구분용
@DiscriminatorValue("M")
@Setter
public class Movie extends Item{

    private String director;
    private String actor;
}
