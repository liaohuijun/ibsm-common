package jxab;

import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.hm.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @version 1.0
 * @date 2017年3月23日 下午3:39:38
 * @describe
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        try {
            JxabModel model = new JxabModel();
            {
                model.setId(UUID.randomUUID().toString());
                model.setName("Tom");
                model.setAge(25);
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(JxabModel.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(model, System.out);

            System.out.println(StringUtil.isPhoneLegal("135884507258"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
