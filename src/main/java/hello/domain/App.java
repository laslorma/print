package hello.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Victor on 16-Jan-17.
 */
@Entity
@Table(name = "app")
public class App {

    private Integer appId;
    private String domain;
    private String s3Location;
    private String licenseHashkey;
    private Timestamp initPaymentDate;
    private period paymentPeriod;
    private Timestamp onlineDate;
    private CatwInstance catw;
    private Company company;
    private Set<Contract> contracts = new HashSet<Contract>();
    private Set<Payment> payments = new HashSet<Payment>();

    public App(){}

    @Id
    @GeneratedValue
    @Column(name = "app_id")
    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @NotNull
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Column(name = "s3_location")
    public String getS3Location() {
        return s3Location;
    }

    public void setS3Location(String s3Location) {
        this.s3Location = s3Location;
    }

    @NotNull
    @Column(name = "license_hashkey")
    public String getLicenseHashkey() {
        return licenseHashkey;
    }

    public void setLicenseHashkey(String licenseHashkey) {
        this.licenseHashkey = licenseHashkey;
    }

    @Column(name = "init_payment_date")
    public Timestamp getInitPaymentDate() {
        return initPaymentDate;
    }

    public void setInitPaymentDate(Timestamp initPaymentDate) {
        this.initPaymentDate = initPaymentDate;
    }

    @Column(name = "payment_period")
    public period getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(period paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    @Column(name = "online_date")
    public Timestamp getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Timestamp onlineDate) {
        this.onlineDate = onlineDate;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catw_instance_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    public CatwInstance getCatw() {
        return catw;
    }

    public void setCatw(CatwInstance catw) {
        this.catw = catw;
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

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "app")
    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public void addContract(Contract contract) {
        this.contracts.add(contract);
    }

    @OneToMany(cascade= CascadeType.ALL, mappedBy = "app")
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public void addPayment(Payment payment) {
        this.payments.add(payment);
    }

    public enum period{
        monthly, yearly, quarterly
    }

    public Double averageCostPerDay()
    {
        return null;
    }

    public Double calculatePayment()
    {
        return null;
    }
}
