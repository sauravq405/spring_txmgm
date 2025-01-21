package com.demo.tx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PAYMENT_INFO")
public class PaymentInfo {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String paymentId;
    private String accountNo;
    private Double amount;
    private String cardType;
    private Long passengerId;

    public PaymentInfo() {
    }
    public PaymentInfo(String paymentId, String accountNo, Double amount, String cardType, Long passengerId) {
        this.paymentId = paymentId;
        this.accountNo = accountNo;
        this.amount = amount;
        this.cardType = cardType;
        this.passengerId = passengerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCardType() {
        return cardType;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "paymentId='" + paymentId + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", amount=" + amount +
                ", cardType='" + cardType + '\'' +
                ", passengerId=" + passengerId +
                '}';
    }
}
