package util.test;

import org.junit.Test;

import com.hm.common.util.EncryptUtil;
import com.hm.common.util.EncryptUtil.RsaUtil;
import com.hm.common.util.EncryptUtil.RsaUtil.RsaCallback;

/**
 * @author shishun.wang
 * @date 下午4:15:25 2017年2月2日
 * @version 1.0
 * @describe
 */
public class TestRsaUtil {

	@Test
	public void testGenerationPwd() {
		RsaUtil.generateKeyPair("D:/tmp/RSA");

		System.out.println(RsaUtil.generateEncrypt());
	}

	@Test
	public void test() {
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAviSuCu4Yg/WAyjp06qiaE/ioI2M/ACT9UTUVxWtM7IZlXMQZPjLn0H1x0zmJ/VLIhnBliyb06QLvtrrBFRt4jnOJR5LjoTg/g8XYdVXN6a+XFjqFvOUPgzZ7OdywOoXxiO+M7WrvT0XgqyBqCnDADpY1eucDqfIDYYOBHKbtMkh0N4ZVBcfULb1Sm+Q7ed+jUa8eXPQPhMrWvhQkIeZJh+hCIrNjXUxyfZPh1tSvqoJYArbyHZs8LnbUtjIQCx9OlR9+xJTx3L9h89I4D+hqA4CZqxUzfibsu5XgYKnoSri2OCR2FefSfYlCd8Fysp0wET/r1L141qnhoMQtrUs8jwIDAQAB";
		String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC+JK4K7hiD9YDKOnTqqJoT+KgjYz8AJP1RNRXFa0zshmVcxBk+MufQfXHTOYn9UsiGcGWLJvTpAu+2usEVG3iOc4lHkuOhOD+Dxdh1Vc3pr5cWOoW85Q+DNns53LA6hfGI74ztau9PReCrIGoKcMAOljV65wOp8gNhg4Ecpu0ySHQ3hlUFx9QtvVKb5Dt536NRrx5c9A+Eyta+FCQh5kmH6EIis2NdTHJ9k+HW1K+qglgCtvIdmzwudtS2MhALH06VH37ElPHcv2Hz0jgP6GoDgJmrFTN+Juy7leBgqehKuLY4JHYV59J9iUJ3wXKynTARP+vUvXjWqeGgxC2tSzyPAgMBAAECggEAMhFkhtpFOFIoFJgp+zRkRgf+9jqG91nGHmEVF4P2oH2PKUs1vmwXII43r8AB9uOai9QC2Q5sBQNR7dLlTtKJ/zCrIF6sc+JkzyUEp3jtnLAw35iPaLsER6/L6OOUwARPIpi5ijbTRxOGYmlJovAnkm+5K2CzVUe13jKLh+joool/ReZk0Rsr4tVLSLmvzDA/sRwYun0x0+jl5EZSQfwsVyN9bD5rY/In/EuvH9yj5R4lPe+mimF4Os6IgTsP5LzqDTAiFx5NNioFRJ2SkcTmM0CZQeMIBuvvF2HCtJlDEfCytD7wYup3GBvar2ccOe9T3YhJdsj5bfAJHVJtamxQwQKBgQDnYReMMzqAh2HOFL8QymzOjImsrOz6NCZatq38TU1hSe9PK+C0sFGhkd788y4AuURS1Btu4i7F+hOYcj1z3L+NSPGE3yLHVjakMrrNbA9rwG/t7oU0cG7d0WWM9bcTQiCSNcUyt69BGH3dZdqee1tITzqghE7+gh9RYiVcI6/8LwKBgQDSYEuWFLMUsR/s7unSHCucuEXjwbYrvknv8Y81sjvrWktNXrJoYlbGy/7HYA6lxzchtSxhPuUSjopwQ5scgMhqf8Gxz7jsDN9ak2dErF7cWRFYfh6aKhkbEw9oG01jIX15MK0TbMafoJslDhPQF1cP9i0+ZGg+gPbASdeUVRTNoQKBgQCOjwDOLgYeiMtXCOtL8hymCmsNDCKaaiUzgRijuhEyHzamJhe13Gj/TnwAh+hRI9UX333jjNJawqDuLXz1dQ5Eg6vjPQQVo2XZNzRnOuwpbJDKHUrPK3Lzkn+qIP6ii/y7eQu+GvSM/AUYsxfGy6RLYh1yJvLw1sVrBDiWk5prmwKBgFvgrmI3XBa3XKgPl5KptupVGEDmAveLvaLLLq5WzxB0eNqrduNbv2ZHBVhxvTPtk0hnZaB65XR7SD7LZ9zE6cKJVUCg5bRB0vIt2jYFydAWHhs1yYuuwxQt+NaQxfV7VN8uwQfww7ZHYDqIsWJ6Lw3Lh+rt0xEpJZrJJRulJNbBAoGBAK1OEnfBpSB99N8gdhp+ZGLsDfwFCQ2Cd4Jpsd4hxdwbXevNuA1OiE20sHPuKqEqfOKocgTMobCwbSfnymatRydVoeUumkEc4Ja+XDgH+P1eXQLdIuRCwh0AXl+vkuOCBDMw367Zp/j6vwPlNKh9ZmOBPwhV0Syv2Z8uGkTZ6g+f";

		System.err.println("公钥加密——私钥解密");
		String source = "高可用架构对于互联网服务基本是标配。";
		System.out.println("\r加密前文字：\r\n" + source);
		String aData = RsaUtil.encrypt(publicKey, source);
		System.out.println("加密后文字：\r\n" + aData);
		String dData = RsaUtil.decrypt(privateKey, aData);
		System.out.println("解密后文字: \r\n" + dData);
	}

	@Test
	public void testBase64() {
		String encode = String.valueOf(EncryptUtil.Base64.encode("MIIEvgIBADANBgkqh".getBytes()));
		System.out.println(encode);
		System.out.println(new String(EncryptUtil.Base64.decode(encode.toCharArray())));
	}

	@Test
	public void test11() {
		// RsaCallback rsaCallback = EncryptUtil.RsaUtil.generateEncrypt();
		// System.out.println(rsaCallback.toString());
		// System.out.println("------------------------------");
		// String aData = RsaUtil.encrypt(rsaCallback.getPublicKey(),
		// "8a9c8ea65a21ad4c015a21af33910001");
		// System.out.println("加密后文字：\r\n" + aData);
		// System.out.println("------------------------------");
		String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCvbR8yWguN/OmYmF11oWPpScUy"
				+ "6czdGp9qJCDLFAvxt9XLIs/O32wOo6oJv2ECZ/5LlZcVGlwqfr2sTuTQrUq0IMVJVqWnMSAwOEbe"
				+ "PSrHpQPRdUiEu3+Ku2H2dYHu+lkzeCUE2HItEJ84WCG4KY6N4rNGItTzzAOIF+sg8v3hmCHGTbq+"
				+ "7Ks7wkV5XwsdEfdkyjhXt3xRgN/QI9J9fitRmzCCXoAgZCbqqaAuNc/0mmA+FDx6IelDIR6Iogah"
				+ "5U9ZxWefoQZRstFU832mSZWamCLA36vLVnfdMkOx1UcKNqMPkHpoxK1BcDjnwHENd4rfUAYSZiWO"
				+ "zuNDzKflwCpPAgMBAAECggEALqXSvN3q4qfVKgmiGIyA9+Dw+jfnjQfuShgvTDEZd/5NcIXiQn1w"
				+ "y3fD2HG2hyBGI600jp0C0x3+q9oSyFa8Xae1LmpHBG1wxMHCPoh/XrVHjaQlU35UTPzE8PeQRI73"
				+ "t1MEbQRtYARigmYVGVabnKkiyMysWePvMHpDwPA8iIcsfU5NLfTNzRUc57FVdQbJ3Ppz1I8t/um9"
				+ "3efUT05aHc2Ci2WXVl8uKaaKQRwQzMtZdBbALGmTh8uEourttHHg038skNZJErd28J6+4SrHofq0"
				+ "gmM/y7hnsgyiuov1MVo7lkwYFfL87hdq6CXmvOmyabfl3qZ1S81DIjuaG6uaOQKBgQD5+cMEXPBM"
				+ "a25zeE4ngcCH71TR4GpjazbUrkD6lSvwezdnTULk3WYvVjO1Wta+dVZIY9dnEGV1iqNntJVgOr2t"
				+ "hZ7SKK1AHez7U2x0SGoHCjvJCP8MTq02uPK4FEFFMp/Eyor7qX+NVJBQXWdvi+kQY9YWLVMNvUaj"
				+ "cjekpzo1VQKBgQCzp2x1eq4A6v2imlYa/zDXGWDDim1c2nuB56y5CsnfWMhJ75uw8cdqdP/TtYCQ"
				+ "Ahdyc/qFEmTbeDjmjJq4mSLEDPERW9KAxymwGtt+8g4GGUelMX+Mvj+VknnaRumjXf7FOvaunug+"
				+ "GNnXax6wWO7HwnA7r7dbEJZpR+Xj1VVhEwKBgQClY/P80gUHetkkOwfyqa2aAuaqygF57/jiNLy3"
				+ "TrU4sj2Q8pR82mN+lFk393c3omjJEo31GesXHvhIaDmg5pySz2Kqh9FBJ8ug6axLL5I/QC0YMQSW"
				+ "vwNpvmqREo2w2/LLgHr+UUIcX0PweCw0qGw2tOdz2t8lqwKup6ZbIW2o1QKBgFg/2O5IiisXQ+pZ"
				+ "CA0Q6CvxYnALkrc2986TQGxRWWLmWNTA5+jei8fGm1ii4vlanavyPtCZfYO1pbXSwboGN2a4uL5/"
				+ "LYNm0hqYxtOwEaV7S49hJje7yL4vir3ncaN3a8v0r/KNSqYeTPwduaCEvx7Pa5RWN6p7Fne3Ar2N"
				+ "MPiTAoGAH74mtcDbYGvOMWKyLcYoM0xXJwOuVE6b0kfxvsW4AnP8+lJgPs3FP4GbiKC/kDfSDURZ"
				+ "KALeKfuN1vIJrZuE7fdWMMGA/3fNpuxpVFeeits/FyXTFWFnPbvOmygDAkEIhQzUegRyoPyYaIaUGLtL0P/jR+R7EBNxL7Zvqh94+Fo=";
		String str = "de7qlVLUoieYhn+rSxojDnTCJv8YKSnY4UUaPiwM5weTgfI+El7ZMTilK3KWpGmhgXc5ruhxo5cI2P4/H/iA0clKbJiXIhIy7hKlZjgZNgFFDTrfY0LGO3CWD0raMuOKH7CCw1g+PPOeGHZ+iaS2G0zolHw1bB22xA5O6YLFUIWSfZCgO4iXy/9wN9XLb1m//v0HmXgxg0mnKUR1mNpqJ/nVrh69/NWyv8JQs4NoMDuH4s8BP0mj4rD5wr5d75AVrZJmSgUGk30zRLlqrh3QaO4bq+HNLFhKvuP4tW7fpo80es5GvHYXGltwQEpIF4r7vkrtJpR9kC0k4nME7XsJJg==";
		System.out.println(RsaUtil.decrypt(privateKey, str));
	}
}
