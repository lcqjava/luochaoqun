package com.luochaoqun.tx.chinabank.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.luochaoqun.tx.chinabank.entity.BankCard;
import com.luochaoqun.tx.chinabank.entity.TransactionRecord;
import com.luochaoqun.tx.chinabank.mapper.BankCardMapper;
import com.luochaoqun.tx.chinabank.mapper.TransactionRecordMapper;
import com.luochaoqun.tx.chinabank.service.ITransactionRecordService;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;

/**
 *
 * TransactionRecord 表数据服务层接口实现类
 *
 */
@Service
public class TransactionRecordServiceImpl implements ITransactionRecordService {

	@Autowired
	private TransactionRecordMapper transactionRecordMapper;
	@Autowired
	private BankCardMapper bankCardMapper;
	
	@Override
	public boolean addTestSingleNodeTx()  {
		
		TransactionRecord transactionRecord = new TransactionRecord();
		transactionRecord.setCardNo("asdf");
		transactionRecord.setCreateTime(new Date());
		transactionRecord.setDescription("asdf");
		transactionRecord.setTransactionAmount("0.001");
		transactionRecord.setTransactionType(1);
		transactionRecord.setUpdateTime(new Date());
		transactionRecordMapper.insert(transactionRecord);
		
		
		List<BankCard> list = bankCardMapper.selectBy(new BankCard());
		if(CollectionUtils.isEmpty(list)){
			throw new IllegalAccessError("银行卡信息为空，模拟事物失败");
		}
		
		transactionRecordMapper.insert(transactionRecord);
		
		throw new IllegalAccessError();
		
	}


}