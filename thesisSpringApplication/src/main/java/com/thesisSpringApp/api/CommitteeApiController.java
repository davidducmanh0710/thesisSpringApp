package com.thesisSpringApp.api;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thesisSpringApp.Dto.CommitteeUserDto;
import com.thesisSpringApp.Dto.NewCommitteeDto;
import com.thesisSpringApp.pojo.Committee;
import com.thesisSpringApp.pojo.CommitteeUser;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.repository.CommitteeUserRepository;
import com.thesisSpringApp.service.CommitteeService;
import com.thesisSpringApp.service.MailSenderService;
import com.thesisSpringApp.service.UserService;

@RestController
@RequestMapping("/api/committee/")
public class CommitteeApiController {

	private CommitteeService committeeService;
	private UserService userService;
	private CommitteeUserRepository committeeUserRepository;
	private MailSenderService mailSenderService;
	private Environment env;

	@Autowired
	public CommitteeApiController(CommitteeService committeeService, UserService userService,
			CommitteeUserRepository committeeUserRepository, MailSenderService mailSenderService,
			Environment env) {
		super();
		this.committeeService = committeeService;
		this.userService = userService;
		this.committeeUserRepository = committeeUserRepository;
		this.mailSenderService = mailSenderService;
		this.env = env;
	}

	@PostMapping(path = "/add", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
	})
	@CrossOrigin
	public ResponseEntity<Committee> addNewCommittee(@RequestBody NewCommitteeDto newCommitteeDto)
			throws MessagingException {

		Committee committee = new Committee();
		String committeeName = newCommitteeDto.getName();
		committee.setName(committeeName);
		committeeService.saveCommittee(committee);

		List<CommitteeUserDto> committeeUserDtos = newCommitteeDto.getCommitteeUserDtos();

		for (CommitteeUserDto c : committeeUserDtos) {
			CommitteeUser cmU = new CommitteeUser();
			cmU.setRole(c.getRoleName());

			User user = userService.getUserById(c.getUserId());

			if (c.getRoleName().equals("Phản biện"))
//				mailSenderService.sendEmail(env.getProperty("spring.mail.username"), user);
				System.out.println("Đã gửi mail");

			cmU.setUserId(user);
			cmU.setCommitteeId(committee);

			committeeUserRepository.saveCommitteeUser(cmU);

		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
