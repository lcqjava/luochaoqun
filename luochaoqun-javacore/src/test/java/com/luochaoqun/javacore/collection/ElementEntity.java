package com.luochaoqun.javacore.collection;

import java.io.Serializable;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月6日 上午11:24:12 
 * @today: 
 */
public class ElementEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9167282300828935133L;
	private Integer num = 3;
	private String name = "asdf";
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object object){
		
		if(object == null){
			return false;
		}
		
		if(!(object instanceof ElementEntity)){
			return false;
		}
		
		ElementEntity elementEntity = (ElementEntity)object;

		//两个不同的对象hashcode可能相同，hashcode不同两个对象一定不同
		if(elementEntity.hashCode()!=hashCode()){
			return false;
		}
		
		if(elementEntity.getName().equals(this.name)
				&& 
		   elementEntity.getNum().intValue() == this.num.intValue()
				){
			return true;
		}
		
		return true;
	}
	
	@Override
	public int hashCode(){
		return (name+num).hashCode();
	}
	@Override
	public String toString() {
		return "ElementEntity [num=" + num + ", name=" + name + "]";
	}
	
	
}
