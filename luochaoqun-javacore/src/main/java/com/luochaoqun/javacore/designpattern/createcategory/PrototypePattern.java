package com.luochaoqun.javacore.designpattern.createcategory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2019年2月19日 下午11:30:34
 * @today:
 */
public class PrototypePattern implements Serializable,Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5232789992565357536L;
	private String name;
	private Reference reference;

	public PrototypePattern(String name, Reference reference) {
		this.name = name;
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	/**
	 * 浅层复制
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public PrototypePattern normalClone() throws CloneNotSupportedException {
		PrototypePattern prototypePattern = (PrototypePattern) super.clone();
		return prototypePattern;
	}

	public PrototypePattern deepClone() throws IOException, ClassNotFoundException{
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		ObjectOutputStream oos = new ObjectOutputStream(bos); 
		oos.writeObject(this); 

		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray()); 
		ObjectInputStream ois = new ObjectInputStream(bis); 
		return (PrototypePattern) ois.readObject(); 
	}

	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		Reference reference = new Reference();
		reference.setAttribute("haha");

		PrototypePattern prototypePatternA = new PrototypePattern("luochaoqun", reference);
		PrototypePattern prototypePatternB = prototypePatternA.normalClone();
		// 相等
		System.out.println("浅层复制：" + (prototypePatternA.getName().equals(prototypePatternB.getName())));
		// 是同一个引用
		System.out.println("浅层复制：" + (prototypePatternA.getReference() == prototypePatternB.getReference()));

		PrototypePattern prototypePatternC = prototypePatternA.deepClone();
		System.out.println("深层复制：" + (prototypePatternA.getName().equals(prototypePatternC.getName())));
		System.out.println("深层复制,不是同一个引用：" + (prototypePatternA.getReference() == prototypePatternC.getReference()));
	}

}

class Reference implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5467030464654939237L;
	private String attribute;

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

}
