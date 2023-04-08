package com.bea.bank.controller.request;

import com.bea.bank.domain.TransactionHistory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WithdrawMoneyRequest {

    private Long money;
}
