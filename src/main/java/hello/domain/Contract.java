package hello.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.security.Timestamp;
import java.sql.Time;

/**
 * Created by Victor on 25-Jan-17.
 */
@Entity
@Table(name = "contract")
public class Contract {

    private Integer contractId;
    private String jsonSetting;
    private Double price;
    private Timestamp initPayment;
    private period paymentPeriod;
    private Timestamp onlineDate;
    private Timestamp finalDate;
    private String description;
    private App app;
    private CatwProduct catw;

    @Id
    @GeneratedValue
    @Column(name = "contract_id")
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    @Column(name = "json_setting")
    public String getJsonSetting() {
        return jsonSetting;
    }

    public void setJsonSetting(String jsonSetting) {
        this.jsonSetting = jsonSetting;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "init_payment")
    public Timestamp getInitPayment() {
        return initPayment;
    }

    public void setInitPayment(Timestamp initPayment) {
        this.initPayment = initPayment;
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

    @Column(name = "final_date")
    public Timestamp getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Timestamp finalDate) {
        this.finalDate = finalDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catw_product_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    public CatwProduct getCatw() {
        return catw;
    }

    public void setCatw(CatwProduct catw) {
        this.catw = catw;
    }

    public enum period{
        monthly, yearly, quarterly, one_time
    }
}
