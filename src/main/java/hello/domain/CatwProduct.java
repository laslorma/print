package hello.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Victor on 25-Jan-17.
 */
@Entity
@Table(name = "catw_product")
public class CatwProduct {

    private Integer catwProductId;
    private String name;
    private type type;
    private String description;

    private Set<Contract> contracts = new HashSet<Contract>();

    public CatwProduct(){}

    public CatwProduct(String name, type type, String description)
    {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    @Id
    @GeneratedValue
    @Column(name = "catw_product_id")
    public Integer getCatwProductId() {
        return catwProductId;
    }

    public void setCatwProductId(Integer catwProductId) {
        this.catwProductId = catwProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public type getType() {
        return type;
    }

    public void setType(type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @OneToMany(cascade= CascadeType.ALL, mappedBy = "catw")
    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    public enum type{
        directive,module,service
    }
}
