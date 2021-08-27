package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
//싱글테이블에서 DB에 저장할때 구분용
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

}
