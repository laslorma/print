package hello.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Victor on 24-Jan-17.
 */
@Entity
@Table(name = "contact")
public class Contact {

    private Integer contactId;
    private String name;
    private String lastname;
    private String address;
    private String phone;
    private Company company;

    public Contact(){}

    @Id
    @GeneratedValue
    @Column(name = "contact_id")
    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
