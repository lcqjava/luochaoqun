package com.luochaoqun.tx.merchantbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * TransactionRecord 控制层
 *
 */
@RequestMapping("/transaction")
@Controller
public class TransactionRecordController {

	/**
	 * 测试springcloud远程调用
	 * @return
	 */
	@RequestMapping("/testFeign")
	public String testFeign(){
		return "success";
	}
	
}