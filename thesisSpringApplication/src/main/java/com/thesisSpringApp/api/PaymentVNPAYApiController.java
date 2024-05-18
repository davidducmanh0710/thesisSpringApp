package com.thesisSpringApp.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thesisSpringApp.Dto.PaymentInitDto;
import com.thesisSpringApp.config.PaymentVnPayConfig;
import com.thesisSpringApp.pojo.Paymentvnpaydetail;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.service.PaymentvnpaydetailService;
import com.thesisSpringApp.service.UserService;

@RestController
@RequestMapping("/api/payment")
public class PaymentVNPAYApiController {

	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse resp;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentvnpaydetailService paymentvnpaydetailService;

	@PostMapping(path = "/")
	@CrossOrigin
	public ResponseEntity<String> payment(@RequestBody PaymentInitDto paymentInitDto)
			throws UnsupportedEncodingException {

		User user = userService.getUserById(paymentInitDto.getUser_id());


		String vnp_Version = "2.1.0";
		String vnp_Command = "pay";
		String orderType = "billpayment";
		long amount = paymentInitDto.getAmount() * 100;
		String bankCode = "";

		String vnp_TxnRef = PaymentVnPayConfig.getRandomNumber(8);
		String vnp_IpAddr = PaymentVnPayConfig.getIpAddress(req);

		String vnp_TmnCode = PaymentVnPayConfig.vnp_TmnCode;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", vnp_Version);
		vnp_Params.put("vnp_Command", vnp_Command);
		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");

		if (bankCode != null && !bankCode.isEmpty()) {
			vnp_Params.put("vnp_BankCode", bankCode);
		}
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo",
				"Thanh toan tien photo cho sinh vien co ma so : " + user.getUsername());
		vnp_Params.put("vnp_OrderType", orderType);

		String locate = req.getParameter("language");
		if (locate != null && !locate.isEmpty()) {
			vnp_Params.put("vnp_Locale", locate);
		} else {
			vnp_Params.put("vnp_Locale", "vn");
		}
		vnp_Params.put("vnp_ReturnUrl", PaymentVnPayConfig.vnp_ReturnUrl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(
						URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = PaymentVnPayConfig.hmacSHA512(PaymentVnPayConfig.secretKey,
				hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = PaymentVnPayConfig.vnp_PayUrl + "?" + queryUrl;

		return new ResponseEntity<String>(paymentUrl, HttpStatus.OK);	
	}
	


	@GetMapping("/payment_return/")
	public ResponseEntity<Map<String, String>> payment_return (@RequestParam Map<String, String> params) { 
		
		String vnpResponseCode = params.get("vnp_ResponseCode");
		Long amount = Long.parseLong(params.get("vnp_Amount")) / 100;
		
		String username = paymentvnpaydetailService.extractLastWord(params.get("vnp_OrderInfo"));
		User user = userService.getUserByUsername(username);
		
		Paymentvnpaydetail paymentvnpaydetail = new Paymentvnpaydetail();
		paymentvnpaydetail.setOrderId(params.get("vnp_TxnRef"));
		paymentvnpaydetail.setAmount(amount);
		paymentvnpaydetail.setOrderDesc(params.get("vnp_OrderInfo"));
		paymentvnpaydetail.setVnpTransactionNo(params.get("vnp_TransactionNo"));
		paymentvnpaydetail.setVnpResponseCode(params.get("vnp_ResponseCode"));
		paymentvnpaydetail.setUserId(user);
		paymentvnpaydetailService.saveVnPay(paymentvnpaydetail);
		
		params = new HashMap<>();
		if (vnpResponseCode.equals("00")) {
			params.put("result", "Thành công");
			params.put("statusResponseCode", vnpResponseCode);
			params.put("username", username);
			params.put("amount", amount.toString());
		} else  {
			params.put("result", "Lỗi");
			params.put("statusResponseCode", vnpResponseCode);
			params.put("username", username);
			params.put("amount", amount.toString());
		}

		return new ResponseEntity<Map<String, String>>(params, HttpStatus.OK);
	}
	
}
