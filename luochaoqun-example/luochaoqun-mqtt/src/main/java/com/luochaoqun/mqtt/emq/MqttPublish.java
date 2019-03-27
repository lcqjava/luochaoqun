package com.luochaoqun.mqtt.emq;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.fusesource.mqtt.client.QoS;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2019年3月3日 下午3:38:12
 * @today:
 */
public class MqttPublish {

	static String broker = "tcp://47.94.170.165:1883";

	public static void main(String[] args) throws MqttException, InterruptedException {
		String clientId = "mqtt-publish-id-1";
		String content = "测试mqtt-";
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(new CreateConnect(clientId+i,content+i));
			thread.start();
		}
	}

}

class CreateConnect implements Runnable {
	
	static String broker = "tcp://47.94.170.165:1883";
	String topic = "mqttTest";
	private String clientId;
	private String content;
	
	public CreateConnect(String clientId,String conent){
		this.clientId = clientId;
		this.content = conent;
	}
	
	private void createConnect()
			throws InterruptedException, MqttException {
		
		int qos = QoS.AT_LEAST_ONCE.ordinal();
		MemoryPersistence persistence = new MemoryPersistence();

		MqttClient sampleClient = null;
		try {
			sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			//清空回话信息
			connOpts.setCleanSession(true);
			//连接超时时间，默认30秒
			Integer connectionTimeout = 30;
			connOpts.setConnectionTimeout(connectionTimeout);
			sampleClient.connect(connOpts);
			
			int i = 0;
			while(true){
				String msg = content+i++;
				MqttMessage message = new MqttMessage(msg.getBytes());
				message.setQos(qos);
				message.setRetained(true);
				sampleClient.publish(topic, message);
				System.out.println(msg+" Message published");
				Thread.sleep(1000);
			}
			// sampleClient.disconnect();
			// System.out.println("Disconnected");
			// System.exit(0);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		} finally {
			if (sampleClient != null) {
				sampleClient.close();
			}
		}
	}
	
	public void run() {
		try {
			createConnect();
		} catch (InterruptedException | MqttException e) {
			e.printStackTrace();
		}
	}
}
