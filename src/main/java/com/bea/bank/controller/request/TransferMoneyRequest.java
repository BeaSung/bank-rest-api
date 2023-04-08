package com.bea.bank.controller.request;

import lombok.Data;

@Data
public class TransferMoneyRequest {

    private String receivingAccountNo;
    private Long money;
}
