package com.luochaoqun.knowledgescope.netsafe;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

/**  
 * All rights Reserved, Designed By www.xiaoaiqinqin.com   
 * @Description: 安全哈希算法（secure hash algorithm）
 * 				主要用于数字签名，校验数据的完整性。SHA1会产生一个160位的消息摘要，
 *              当接受到消息的时候，这个消息摘要可以用来校验数据的完整性，在传输过程中，
 *              数据发生变化，都会产生不同的消息摘要。
 *              特性：1.消息摘要不可还原；
 *                   2.两个不同消息有10的48次方分之一的概率重复
 * @author: 小艾亲亲     
 * @date:   2019年3月1日 下午9:14:41 
 * @today: 
 */
public class SHA1Encrypt {
	private static final char[] HEX = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式  
        for (int j = 0; j < len; j++) {
            buf.append(HEX[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
	
	public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	public static void main(String[] args) {
		String message = "luocdsfsdsfafsdfdafhaoqun";
		String en = encode(message);
		System.out.println(en);
		
		String sha1 = DigestUtils.shaHex(message.getBytes());
		System.out.println(sha1.length());
		
	}
}
