package com.luochaoqun.mqtt.emq;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description:
 * @author: 小艾亲亲
 * @date: 2019年3月3日 下午3:38:12
 * @today:
 */
public class MqttPublish {

	static String broker = "tcp://127.0.0.1:1883";

	public static void main(String[] args) throws MqttException, InterruptedException {
		String clientId = "mqtt-client-id-1";
		String content = "zhongqiyuan";
		for (int i = 0; i < 1; i++) {
			Thread thread = new Thread(new CreateConnect(clientId+i,content+i));
			thread.start();
		}
	}

}

class CreateConnect implements Runnable {
	
	static String broker = "tcp://127.0.0.1:1883";
	String topic = "mqttTest";
	private String clientId;
	private String content;
	
	public CreateConnect(String clientId,String conent){
		this.clientId = clientId;
		this.content = conent;
	}
	
	private void createConnect()
			throws InterruptedException, MqttException {
		
		int qos = 2;
		MemoryPersistence persistence = new MemoryPersistence();

		MqttClient sampleClient = null;
		try {
			sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");
			System.out.println("Publishing message: " + content);
			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			while(true){
				sampleClient.publish(topic, message);
				System.out.println("Message published");
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
