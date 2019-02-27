package com.luochaoqun.tx.merchantbank.dto;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月27日 下午1:49:25 
 * @today: 
 */
public class TransactionDto {
	
	/** 银行卡 */
	private String cardNo;

	/** 成交额 */
	private String amount;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
