package com.hm.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author shishun.wang
 * @date 下午2:07:13 2016年11月18日
 * @version 1.0
 * @describe
 */
public class EncryptUtil {

	private EncryptUtil() {
	}

	/**
	 * @author
	 * @version 1.0
	 * @date 12:59:51 PM May 31, 2014
	 * @statement Without my written permission, any unit and individual shall
	 *            not in any way or reason of the above products, services,
	 *            information, and any part of the material to use, copy,
	 *            modify, transcribing, spread or with other products bound use
	 *            and marketing. Hereby solemnly statement!
	 * @team chasing (personal<author></author>)
	 * @email
	 * @describe md5加密
	 */
	public static class Md5 {

		private Md5() {
		}

		private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

		// 返回形式为数字跟字符串
		private static String byteToArrayString(byte bByte) {
			int iRet = bByte;
			if (iRet < 0) {
				iRet += 256;
			}
			int iD1 = iRet / 16;
			int iD2 = iRet % 16;
			return strDigits[iD1] + strDigits[iD2];
		}

		// 转换字节数组为16进制字串
		private static String byteToString(byte[] bByte) {
			StringBuffer sBuffer = new StringBuffer();
			for (int i = 0; i < bByte.length; i++) {
				sBuffer.append(byteToArrayString(bByte[i]));
			}
			return sBuffer.toString();
		}

		public static String getMD5Code(String strObj) {
			String resultString = null;
			if (null != strObj) {
				try {
					resultString = new String(strObj);
					MessageDigest md = MessageDigest.getInstance("MD5");
					// md.digest() 该函数返回值为存放哈希值结果的byte数组
					resultString = byteToString(md.digest(strObj.getBytes()));
					return resultString;
				} catch (NoSuchAlgorithmException ex) {
					ex.printStackTrace();
				}
			}
			return UUID.randomUUID().toString();
		}
	}

	/**
	 * @author shishun.wang
	 * @date 上午10:01:03 2016年5月19日
	 * @version 1.0
	 * @describe Base64
	 */
	public static class Base64 {

		private Base64() {
		}

		public static char[] encode(byte[] data) {
			char[] out = new char[((data.length + 2) / 3) * 4];

			for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
				boolean quad = false;
				boolean trip = false;
				int val = (0xFF & (int) data[i]);
				val <<= 8;
				if ((i + 1) < data.length) {
					val |= (0xFF & (int) data[i + 1]);
					trip = true;
				}
				val <<= 8;
				if ((i + 2) < data.length) {
					val |= (0xFF & (int) data[i + 2]);
					quad = true;
				}
				out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
				val >>= 6;
				out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
				val >>= 6;
				out[index + 1] = alphabet[val & 0x3F];
				val >>= 6;
				out[index + 0] = alphabet[val & 0x3F];
			}
			return out;
		}

		public static byte[] decode(char[] data) {
			int len = ((data.length + 3) / 4) * 3;
			if (data.length > 0 && data[data.length - 1] == '=')
				--len;
			if (data.length > 1 && data[data.length - 2] == '=')
				--len;
			byte[] out = new byte[len];
			int shift = 0;
			int accum = 0;
			int index = 0;
			for (int ix = 0; ix < data.length; ix++) {
				int value = codes[data[ix] & 0xFF];
				if (value >= 0) {
					accum <<= 6;
					shift += 6;
					accum |= value;
					if (shift >= 8) {
						shift -= 8;
						out[index++] = (byte) ((accum >> shift) & 0xff);
					}
				}
			}
			if (index != out.length)
				throw new Error("miscalculated data length!");
			return out;
		}

		private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();

		private static byte[] codes = new byte[256];

		static {
			for (int i = 0; i < 256; i++)
				codes[i] = -1;
			for (int i = 'A'; i <= 'Z'; i++)
				codes[i] = (byte) (i - 'A');
			for (int i = 'a'; i <= 'z'; i++)
				codes[i] = (byte) (26 + i - 'a');
			for (int i = '0'; i <= '9'; i++)
				codes[i] = (byte) (52 + i - '0');
			codes['+'] = 62;
			codes['/'] = 63;
		}
	}

	/**
	 * 获取随机密码
	 * 
	 * @return
	 */
	public static String getRandomPwd() {
		char[] chars = Base64.encode(UUID.randomUUID().toString().getBytes());
		String random = new String(chars);
		return random.substring(5, 14);
	}

	/**
	 * @author shishun.wang
	 * @date 下午3:16:30 2016年8月24日
	 * @version 1.0
	 * @describe DESC
	 */
	public static class DesUtil {

		private DesUtil() {
		}

		/**
		 * @param algorithm
		 *            加密算法,可用 DES,DESede,Blowfish
		 * @return 对称密钥
		 */
		public static SecretKey createSecretKey(String algorithm) {
			try {
				KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
				return keyGenerator.generateKey();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 根据密匙进行DES加密
		 * 
		 * @param key
		 *            密匙
		 * @param info
		 *            要加密的信息
		 * @return 加密后的信息
		 */
		public static String encrypt(SecretKey key, String info) {
			// 定义 加密算法,可用 DES,DESede,Blowfish
			String Algorithm = "DES";
			// 加密随机数生成器 (RNG),(可以不写)
			SecureRandom sr = new SecureRandom();
			// 定义要生成的密文
			byte[] cipherByte = null;
			try {
				// 得到加密/解密器
				Cipher c1 = Cipher.getInstance(Algorithm);
				// 用指定的密钥和模式初始化Cipher对象
				// 参数:(ENCRYPT_MODE, DECRYPT_MODE, WRAP_MODE,UNWRAP_MODE)
				c1.init(Cipher.ENCRYPT_MODE, key, sr);
				// 对要加密的内容进行编码处理,
				cipherByte = c1.doFinal(info.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 返回密文的十六进制形式
			return byte2hex(cipherByte);
		}

		/**
		 * 根据密匙进行DES解密
		 * 
		 * @param key
		 *            密匙
		 * @param info
		 *            要解密的密文
		 * @return 返回解密后信息
		 */
		public static String decode(SecretKey key, String info) {
			// 定义 加密算法,
			String Algorithm = "DES";
			// 加密随机数生成器 (RNG)
			SecureRandom sr = new SecureRandom();
			byte[] cipherByte = null;
			try {
				// 得到加密/解密器
				Cipher c1 = Cipher.getInstance(Algorithm);
				// 用指定的密钥和模式初始化Cipher对象
				c1.init(Cipher.DECRYPT_MODE, key, sr);
				// 对要解密的内容进行编码处理
				cipherByte = c1.doFinal(hex2byte(info));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// return byte2hex(cipherByte);
			return new String(cipherByte);
		}

		/**
		 * 将二进制转化为16进制字符串
		 * 
		 * @param b
		 *            二进制字节数组
		 * @return String
		 */
		public static String byte2hex(byte[] b) {
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
				stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
				if (stmp.length() == 1) {
					hs = hs + "0" + stmp;
				} else {
					hs = hs + stmp;
				}
			}
			return hs.toUpperCase();
		}

		/**
		 * 十六进制字符串转化为2进制
		 * 
		 * @param hex
		 * @return
		 */
		public static byte[] hex2byte(String hex) {
			byte[] ret = new byte[8];
			byte[] tmp = hex.getBytes();
			for (int i = 0; i < 8; i++) {
				ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
			}
			return ret;
		}

		/**
		 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
		 * 
		 * @param src0
		 *            byte
		 * @param src1
		 *            byte
		 * @return byte
		 */
		public static byte uniteBytes(byte src0, byte src1) {
			byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
			_b0 = (byte) (_b0 << 4);
			byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
			byte ret = (byte) (_b0 ^ _b1);
			return ret;
		}

		/**
		 * 创建密匙组，并将公匙，私匙放入到指定文件中
		 * 
		 * @param privateListence
		 *            私匙默认放入文件的,文件位置
		 */
		public static void createPairKey(String privateListence) {
			try {
				// 根据特定的算法一个密钥对生成器
				KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
				// 加密随机数生成器 (RNG)
				SecureRandom random = new SecureRandom();
				// 重新设置此随机对象的种子
				random.setSeed(1000);
				// 使用给定的随机源（和默认的参数集合）初始化确定密钥大小的密钥对生成器
				keygen.initialize(512, random);// keygen.initialize(512);
				// 生成密钥组
				KeyPair keys = keygen.generateKeyPair();
				// 得到公匙
				PublicKey pubkey = keys.getPublic();
				// 得到私匙
				PrivateKey prikey = keys.getPrivate();
				// 将公匙私匙写入到文件当中
				doObjToFile(privateListence, new Object[] { prikey, pubkey });
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 将指定的对象写入指定的文件
		 * 
		 * @param file
		 *            指定写入的文件
		 * @param objs
		 *            要写入的对象
		 */
		public static void doObjToFile(String file, Object[] objs) {
			ObjectOutputStream oos = null;
			try {
				FileOutputStream fos = new FileOutputStream(file);
				oos = new ObjectOutputStream(fos);
				for (int i = 0; i < objs.length; i++) {
					oos.writeObject(objs[i]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * 返回在文件中指定位置的对象
		 * 
		 * @param file
		 *            指定的文件
		 * @param i
		 *            从1开始
		 * @return
		 */
		public static Object getObjFromFile(String file, int i) {
			ObjectInputStream ois = null;
			Object obj = null;
			try {
				FileInputStream fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				for (int j = 0; j < i; j++) {
					obj = ois.readObject();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return obj;
		}

		/**
		 * 利用私匙对信息进行签名 把签名后的信息放入到指定的文件中
		 * 
		 * @param info
		 *            要签名的信息
		 * @param signfile
		 *            存入的文件
		 * @param privateListence
		 *            私匙默认放入文件的,文件位置
		 */
		public static void signToInfo(String info, String signfile, String privateListence) {
			// 从文件当中读取私匙
			PrivateKey myprikey = (PrivateKey) getObjFromFile(privateListence, 1);
			// 从文件中读取公匙
			PublicKey mypubkey = (PublicKey) getObjFromFile(privateListence, 2);
			try {
				// Signature 对象可用来生成和验证数字签名
				Signature signet = Signature.getInstance("DSA");
				// 初始化签署签名的私钥
				signet.initSign(myprikey);
				// 更新要由字节签名或验证的数据
				signet.update(info.getBytes());
				// 签署或验证所有更新字节的签名，返回签名
				byte[] signed = signet.sign();
				// 将数字签名,公匙,信息放入文件中
				doObjToFile(signfile, new Object[] { signed, mypubkey, info });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 读取数字签名文件 根据公匙，签名，信息验证信息的合法性
		 * 
		 * @return true 验证成功 false 验证失败
		 */
		public static boolean validateSign(String signfile) {
			// 读取公匙
			PublicKey mypubkey = (PublicKey) getObjFromFile(signfile, 2);
			// 读取签名
			byte[] signed = (byte[]) getObjFromFile(signfile, 1);
			// 读取信息
			String info = (String) getObjFromFile(signfile, 3);
			try {
				// 初始一个Signature对象,并用公钥和签名进行验证
				Signature signetcheck = Signature.getInstance("DSA");
				// 初始化验证签名的公钥
				signetcheck.initVerify(mypubkey);
				// 使用指定的 byte 数组更新要签名或验证的数据
				signetcheck.update(info.getBytes());
				// 验证传入的签名
				return signetcheck.verify(signed);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	/**
	 * @author shishun.wang
	 * @date 下午4:10:12 2017年2月2日
	 * @version 1.0
	 * @describe rsa 加密
	 */
	public static class RsaUtil {

		private RsaUtil() {

		}

		/**
		 * 生成密钥对
		 * 
		 * @param filePath
		 *            生成密钥的路径
		 * @return
		 */
		public static Map<String, String> generateKeyPair(String filePath) {
			try {
				KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
				// 密钥位数
				keyPairGen.initialize(2048);
				// 密钥对
				KeyPair keyPair = keyPairGen.generateKeyPair();
				// 公钥
				PublicKey publicKey = keyPair.getPublic();
				// 私钥
				PrivateKey privateKey = keyPair.getPrivate();
				// 得到公钥字符串
				String publicKeyString = getKeyString(publicKey);
				// 得到私钥字符串
				String privateKeyString = getKeyString(privateKey);
				// 将密钥对写入到文件
				FileWriter pubfw = new FileWriter(filePath + "/publicKey.keystore");
				FileWriter prifw = new FileWriter(filePath + "/privateKey.keystore");
				BufferedWriter pubbw = new BufferedWriter(pubfw);
				BufferedWriter pribw = new BufferedWriter(prifw);
				pubbw.write(publicKeyString);
				pribw.write(privateKeyString);
				pubbw.flush();
				pubbw.close();
				pubfw.close();
				pribw.flush();
				pribw.close();
				prifw.close();
				// 将生成的密钥对返回
				Map<String, String> map = new HashMap<String, String>();
				map.put("publicKey", publicKeyString);
				map.put("privateKey", privateKeyString);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public static RsaCallback generateEncrypt() {
			try {
				KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
				// 密钥位数
				keyPairGen.initialize(2048);
				// 密钥对
				KeyPair keyPair = keyPairGen.generateKeyPair();
				// 公钥
				PublicKey publicKey = keyPair.getPublic();
				// 私钥
				PrivateKey privateKey = keyPair.getPrivate();
				// 得到公钥字符串
				String publicKeyString = getKeyString(publicKey);
				// 得到私钥字符串
				String privateKeyString = getKeyString(privateKey);

				return new RsaCallback(privateKeyString, publicKeyString);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * @author shishun.wang
		 * @date 2017年2月8日 下午2:28:41
		 * @version 1.0
		 * @describe Rsa加密回调
		 */
		public static class RsaCallback {

			private String privateKey;

			private String publicKey;

			public String getPrivateKey() {
				return privateKey;
			}

			public void setPrivateKey(String privateKey) {
				this.privateKey = privateKey;
			}

			public String getPublicKey() {
				return publicKey;
			}

			public void setPublicKey(String publicKey) {
				this.publicKey = publicKey;
			}

			/**
			 * @param privateKey
			 * @param publicKey
			 */
			public RsaCallback(String privateKey, String publicKey) {
				super();
				this.privateKey = privateKey;
				this.publicKey = publicKey;
			}

			@Override
			public String toString() {
				return "RsaCallback [privateKey=" + privateKey + ", publicKey=" + publicKey + "]";
			}

		}

		/**
		 * 得到公钥
		 * 
		 * @param key
		 *            密钥字符串（经过base64编码）
		 * @throws Exception
		 */
		public static PublicKey getPublicKey(String key) throws Exception {
			byte[] keyBytes;
			keyBytes = (new BASE64Decoder()).decodeBuffer(key);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		}

		/**
		 * 得到私钥
		 * 
		 * @param key
		 *            密钥字符串（经过base64编码）
		 * @throws Exception
		 */
		public static PrivateKey getPrivateKey(String key) throws Exception {
			byte[] keyBytes;
			keyBytes = (new BASE64Decoder()).decodeBuffer(key);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			return privateKey;
		}

		/**
		 * 得到密钥字符串（经过base64编码）
		 * 
		 * @return
		 */
		public static String getKeyString(Key key) throws Exception {
			byte[] keyBytes = key.getEncoded();
			String s = (new BASE64Encoder()).encode(keyBytes);
			return s;
		}

		/**
		 * 使用公钥对明文进行加密，返回BASE64编码的字符串
		 * 
		 * @param publicKey
		 * @param plainText
		 * @return
		 */
		public static String encrypt(PublicKey publicKey, String plainText) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				byte[] enBytes = cipher.doFinal(plainText.getBytes());
				return (new BASE64Encoder()).encode(enBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 使用keystore对明文进行加密
		 * 
		 * @param publicKeystore
		 *            公钥文件路径
		 * @param plainText
		 *            明文
		 * @return
		 */
		public static String fileEncrypt(String publicKeystore, String plainText) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				FileReader fr = new FileReader(publicKeystore);
				BufferedReader br = new BufferedReader(fr);
				String publicKeyString = "";
				String str;
				while ((str = br.readLine()) != null) {
					publicKeyString += str;
				}
				br.close();
				fr.close();
				cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKeyString));
				byte[] enBytes = cipher.doFinal(plainText.getBytes());
				return (new BASE64Encoder()).encode(enBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 使用公钥对明文进行加密
		 * 
		 * @param publicKey
		 *            公钥
		 * @param plainText
		 *            明文
		 * @return
		 */
		public static String encrypt(String publicKey, String plainText) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
				byte[] enBytes = cipher.doFinal(plainText.getBytes());
				return (new BASE64Encoder()).encode(enBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 使用私钥对明文密文进行解密
		 * 
		 * @param privateKey
		 * @param enStr
		 * @return
		 */
		public static String decrypt(PrivateKey privateKey, String enStr) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(enStr));
				return new String(deBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 使用私钥对密文进行解密
		 * 
		 * @param privateKey
		 *            私钥
		 * @param enStr
		 *            密文
		 * @return
		 */
		public static String decrypt(String privateKey, String enStr) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
				byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(enStr));
				return new String(deBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 使用keystore对密文进行解密
		 * 
		 * @param privateKeystore
		 *            私钥路径
		 * @param enStr
		 *            密文
		 * @return
		 */
		public static String fileDecrypt(String privateKeystore, String enStr) {
			try {
				Cipher cipher = Cipher.getInstance("RSA");
				FileReader fr = new FileReader(privateKeystore);
				BufferedReader br = new BufferedReader(fr);
				String privateKeyString = "";
				String str;
				while ((str = br.readLine()) != null) {
					privateKeyString += str;
				}
				br.close();
				fr.close();
				cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKeyString));
				byte[] deBytes = cipher.doFinal((new BASE64Decoder()).decodeBuffer(enStr));
				return new String(deBytes);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}
}
