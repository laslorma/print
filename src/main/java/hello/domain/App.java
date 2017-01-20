package hello.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 * Created by Victor on 16-Jan-17.
 */
@Entity
@Table(name = "app")
public class App {

    private Integer id;

    private String domain;

    public App(String domain)
    {
        this.domain = domain;
    }

    public App(Integer id, String domain)
    {
        this.id = id;
        this.domain = domain;
    }

    public App(){}

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
