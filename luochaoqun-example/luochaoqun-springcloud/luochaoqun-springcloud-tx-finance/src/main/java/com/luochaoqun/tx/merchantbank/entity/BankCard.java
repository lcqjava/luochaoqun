package com.luochaoqun.tx.merchantbank.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 *
 */
public class BankCard implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	/** 银行卡号 */
	private String cardNo;

	/**  */
	private String balance;

	/**  */
	private Date createTime;

	/**  */
	private Date updateTime;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBalance() {
		return this.balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
