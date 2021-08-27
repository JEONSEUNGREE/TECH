package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    //    manytomany 안쓰는이유는 현 등록상태에서 필드 추가등이 불가능
//    RDB로 봤을때 테이블로연결되어있는 구조라서 joinTable
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
//            중간테이블 카테고리아이디
//            이 테이블에 아이템쪽으로 들어가는 것
            inverseJoinColumns = @JoinColumn(name = "item_id")

    )
    private List<Item> items = new ArrayList<>();

    //    계층구조 연관관계 스스로 할때
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
