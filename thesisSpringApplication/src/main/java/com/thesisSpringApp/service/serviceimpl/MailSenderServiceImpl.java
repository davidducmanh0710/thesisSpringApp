package com.thesisSpringApp.service.serviceimpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.service.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	@Async
	public void sendEmail(String from, User user) throws MessagingException {

		String text = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "  <head>\r\n"
				+ "    <meta charset=\"UTF-8\" />\r\n"
				+ "    <meta\r\n"
				+ "      name=\"viewport\"\r\n"
				+ "      content=\"width=device-width, initial-scale=1, shrink-to-fit=no\"\r\n"
				+ "    />\r\n"
				+ "    <title>Xác nhận đã thay đổi mật khẩu</title>\r\n"
				+ "    <style>\r\n"
				+ "      body {\r\n"
				+ "        margin: 0;\r\n"
				+ "        padding: 0;\r\n"
				+ "        font-family: Arial, sans-serif;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .container {\r\n"
				+ "        max-width: 1200px;\r\n"
				+ "        margin: 0 auto;\r\n"
				+ "        position: relative;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .header {\r\n"
				+ "        border: 1px solid #ddd;\r\n"
				+ "        background: #fff;\r\n"
				+ "        padding: 5rem;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .logo {\r\n"
				+ "        width: 80px;\r\n"
				+ "        height: 80px;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .company-info h2 {\r\n"
				+ "        font-size: 1.5em;\r\n"
				+ "        margin-bottom: 0.5rem;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .company-info p {\r\n"
				+ "        margin: 0;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .content {\r\n"
				+ "        margin-top: 5rem;\r\n"
				+ "        text-align: left;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .content p {\r\n"
				+ "        margin-bottom: 1rem;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .content ul {\r\n"
				+ "        list-style: none;\r\n"
				+ "        padding: 0;\r\n"
				+ "        margin: 0;\r\n"
				+ "      }\r\n"
				+ "\r\n"
				+ "      .content ul li {\r\n"
				+ "        margin-bottom: 0.5rem;\r\n"
				+ "      }\r\n"
				+ "    </style>\r\n"
				+ "  </head>\r\n"
				+ "\r\n"
				+ "  <body>\r\n"
				+ "    <div class=\"container\">\r\n"
				+ "      <div class=\"header\">\r\n"
				+ "        <div style=\"display: flex; align-items: center\">\r\n"
				+ "          <img\r\n"
				+ "            style=\"border-radius: 50%; margin-right: 12px\"\r\n"
				+ "            src=\"\"\r\n"
				+ "            alt=\"Logo\"\r\n"
				+ "            class=\"logo\"\r\n"
				+ "          />\r\n"
				+ "          <div class=\"company-info\">\r\n"
				+ "            <h2>HỆ THỐNG THESISOU</h2>\r\n"
				+ "            <p>Nhơn Đức - Nhà Bè- TP HCM </p>\r\n"
				+ "          </div>\r\n"
				+ "        </div>\r\n"
				+ "\r\n"
				+ "        <div class=\"content\">\r\n"
				+ "          <p>Xin chào " + user.getFirstName() + " ! </p>"
				+ "          <p> Nhà trường đã triển khai thành công hệ thống quản lý khóa luận tốt nghiệp."
				+ "          Vì vậy , quản trị viên đã cấp tài khoản cho người dùng tên :"
				+ user.getFirstName() + " " + user.getLastName()
				+ " để đăng nhập vào hệ thống</p>\r\n"
				+ "          <p>\r\n"
				+ "            Tuy nhiên , bạn phải đổi mật khẩu và upload hình ảnh lên hệ thống để có thể tham gia hoạt phần mềm quản lý khóa luận tốt nghiệp này !"
				+ "          </p>\r\n"
				+ "          <p>\r\n"
				+ "            Hãy nhớ sử dụng mật khẩu vừa mạnh vừa duy nhất cho tài khoản của\r\n"
				+ "            bạn.\r\n"
				+ "          </p>\r\n"
				+ "          <p>\r\n"
				+ "            Nếu bạn có bất kỳ câu hỏi hoặc cần sự hỗ trợ, vui lòng liên hệ qua quản trị viên của hệ thống này"
				+ "          </p>\r\n"
				+ "          <p>Trân trọng!</p>\r\n"
				+ "        </div>\r\n"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "  </body>\r\n"
				+ "</html>\r\n"
				+ "";
		String subject = "Thư gửi tài khoản đăng nhập hệ thống khóa luận tốt nghiệp ";

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		helper.setTo(user.getEmail());
		helper.setSubject(subject);
		helper.setText(text, true); // Set nội dung là HTML
		javaMailSender.send(mimeMessage);
	}

}
