package com.luochaoqun.knowledgescope.algorithm.btree;

import java.util.Objects;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 普通二叉树
 * @author: 小艾亲亲     
 * @date:   2019年3月15日 上午12:13:30 
 * @today: 
 */
public class NormalBtree<T>{
	
	private Node<T> root;
	
	public NormalBtree(){
		this.root = new Node<T>();
	}
	
	public NormalBtree(T value){
		this.root = new Node<T>(value);
	}
	
	public Boolean insert(T value){
		Node<T> targetNode = root;
		do{
			if(root.compareTo(value)>0){
				targetNode = targetNode.getLeft();
			}else if(root.compareTo(value)<0){
				targetNode = targetNode.getRight();
			}else{
				NodeLink<T> oldNodeLink = targetNode.getValue();
				NodeLink<T> newNodeLink = new NodeLink<T>(value);
				newNodeLink.setNext(oldNodeLink);
				return true;
			}
			
		
		}while(targetNode!=null);
		
		return true;
	}
	
}

/**
 * 二叉树节点
 * @author chaoqunluo
 *
 */
class Node<T> implements Comparable<T>{
	private Node<T> left;
	private NodeLink<T> value;
	private Node<T> right;
	
	public Node(){
		
	}
	
	public Node(T value){
		this.value = new NodeLink<T>(value);
		this.left = null;
		this.right = null;
	}
	
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public NodeLink<T> getValue() {
		return value;
	}
	public void setValue(NodeLink<T> value) {
		this.value = value;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}

	@Override
	public int compareTo(T o) {
		return value.hashCode()-o.hashCode();
	}
}

//如果有值相同，每个节点是一个链表
class NodeLink<T> implements Comparable<T>{
	private T value;
	private NodeLink<T> next;
	
	public NodeLink(){
		
	}

	public NodeLink(T value){
		this.value = value;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public NodeLink<T> getNext() {
		return next;
	}
	public void setNext(NodeLink<T> next) {
		this.next = next;
	}

	@Override
	public int compareTo(T o) {
		return value.hashCode() - o.hashCode();
	}
}



