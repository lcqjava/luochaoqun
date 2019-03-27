package com.luochaoqun.emq.publis.pressuretest;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.luochaoqun.mqtt.emq.PushCallback;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 
 * @author: 小艾亲亲     
 * @date:   2019年3月4日 下午12:45:26 
 * @today: 
 */
public class PublishThread extends Thread{

	private String HOST = "tcp://192.168.230.129:1883";
	private static int QOS = 1;
	
	private synchronized MqttClient connect(String clientId) throws MqttException{
		MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
 
        MqttClient client = new MqttClient(HOST, clientId, persistence);
        client.setCallback(new PushCallback());
        client.connect(options);
        
        return client;
	}
	
	private void publish(MqttClient client, String msg, String topic) throws MqttException {
        MqttMessage message = new MqttMessage(msg.getBytes());
        message.setQos(QOS);
        message.setRetained(false);
        while(true){
        		client.publish(topic, message);
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        }
    }

	public void start(String msg) throws MqttException {
        /*String msg = "Hello !";*/
        String clientId = "ServerId_01";
        String topic = "MQTT_TOPIC";
 
        MqttClient client = connect(clientId);
        if (client != null) {
            publish(client, msg, topic);
            System.out.println("Start-----Public Message:" + msg);
        }
        if (client != null) {
            client.disconnect();
        }
    }
	
	public void run(){
		start();
	}
	
}
