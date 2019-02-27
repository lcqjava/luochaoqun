package com.luochaoqun.tx.chinabank.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.luochaoqun.tx.chinabank.dto.TransactionDto;
import com.luochaoqun.tx.chinabank.entity.BankCard;
import com.luochaoqun.tx.chinabank.entity.TransactionRecord;
import com.luochaoqun.tx.chinabank.mapper.BankCardMapper;
import com.luochaoqun.tx.chinabank.mapper.TransactionRecordMapper;
import com.luochaoqun.tx.chinabank.service.IBankCardService;

/**
 *
 * BankCard 表数据服务层接口实现类
 *
 */
@Service
public class BankCardServiceImpl implements IBankCardService {

	@Autowired
	private BankCardMapper bankCardMapper;
	@Autowired
	private TransactionRecordMapper transactionRecordMapper;
	
	@Override
	public boolean savemoney(TransactionDto transactionDto) {
		
		Date now = new Date();
		TransactionRecord transactionRecord = new TransactionRecord();
		transactionRecord.setCardNo(transactionDto.getCardNo());
		transactionRecord.setDescription("存钱");
		transactionRecord.setTransactionAmount(transactionDto.getAmount());
		transactionRecord.setTransactionType(1);
		transactionRecord.setCreateTime(now);
		transactionRecord.setUpdateTime(now);
		
		transactionRecordMapper.insert(transactionRecord);

		BankCard bankCard = new BankCard();
		bankCard.setCardNo(transactionDto.getCardNo());
		List<BankCard> list = bankCardMapper.selectBy(bankCard);
		if(!CollectionUtils.isEmpty(list)){
			BankCard dbBankCard = list.get(0);
			String balanceStr = dbBankCard.getBalance();
			Double balanceDouble = Double.valueOf(balanceStr);
			balanceDouble = balanceDouble+Double.valueOf(transactionDto.getAmount());
			
			dbBankCard.setUpdateTime(now);
			dbBankCard.setBalance(balanceDouble.toString());
			Integer effectRowNum = bankCardMapper.update(dbBankCard);
			return effectRowNum>0;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String balanceStr = "100.98765";
		String amount = "10.00001";
		Double balanceDouble = Double.valueOf(balanceStr);
		balanceDouble = balanceDouble+Double.valueOf(amount);
		String newBanlance = balanceDouble.toString();
		System.out.println(newBanlance);
	}


}