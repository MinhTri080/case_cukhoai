package com.cg.model.dto;

import com.cg.model.Role;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Validator {

    private Long id;

    @NotBlank(message = "Không được để trống")
    @Email(message = "Bạn phải nhập đúng định dạng")
    @Size(min=6 ,max = 50, message = "Độ dài email phải từ 7-50 ký tự")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min=7,max = 16, message = "Mật khẩu tối thiểu là 8,tối đa là 16")
    private String password;

    private String fullName;

    private String phone;

    private String address;

    private String status;
    @Valid
    private RoleDTO role;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role.toRoleDTO();
    }

    public UserDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserDTO(Long id, String username, String fullName, String phone, String address, String status, RoleDTO role) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.role = role;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setPhone(phone)
                .setAddress(address)
                .setRole(role.toRole());
    }
    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        String usenameCheck = userDTO.getUsername();
        String fullnameCheck = userDTO.getFullName();
        String passwordCheck = userDTO.getPassword();
        String phoneCheck = userDTO.getPhone();
        String addressCheck = userDTO.getAddress();

        if (phoneCheck.trim().isEmpty()){
            errors.rejectValue("username","username.isEmpty", "vui long nhap email");
        }
    }

}
