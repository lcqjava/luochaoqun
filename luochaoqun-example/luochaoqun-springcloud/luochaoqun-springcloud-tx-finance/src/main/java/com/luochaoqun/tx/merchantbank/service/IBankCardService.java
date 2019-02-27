package com.luochaoqun.tx.merchantbank.service;

import com.luochaoqun.tx.merchantbank.dto.TransactionDto;

/**
 *
 * BankCard 表数据服务层接口
 *
 */
public interface IBankCardService  {

	boolean savemoney(TransactionDto transactionDto);


}