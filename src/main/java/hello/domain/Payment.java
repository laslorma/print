package hello.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;

/**
 * Created by Victor on 24-Jan-17.
 */
@Entity
@Table(name = "payment")
public class Payment {

    private Integer paymentId;
    private Double amount;
    private String numberConfirmationOp;
    private String bankName;
    private Timestamp paymentDate;
    private String invoiceNumber;
    private Boolean approved;
    private String payerName;
    private String appName;
    private String json;
    private App app;

    public Payment(){}

    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    @NotNull
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "number_confirmation_op")
    public String getNumberConfirmationOp() {
        return numberConfirmationOp;
    }

    public void setNumberConfirmationOp(String numberConfirmationOp) {
        this.numberConfirmationOp = numberConfirmationOp;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "payment_date")
    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Column(name = "invoice_number")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @Column(name = "payer_name")
    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    @Column(name = "app_name")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    public App getApp() { return app; }

    public void setApp(App app) { this.app = app; }
}
