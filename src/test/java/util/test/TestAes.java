package util.test;

import com.hm.common.util.EncryptUtil;
import com.hm.common.util.StringUtil;

/**
 * @author shishun.wang
 * @version 1.0
 * @date 2017年4月28日 下午4:30:59
 * @describe
 */
public class TestAes {

    public static void main(String[] args) {
        String content = StringUtil.token() + ":" + System.currentTimeMillis();
        System.out.println(content);
        String token = EncryptUtil.AES.encrypt(content);
        System.out.println(token);
        System.out.println(token.length());
        String decContent = EncryptUtil.AES.decrypt(token);
        System.out.println(decContent);
        System.out.println(EncryptUtil.AES.validation(content, token));

        System.out.println(StringUtil.isNumberic("1234567890"));

        System.out.printf(EncryptUtil.AES.encrypt("nihao123:"+System.currentTimeMillis()));

    }
}
