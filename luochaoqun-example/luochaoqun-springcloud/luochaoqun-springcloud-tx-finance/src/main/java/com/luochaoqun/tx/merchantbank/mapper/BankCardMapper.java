package com.luochaoqun.tx.merchantbank.mapper;

import java.util.List;

import com.luochaoqun.tx.merchantbank.entity.BankCard;

/**
 *
 * BankCard 表数据库控制层接口
 *
 */
public interface BankCardMapper {

	Integer update(BankCard bankCard);

	List<BankCard> selectBy(BankCard bankCard);


}