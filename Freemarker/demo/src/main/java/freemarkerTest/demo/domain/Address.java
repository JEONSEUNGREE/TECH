package freemarkerTest.demo.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    String city;
    String street;
    String zipcode;
}
