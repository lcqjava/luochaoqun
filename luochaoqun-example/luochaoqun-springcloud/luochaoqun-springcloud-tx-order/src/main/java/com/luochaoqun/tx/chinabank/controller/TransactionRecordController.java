package com.luochaoqun.tx.chinabank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luochaoqun.tx.chinabank.service.ITransactionRecordService;

/**
 *
 * TransactionRecord 控制层
 *
 */
@RequestMapping("transaction/")
@RestController
public class TransactionRecordController {

	@Autowired
	private ITransactionRecordService transactionService;
	
	/**
	 * 测试springcloud远程调用
	 * @return
	 */
	@RequestMapping("testFeign")
	public String testFeign(){
		return "success";
	}
	
	/**
	 * 测试单点事物
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("testSingleNodeTx")
	public String testSingleNodeTx() throws Exception{
		boolean result = transactionService.addTestSingleNodeTx();
		return result?"success":"failed";
	}
	
}