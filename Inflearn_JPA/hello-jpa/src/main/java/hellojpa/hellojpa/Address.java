package hellojpa.hellojpa;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
//Account에 종속적인 관계
public class Address {

    private String street;

    private String city;

    private String state;

    private String zipCode;
}
