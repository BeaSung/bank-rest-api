package com.bea.bank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name="transactionHistories")
public class TransactionHistory {

    @Id
    private Long no;
    private TransactionType transactionType;
    private LocalDateTime transactionDateTime;
    private Long money;
    private Long balance;

    public TransactionHistory(TransactionType transactionType, Long money, Long balance, LocalDateTime transactionDateTime) {
        this.transactionType = transactionType;
        this.transactionDateTime = transactionDateTime;
        this.money = money;
        this.balance = balance;
    }
}
