package ru.innopolis.baki.currencyconv.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tbl_history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "original_currency")
    private String originalCurrency;
    @Column(name = "target_currency")
    private String targerCurrency;
    @Column(name = "initial_amount")
    private String initialAmount;
    @Column(name = "amount_received")
    private String amountReceived;
    private LocalDate date;
    private String originalCurrencyName;
    private String targetCurrencyName;
    @Column(name = "user_id")
    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getOriginalCurrencyName() {
        return originalCurrencyName;
    }

    public void setOriginalCurrencyName(String originalCurrencyName) {
        this.originalCurrencyName = originalCurrencyName;
    }

    public String getTargetCurrencyName() {
        return targetCurrencyName;
    }

    public void setTargetCurrencyName(String targetCurrencyName) {
        this.targetCurrencyName = targetCurrencyName;
    }

    public Long getId() {
        return id;
    }

    public History(){}

    public History(String ordinalCurrency, String targetCurrency, String initialAmount, String amountReceived){
        this.originalCurrency = ordinalCurrency;
        this.targerCurrency = targetCurrency;
        this.initialAmount = initialAmount;
        this.amountReceived = amountReceived;
    }


    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getTargerCurrency() {
        return targerCurrency;
    }

    public void setTargerCurrency(String targerCurrency) {
        this.targerCurrency = targerCurrency;
    }

    public String getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(String initialAmount) {
        this.initialAmount = initialAmount;
    }

    public String getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(String amountReceived) {
        this.amountReceived = amountReceived;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

