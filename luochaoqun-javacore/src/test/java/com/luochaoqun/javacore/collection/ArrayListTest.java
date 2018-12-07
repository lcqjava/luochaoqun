package com.luochaoqun.javacore.collection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

import com.luochaoqun.javacore.collection.list.LcqList;
import com.luochaoqun.javacore.collection.list.arraylist.LcqArrayList;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月6日 上午9:18:54 
 * @today: 
 */
public class ArrayListTest {

	/**
	 * 测试自定义ArrayList
	 */
	@Test
	public void testLcqArrayList(){
		
		LcqList<ElementEntity> lcqList = new LcqArrayList<ElementEntity>();
		ElementEntity elementEntity = new ElementEntity();
		elementEntity.setName("a");
		lcqList.add(elementEntity);
		
		ElementEntity elementEntity1 = new ElementEntity();
		elementEntity1.setName("a1");
		lcqList.add(elementEntity1);
		
		ElementEntity elementEntity2 = new ElementEntity();
		elementEntity2.setName("a2");
		lcqList.add(elementEntity2);
		
		ElementEntity elementEntity3 = new ElementEntity();
		elementEntity3.setName("a3");
		lcqList.add(elementEntity3);
		
		ElementEntity elementEntity4 = new ElementEntity();
		elementEntity4.setName("a4");
		lcqList.add(elementEntity4);
		
		ElementEntity elementEntity5 = new ElementEntity();
		elementEntity5.setName("a5");
		lcqList.add(elementEntity5);
		
		ElementEntity elementEntity6 = new ElementEntity();
		elementEntity6.setName("a6");
		lcqList.add(elementEntity6);
		
		lcqList.remove(elementEntity6);
		
		System.out.println("lcqArrayList size="+lcqList.size());
		System.out.println("test get:"+lcqList.get(1).toString());
		System.out.println("test contains:"+lcqList.contains(elementEntity));
		
	}
	
	@Test
	public void testArrayListSerialize() throws IOException{
		
		String filePath = "./array.obj";
		ElementEntity elementEntity = new ElementEntity();
		elementEntity.setName("adf");
		elementEntity.setNum(123);
		
		ObjectOutputStream outputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
			outputStream.writeObject(elementEntity);
			
//			objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
//			ElementEntity elementEntity2 = (ElementEntity)objectInputStream.readObject();
//			System.out.println(elementEntity2.toString());
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}finally {
			if(outputStream!=null){
				outputStream.close();
			}
			if(objectInputStream!=null){
				objectInputStream.close();
			}
		}
		
	}
	
	public static void main(String[] args) {

	}
}
