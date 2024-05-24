package com.thesisSpringApp.api;

import java.util.ArrayList;
import java.util.List;

import com.thesisSpringApp.Dto.*;
import com.thesisSpringApp.pojo.*;
import com.thesisSpringApp.service.CommitteeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thesisSpringApp.JwtComponents.JwtService;
import com.thesisSpringApp.service.RoleService;
import com.thesisSpringApp.service.ThesisUserService;
import com.thesisSpringApp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private ThesisUserService thesisUserService;
	private JwtService jwtService;
    private CommitteeUserService committeeUserService;

    @Autowired
    public UserApiController(UserService userService, PasswordEncoder passwordEncoder,
			RoleService roleService, ThesisUserService thesisUserService,
                             CommitteeUserService committeeUserService, JwtService jwtService) {
        super();
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.thesisUserService = thesisUserService;
		this.jwtService = jwtService;
        this.committeeUserService = committeeUserService;
    }

	@PostMapping("/login/")
	@CrossOrigin
	public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
		if (this.userService.authUser(userLoginDto.getUsername(),
				userLoginDto.getPassword())) {
			String token = jwtService.generateTokenLogin(userLoginDto.getUsername(),
					userLoginDto.getPassword());

			return new ResponseEntity<>(token, HttpStatus.OK);
		}

		return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/current-user/", produces = {
			MediaType.APPLICATION_JSON_VALUE
	})
	@CrossOrigin
	public ResponseEntity<CurrentUserDetailDto> getCurrentUserApi() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			User user =  userService.getUserByUsername((authentication.getName()));
			CurrentUserDetailDto c = new CurrentUserDetailDto(user, user.getFacultyId(),
					user.getRoleId());
			return new ResponseEntity<CurrentUserDetailDto>(c, HttpStatus.OK);
		}
		return null;
	}


    @GetMapping("/all/")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(
                this.userService.getAllUsers(),
                HttpStatus.OK);
    }

    @GetMapping("/role/get2RoleList/")
    @CrossOrigin
    public ResponseEntity<UserListsByRoleDTO> getUsersByRoleNameApi() {
        Role role1 = roleService.getRoleByName("ROLE_GIANGVIEN");
        Role role2 = roleService.getRoleByName("ROLE_SINHVIEN");

        UserListsByRoleDTO dto = new UserListsByRoleDTO();
        dto.setUsersGiangVien(userService.getUserByRoleName(role1));
        dto.setUsersSinhVien(userService.getUserByRoleName(role2));

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	@GetMapping("/lecturers/")
    @CrossOrigin
    public ResponseEntity<List<User>> getLecturers() {
        Role role = roleService.getRoleByName("ROLE_GIANGVIEN");

        List<User> users = userService.getUserByRoleName(role);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/students/")
    @CrossOrigin
    public ResponseEntity<List<User>> getStudents() {
        Role role = roleService.getRoleByName("ROLE_SINHVIEN");

        List<User> users = userService.getUserByRoleName(role);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/students/noneThesis/")
    public ResponseEntity<List<User>> getStudentsNoneThesis() {
        Role role = roleService.getRoleByName("ROLE_SINHVIEN");

        List<User> users = userService.getUserByRoleName(role);

        thesisUserService.getStudentInThesisUsers().forEach(thesisUser -> {
            users.remove(thesisUser.getUserId());
        });

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

	@PostMapping(path = "/init-account/", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE
			})
    @CrossOrigin
    public ResponseEntity<CurrentUserDetailDto> changePassAndUploadAvatar(
            @RequestParam("password") String password,
			@RequestPart("avatar") MultipartFile files) {

        if (password == null || password.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.getCurrentLoginUser();

		if (user != null) {
            user.setPassword(passwordEncoder.encode(password));
            user.setActive(true);
            userService.saveUser(user);

            if (files != null && !files.isEmpty()) {
                user.setFile(files);
                userService.setCloudinaryField(user);
            }

			CurrentUserDetailDto c = new CurrentUserDetailDto(user, user.getFacultyId(),
					user.getRoleId());

			return new ResponseEntity<>(c, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

	@GetMapping(path = "/theses/")
	@CrossOrigin
	public ResponseEntity<List<Thesis>> getThesesCurrentUser() {
		User user = userService.getCurrentLoginUser();

		List<ThesisUser> thesisUsers = thesisUserService.getThesisByUser(user);

		List<Thesis> theses = new ArrayList<>();

		if (thesisUsers != null) {
			for (int i = 0; i < thesisUsers.size(); i++)
				theses.add(thesisUsers.get(i).getThesisId());
		return new ResponseEntity<List<Thesis>>(theses, HttpStatus.OK);
	}

		return new ResponseEntity<List<Thesis>>(HttpStatus.NO_CONTENT);
	}

    @GetMapping("/committees/")
    @CrossOrigin
    public ResponseEntity<List<CommitteeDetailDTO>> getCommitteeCurrentUser() {
        User user = userService.getCurrentLoginUser();

        List<CommitteeUser> committeeUserList = committeeUserService.getCommitteeUserByUser(user);

		List<CommitteeDetailDTO> committeeList = new ArrayList<>();

        if (committeeUserList != null) {
            for (CommitteeUser c : committeeUserList) {
                CommitteeDetailDTO committee = new CommitteeDetailDTO();
                committee.setId(c.getCommitteeId().getId());
                committee.setName(c.getCommitteeId().getName());


                List<CommitteeUserDetailDTO> memberList = new ArrayList<>();

                for (CommitteeUser m: committeeUserService.getAllUsersOfCommittee(c.getCommitteeId().getId())) {
                    CommitteeUserDetailDTO member = new CommitteeUserDetailDTO();
                    member.setRole(m.getRole());
                    member.setUser(m.getUserId());

                    memberList.add(member);
                }

                committee.setMembers(memberList);
                committeeList.add(committee);
            }
        }
        return new ResponseEntity<>(committeeList, HttpStatus.OK);
    }

}
