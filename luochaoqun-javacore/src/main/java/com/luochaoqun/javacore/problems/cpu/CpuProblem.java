package com.luochaoqun.javacore.problems.cpu;

import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2019年2月27日 下午10:30:32
 * @today:
 */
public class CpuProblem {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		try {
			while (true) {
				list.add("asdfsdf");
				Thread.sleep(100);
			}
		} catch (Exception e) {
			System.out.println("#######:" + list.size());
		}
	}

}
