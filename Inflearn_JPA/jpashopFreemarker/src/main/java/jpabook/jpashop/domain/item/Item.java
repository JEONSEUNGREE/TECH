package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//상속관계전략 부모클래스에서 잡아준다. 싱글테이블 전략설정
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
//    추상클래스로 싱글테이블전략
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

//    도메인주도설계 비즈니스 로직
//    재고 증가
    public void addStock(int stockQuantity) {
        this.stockQuantity += stockQuantity;
    }

    //    재고 감소
    public void removeStock(int stockQuantity) {
        int resctStock = this.stockQuantity -= stockQuantity;
        if (resctStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = resctStock;
    }
}
