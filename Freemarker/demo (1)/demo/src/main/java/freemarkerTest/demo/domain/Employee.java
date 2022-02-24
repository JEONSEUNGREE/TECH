package freemarkerTest.demo.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Getter @Table(name = "employees") @NoArgsConstructor
public class Employee {

    @Id @GeneratedValue
    private Long emp_id;

    private String emp_name;

    private String emp_address;

    private String emp_email;

    @Builder
    public Employee(String emp_name, String emp_address, String emp_email) {
        this.emp_name = emp_name;
        this.emp_address = emp_address;
        this.emp_email = emp_email;
    }
}
