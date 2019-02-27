package com.luochaoqun.tx.chinabank.service;

import com.luochaoqun.tx.chinabank.dto.TransactionDto;

/**
 *
 * BankCard 表数据服务层接口
 *
 */
public interface IBankCardService  {

	boolean savemoney(TransactionDto transactionDto);


}