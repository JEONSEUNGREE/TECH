package freemarkerTest.demo.test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "appUser", cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<AppRole> appRoles = new ArrayList<>();

    public List<AppRole> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(List<AppRole> appRoles) {
        this.appRoles = appRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
