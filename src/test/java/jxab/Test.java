package jxab;

import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 2017年3月23日 下午3:39:38
 * @version 1.0
 * @describe
 */
public class Test {

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
			
			System.out.println(CommonUtil.isPhoneLegal("135884507258"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
