package com.bea.bank.domain;

import com.bea.bank.domain.exception.OverdrawnAccountException;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {

    // 값에 대해 의미를 부여하기 위해 의미 있는 이름의 상수로 선언
    private static final Long ACCOUNT_OPENING_BALANCE = 0L;
    private static final int MINIMUM_NAME_LENGTH = 2;
    private static final int MAXIMUM_NAME_LENGTH = 10;

    // 엔티티는 식별자로 비교하기 위해서 id필드만 Include를 선언
    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String accountNo;
    private String name;
    private Long balance;
    private List<TransactionHistory> transactionHistories = new ArrayList<>();

    // 계좌 개설용 static factory method
    public static Account open(String accountNo, String name) {
        if (name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 2자 이상 10자 이하로 입력해주세요.");
        }
        return new Account(null, accountNo, name, ACCOUNT_OPENING_BALANCE, new ArrayList<>());
    }

    public void deposit(Long money) {
        if (money < 0) {
            throw new IllegalArgumentException();
        }
        this.balance += money;
        transactionHistories.add(new TransactionHistory(TransactionType.DEPOSIT, money, balance, LocalDateTime.now()));
    }

    public void withdraw(Long money) {
        if (money < 0) {
            throw new IllegalArgumentException();
        }
        if (this.balance < money) {
            throw new OverdrawnAccountException("잔액이 부족합니다.");
        }
        this.balance -= money;
        transactionHistories.add(new TransactionHistory(TransactionType.WITHDRAW, money, balance, LocalDateTime.now()));
    }

    public void transfer(Account receivingAccount, Long money) {
        withdraw(money);
        receivingAccount.deposit(money);
    }

    public void checkCancelableAccount() {
        if (this.balance > 0) {
            throw new IllegalStateException("아직 잔액이 남아있습니다. 출금 후 해지해주세요.");
        }
    }
}
