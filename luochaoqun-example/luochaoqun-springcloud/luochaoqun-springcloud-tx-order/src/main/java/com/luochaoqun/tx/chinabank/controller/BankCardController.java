package com.luochaoqun.tx.chinabank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luochaoqun.tx.chinabank.dto.TransactionDto;
import com.luochaoqun.tx.chinabank.service.IBankCardService;

/**
 *
 * BankCard 控制层
 *
 */
@RequestMapping("/bankcard")
@RestController
public class BankCardController {

	@Autowired
	private IBankCardService bankCardService;
	
	/**
	 * 存钱
	 * @param transactionDto
	 * @return
	 */
	@PostMapping("/savemoney")
	public String savemoney(@RequestBody TransactionDto transactionDto){
		
		boolean savemoneyResult = bankCardService.savemoney(transactionDto);
		return savemoneyResult?"success":"failed";
	}
	
	

	
	
}