package com.bea.bank.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class AccountNoGenerator {

    /**
     * ThreadLocalRandom 클래스를 사용하여 무작위 숫자를 생성하는 경우,
     * 동시에 여러 스레드에서 해당 메소드를 호출해도 중복되는 값이 나오지 않도록 안전성이 보장된다.
     * 이 클래스는 각 스레드마다 별도의 무작위 숫자 생성기를 가지며, 각각의 생성기는 서로 영향을 주지 않는다.
     * 따라서 여러 스레드에서 동시에 해당 메소드를 호출하더라도 안전하게 사용할 수 있다.
     * TODO 하지만, 만약 여러 서버에서 동시에 해당 메소드를 호출하는 경우에는 중복되는 값이 발생할 수 있다.
     * 이 경우에는 분산 시퀀스 생성기(Distributed Sequence Generator) 등의 기술을 사용하여 중복되지 않는 고유한 식별자를 생성해야 한다.
     */
    public String generate() {
        int accountNo = ThreadLocalRandom.current().nextInt(10000000, 100000000);
        String formattedNumber = String.format("%010d", accountNo);
        return formattedNumber.substring(0, 2) + "-" + formattedNumber.substring(2, 6) + "-" + formattedNumber.substring(6, 10);
    }
}
