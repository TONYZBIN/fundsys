package edu.fjut.fundsys.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.NewsAddress;

public class SendMail extends Thread {
	private String clientEmail;
	private String checkCode;
	private String clientName;
	private Object object = new Object();

	public SendMail(String clientEmail, String checkCode, String clientName) {
		super();
		this.clientEmail = clientEmail;
		this.checkCode = checkCode;
		this.clientName = clientName;
	}

	// �����ʼ�
	public void run() {
		try {
			synchronized (object) {
				Properties props = new Properties();
				props.setProperty("mail.host", "smtp.163.com");
				props.setProperty("mail.transport.protocol", "smtp");
				props.setProperty("mail.smtp.auth", "true");// ������֤����JavaMail��ʵ���й�
				Session session = Session.getInstance(props);
				// session.setDebug(true);//����ģʽ
				MimeMessage msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("13067415626@163.com"));// ���Ե�ʱ�����Լ���������Ϊ����ʹ�ã���Ҫ�������smtpЭ��
				// �����ռ��ˣ�������(TO)������(CC)������(BCC)
				msg.setRecipients(Message.RecipientType.TO, this.clientEmail);
				// ��������
				msg.setSubject("��������-ģ��������ϵͳ");
				// �����ʼ���������
				msg.setContent("�𾴵� [" + this.clientName + " ],������ļ�����:<br/>"
						+ this.checkCode, "text/html;charset=UTF-8");
				msg.saveChanges();// ����
				// �����ʼ�
				Transport ts = session.getTransport();
				ts.connect("13067415626", "rxc1666166");// �˺źͼ�����,��������,���������.
				ts.sendMessage(msg, msg.getAllRecipients());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("�����ʼ�ʧ�ܣ�����");
		}
	}
}

/*
 * //�ʼ���������:��װ���� //�ı����� MimeBodyPart textPart = new MimeBodyPart();
 * textPart.setContent("��ʼ�� aaa<img src='cid:mm'/>aaa",
 * "text/html;charset=UTF-8");
 * 
 * //ͼƬ���� MimeBodyPart imagePart = new MimeBodyPart(); //�����ݽ�����Ҫ�õ�JAF��API
 * DataHandler dh = new DataHandler(new
 * FileDataSource("src/1.jpg"));//�Զ�̽���ļ���MIME���� imagePart.setDataHandler(dh);
 * imagePart.setContentID("mm");
 * 
 * //�������� MimeBodyPart attachmentPart = new MimeBodyPart(); DataHandler dh1 =
 * new DataHandler(new FileDataSource("src/��.rar"));//�Զ�̽���ļ���MIME���� String name
 * = dh1.getName(); System.out.println(name);
 * attachmentPart.setDataHandler(dh1); //�ֹ����ø���������
 * attachmentPart.setFileName(MimeUtility.encodeText(name));
 * 
 * //������ϵ�� MimeMultipart mmpart = new MimeMultipart();
 * mmpart.addBodyPart(textPart); mmpart.addBodyPart(imagePart);
 * mmpart.setSubType("related");//�й�ϵ��
 * 
 * //���ı�ͼƬ����һ������ MimeBodyPart textImagePart = new MimeBodyPart();
 * textImagePart.setContent(mmpart);
 * 
 * MimeMultipart multipart = new MimeMultipart();
 * multipart.addBodyPart(textImagePart); multipart.addBodyPart(attachmentPart);
 * multipart.setSubType("mixed");//���ӹ�ϵ
 * 
 * msg.setContent(multipart);
 * 
 * msg.saveChanges();
 */