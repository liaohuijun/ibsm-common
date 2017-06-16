package com.hm.common.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.collections.CollectionUtils;

import com.hm.common.exception.ErrorCode;
import com.hm.common.exception.ServiceException;
import com.hm.common.util.CommonUtil;

/**
 * @author shishun.wang
 * @date 上午10:32:14 2017年6月16日
 * @version 1.0
 * @describe
 */
public class EmailFactory {

	private Properties properties = new Properties();

	private EmailAuthenticator authenticator;

	private Session session;

	private String contentType = "text/html;charset=utf-8";

	public EmailFactory build(String smtpHost, String eamilAccount, String emailPassword) {
		if (CommonUtil.isAnyEmpty(smtpHost, emailPassword, eamilAccount)) {
			throw ServiceException.warning(ErrorCode.REQUIRED_PARAMETERS_MISSING);
		}
		// 初始化properties
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", smtpHost);

		// 验证
		authenticator = new EmailAuthenticator(eamilAccount, emailPassword);
		// 创建session
		session = Session.getInstance(properties, authenticator);

		return this;
	}

	/**
	 * 发送/抄送邮件
	 *
	 * @param email
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(Email email) throws AddressException, MessagingException {
		final MimeMessage message = copyToSetting(email);
		// 设置邮件内容
		message.setContent(email.getContent(), contentType);
		// 发送
		Transport.send(message);
	}

	/**
	 * 发送/抄送邮件 - 附件
	 *
	 * @param email
	 * @param files
	 *            文件
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void send(Email email, File[] files)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		final MimeMessage message = copyToSetting(email);
		// 设置邮件内容
		accessorySetting(email, files, message);
		// 发送
		Transport.send(message);
	}

	/**
	 * @param email
	 * @param files
	 * @param message
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private void accessorySetting(Email email, File[] files, final MimeMessage message)
			throws MessagingException, UnsupportedEncodingException {
		Multipart multipart = new MimeMultipart();
		{
			BodyPart bodyPart = new MimeBodyPart();
			{// html 文本内容描述
				bodyPart.setContent(email.getContent(), contentType);
				multipart.addBodyPart(bodyPart);
			}
			if (null != files) {
				for (File file : files) {
					bodyPart = new MimeBodyPart();
					{
						bodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
						bodyPart.setFileName(MimeUtility.encodeText(file.getName()));
						multipart.addBodyPart(bodyPart);
					}
				}
			}
		}
		message.setContent(multipart);
	}

	/**
	 * @param email
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 */
	private MimeMessage copyToSetting(Email email) throws MessagingException, AddressException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// 设置收件人
		if (CollectionUtils.isEmpty(email.getRecipient())) {
			throw ServiceException.warn("收件人不能为null");
		}
		message.setRecipient(RecipientType.TO, new InternetAddress(email.getRecipient().get(0)));

		List<String> copyTos = email.getCopyTo();
		if (CommonUtil.isNotEmpty(copyTos)) {
			// 为每个邮件接收者创建一个地址
			Address[] copyToAdresses = new InternetAddress[copyTos.size()];
			for (int i = 0; i < copyTos.size(); i++) {
				copyToAdresses[i] = new InternetAddress(copyTos.get(i));
			}
			// 将抄送者信息设置到邮件信息中，注意类型为Message.RecipientType.CC
			message.setRecipients(Message.RecipientType.CC, copyToAdresses);
		}

		// 设置主题
		message.setSubject(email.getTheme());
		return message;
	}

	/**
	 * 群发邮件 - 附件
	 *
	 * @param recipients
	 * @param subject
	 * @param content
	 * @param files
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void send(List<String> recipients, String subject, Object content, File[] files)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		final MimeMessage message = massSetting(recipients, subject);
		// 设置邮件内容
		Multipart multipart = new MimeMultipart();
		{
			BodyPart bodyPart = new MimeBodyPart();
			{// html 文本内容描述
				bodyPart.setContent(content.toString(), contentType);
				multipart.addBodyPart(bodyPart);
			}
			if (null != files) {
				for (File file : files) {
					bodyPart = new MimeBodyPart();
					{
						bodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
						bodyPart.setFileName(MimeUtility.encodeText(file.getName()));
						multipart.addBodyPart(bodyPart);
					}
				}
			}
		}
		message.setContent(multipart);

		// 发送
		Transport.send(message);
	}

	/**
	 * @param recipients
	 * @param subject
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 */
	private MimeMessage massSetting(List<String> recipients, String subject)
			throws MessagingException, AddressException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUsername()));
		// 设置收件人们
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(RecipientType.TO, addresses);
		// 设置主题
		message.setSubject(subject);
		return message;
	}

	/**
	 * 群发邮件
	 *
	 * @param recipients
	 *            收件人们
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void send(List<String> recipients, String subject, Object content)
			throws AddressException, MessagingException {
		final MimeMessage message = massSetting(recipients, subject);
		// 设置邮件内容
		message.setContent(content.toString(), contentType);
		// 发送
		Transport.send(message);
	}

}
