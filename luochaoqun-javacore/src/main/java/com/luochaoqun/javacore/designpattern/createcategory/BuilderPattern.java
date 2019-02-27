package com.luochaoqun.javacore.designpattern.createcategory;
/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description:建造者模式，构建复杂对象，可以根据需要创建想要的对象
 * @author: 小艾亲亲     
 * @date:   2019年2月19日 下午10:04:14 
 * @today: 
 */
public class BuilderPattern {

	public static void main(String[] args) {
		Builder builder = new ConstructionTeam();
		builder.buildFlowGarden();
		builder.buildElevatorLift();
		builder.buildWindow();
		
		House house = builder.buildResult();
		System.out.println("house:"+house.toString());
	}
	
}

class House{
	
	//花园
	private String flowerGarden;
	
	//落地窗
	private String frenchWindow;
	
	//电梯
	private String elevatorLift;

	public String getFlowerGarden() {
		return flowerGarden;
	}

	public void setFlowerGarden(String flowerGarden) {
		this.flowerGarden = flowerGarden;
	}

	public String getFrenchWindow() {
		return frenchWindow;
	}

	public void setFrenchWindow(String frenchWindow) {
		this.frenchWindow = frenchWindow;
	}

	public String getElevatorLift() {
		return elevatorLift;
	}

	public void setElevatorLift(String elevatorLift) {
		this.elevatorLift = elevatorLift;
	}

	@Override
	public String toString() {
		return "House [flowerGarden=" + flowerGarden + ", frenchWindow=" + frenchWindow + ", elevatorLift="
				+ elevatorLift + "]";
	}
	
}

interface Builder{
	
	void buildFlowGarden();
	
	void buildWindow();
	
	void buildElevatorLift();
	
	House buildResult();
}

class ConstructionTeam implements Builder{

	private House house;
	public ConstructionTeam() {
		house = new House();
	}
	
	@Override
	public void buildFlowGarden() {
		house.setFlowerGarden("带花园的房子");
	}

	@Override
	public void buildWindow() {
		house.setFrenchWindow("带落地窗的房子");
	}

	@Override
	public void buildElevatorLift() {
		house.setElevatorLift("有电梯的房子");
	}

	@Override
	public House buildResult() {
		return house;
	}
	
}