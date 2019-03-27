package com.luochaoqun.emq.publis.pressuretest;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.luochaoqun.mqtt.emq.PushCallback;

/**
 * All rights Reserved, Designed By www.xiaoaiqinqin.com
 * 
 * @Description: emq 压力测试连接
 * @author: 小艾亲亲
 * @date: 2019年3月4日 下午12:38:17
 * @today:
 */
public class EmqPressureTest {
	private static String HOST = "tcp://47.94.170.165:1883";
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 500; i++) {
			String clientId = "ServerId_" + i;
			String topic = "emq-pressure-test";
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					AtomicInteger atomicInteger = new AtomicInteger();
					MqttClient client = null;

					try {
						synchronized (EmqPressureTest.class) {
							MemoryPersistence persistence = new MemoryPersistence();

							MqttConnectOptions options = new MqttConnectOptions();
							options.setCleanSession(false);
							options.setConnectionTimeout(10);
							options.setKeepAliveInterval(20);

							try {
								client = new MqttClient(HOST, clientId, persistence);
								client.setCallback(new PushCallback());
								client.connect(options);

							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						while (true) {
							try {
								client.publish(topic, new MqttMessage(("压力测试"+Thread.currentThread().getName()+atomicInteger.incrementAndGet()).getBytes()));
							} catch (MqttPersistenceException e) {
								e.printStackTrace();
							} catch (MqttException e) {
								e.printStackTrace();
							}
							System.out.println(Thread.currentThread().getName());
						}
					} catch (Exception e) {

					} finally {
						if (client != null) {
							try {
								client.close();
							} catch (MqttException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
			thread.setName("Thread-" + i);
			thread.start();
		}
	}
}
