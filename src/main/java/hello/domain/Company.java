package hello.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Victor on 24-Jan-17.
 */
@Entity
@Table(name = "company")
public class Company {

    private Integer companyId;
    private String name;
    private String address;
    private String phone;
    private String legalIdentityNumber;
    private Set<App> apps = new HashSet<App>();
    private Set<Contact> contacts = new HashSet<Contact>();

    public Company(){}

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "legal_identity_number")
    public String getLegalIdentityNumber() {
        return legalIdentityNumber;
    }

    public void setLegalIdentityNumber(String legalIdentityNumber) {
        this.legalIdentityNumber = legalIdentityNumber;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    public Set<App> getApps() {
        return apps;
    }

    public void setApps(Set<App> apps) {
        this.apps = apps;
    }

    public void addApps(App app) {
        this.apps.add(app);
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> companys) {
        this.contacts = companys;
    }

    public void addContacts(Contact contact) {
        this.contacts.add(contact);
    }
}
