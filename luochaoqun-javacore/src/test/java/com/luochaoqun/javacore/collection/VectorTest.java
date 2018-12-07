package com.luochaoqun.javacore.collection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.Test;

import com.luochaoqun.javacore.testutil.RemotingSerialiable;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2018年12月7日 下午12:46:27 
 * @today: 
 */
public class VectorTest{

	@Test
	public void testSerialize() throws IOException{
		
		String filePath = "./array.obj";
		ElementEntity elementEntity = new ElementEntity();
		elementEntity.setName("adf");
		elementEntity.setNum(123);
		
		Vector<ElementEntity> vector = new Vector<ElementEntity>();
		vector.add(elementEntity);
		
		ObjectOutputStream outputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
			outputStream.writeObject(vector);
			
			objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
			@SuppressWarnings("unchecked")
			Vector<ElementEntity> elementEntity2 = (Vector<ElementEntity>)objectInputStream.readObject();
			System.out.println(elementEntity2.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null){
				outputStream.close();
			}
			if(objectInputStream!=null){
				objectInputStream.close();
			}
		}
	}
	
	
	@Test
	public void testArrayListSerialize() throws IOException{
		
		String filePath = "./array.obj";
		ElementEntity elementEntity = new ElementEntity();
		elementEntity.setName("adf");
		elementEntity.setNum(123);
		
		ArrayList<ElementEntity> vector = new ArrayList<ElementEntity>();
		vector.add(elementEntity);
		
		ElementEntity elementEntity2 = new ElementEntity();
		elementEntity2.setName("adf");
		elementEntity2.setNum(123);
		vector.add(elementEntity2);
		
		ObjectOutputStream outputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
			outputStream.writeObject(vector);
			
//			objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
//			@SuppressWarnings("unchecked")
//			ArrayList<ElementEntity> elementEntity2 = (ArrayList<ElementEntity>)objectInputStream.readObject();
//			System.out.println();
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
	
}
