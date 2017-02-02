package util.test;

/**
 * @author shishun.wang
 * @date 下午4:15:25 2017年2月2日
 * @version 1.0
 * @describe
 */
public class TestRsaUtil {

	public static void main(String[] args) {

		/*Map<String, byte[]> keyMap = EncryptUtil.RsaUtil.generateKeyBytes();

		// 加密
		PublicKey publicKey = EncryptUtil.RsaUtil.restorePublicKey(keyMap.get(EncryptUtil.RsaUtil.PUBLIC_KEY));

		byte[] encodedText = EncryptUtil.RsaUtil.RsaEncode(publicKey, "Wangshishun123.".getBytes());
		System.out.println("RSA encoded: " + org.apache.commons.codec.binary.Base64.encodeBase64String(encodedText));

		// 解密
		PrivateKey privateKey = EncryptUtil.RsaUtil.restorePrivateKey(keyMap.get(EncryptUtil.RsaUtil.PRIVATE_KEY));
		System.out.println("RSA decoded: " + EncryptUtil.RsaUtil.RsaDecode(privateKey, encodedText));*/
		
		
//		RsaEncryptionInfo encryptionInfo = EncryptUtil.RsaUtil.encode("Wangshishun123.".getBytes());
//		System.out.println(encryptionInfo);
//		System.out.println("---------------");
//		System.out.println(EncryptUtil.RsaUtil.decode(encryptionInfo));
	}
}
