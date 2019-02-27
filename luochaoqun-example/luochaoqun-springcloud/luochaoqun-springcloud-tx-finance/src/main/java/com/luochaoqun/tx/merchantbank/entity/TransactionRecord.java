package com.luochaoqun.tx.merchantbank.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 *
 */
public class TransactionRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	/**  */
	private Long id;

	/** 银行卡 */
	private String cardNo;

	/** 成交额 */
	private String transactionAmount;

	/** 交易类型：1转入，2转出 */
	private Integer transactionType;

	/**  */
	private Date createTime;

	/**  */
	private Date updateTime;

	/** 本次交易备注 */
	private String description;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Integer getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
