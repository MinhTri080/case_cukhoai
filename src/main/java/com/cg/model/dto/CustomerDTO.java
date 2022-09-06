package com.cg.model.dto;


import com.cg.model.Customer;
import com.cg.utils.ValidDateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class CustomerDTO implements Validator {
    private Long id;

    @NotEmpty(message = "Họ và tên không được để trống")
    @Size(min = 3, message = "Họ và tên phải từ 4 ký tự")
    @Size(max = 254, message = "họ và tên tối đa 255 ký tự")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "Số điện thoại không được để trống")
    @Size(min = 10, message = "Số điện thoại phải từ 10 số")
    @Size(max = 12, message = "Số điẹn thoại tối đa 12 số")
    private String phone;
    @NotBlank(message = "Vui lòng nhập email")
    @Size(min = 5, max = 20, message = "Độ dài email từ 5-20 ký tự")
    @Email(message = "Vui lòng nhập đúng kiểu email")
    private String email;

    @NotEmpty(message = "Địa chỉ không được để trống")
    @Size(min = 10, message = "địa chỉ tối thiểu 10 ký tự")
    @Size(max = 200, message = "địa chỉ tối đa 200 ký tự")
    private String address;

    public Customer toCustomer() {
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                ;
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return CustomerDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerDTO customerDTO = (CustomerDTO) o;
        String phone = customerDTO.getPhone();


        if (!ValidDateUtils.isPhoneValid(phone)) {

            if (phone == null || phone.equals("")) {
                errors.rejectValue("phone", "400", "SĐT không được để trống!");
            } else {
                errors.rejectValue("phone", "400", "Vui lòng nhập SĐT theo định dạng!");
            }

        }
    }
}
