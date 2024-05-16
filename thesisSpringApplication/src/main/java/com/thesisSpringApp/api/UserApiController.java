package com.thesisSpringApp.api;

import java.util.List;

import com.thesisSpringApp.service.ThesisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thesisSpringApp.Dto.UserListsByRoleDTO;
import com.thesisSpringApp.pojo.Role;
import com.thesisSpringApp.pojo.User;
import com.thesisSpringApp.service.RoleService;
import com.thesisSpringApp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private ThesisUserService thesisUserService;

    @Autowired
    public UserApiController(UserService userService, PasswordEncoder passwordEncoder,
                             RoleService roleService, ThesisUserService thesisUserService) {
        super();
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.thesisUserService = thesisUserService;
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

    @PostMapping(path = "/{userId}/setInitAcc/", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin
    public ResponseEntity<User> changePassAndUploadAvatar(
            @PathVariable int userId,
            @RequestParam("password") String password,
            @RequestPart("avatar") MultipartFile[] files) {
        User user = userService.getUserById(userId);

        user.setPassword(passwordEncoder.encode(password));
        if (files.length > 0)
            user.setFile(files[0]);
        user.setActive(true);
        userService.saveUser(user);
        userService.setCloudinaryField(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
