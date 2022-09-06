package com.cg.controller.rest;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private IUserService userService;
@Autowired
AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> showListUser() {
        List<UserDTO> users = userService.finAllUserDTO();

        if (users.isEmpty()) {
            return new ResponseEntity<>("Danh sách trống!", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<UserDTO> userDTO = userService.findUserDTOById(id);

        if (!userDTO.isPresent()) {
            return new ResponseEntity<>("Không tìm thấy user có id là:" + id + "!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTO.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {

        new UserDTO().validate(userDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

//        System.out.println(customerDTO);
        try {
            User user = userDTO.toUser();
            user.setId(0L);
            user.setDeleted(false);
            user = userService.save(user);

            return new ResponseEntity<>(user.toUserDTO(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Server không xử lý được", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doEdit(@PathVariable Long id,@Validated @RequestBody UserDTO userDTO,
                                    BindingResult bindingResult) {
        Optional<User> user1 = userService.findById(id);

        if (!user1.isPresent()) {
            return new ResponseEntity<>("Không tồn tại sản phẩm", HttpStatus.NOT_FOUND);
        }

        new UserDTO().validate(userDTO,bindingResult);

        if (bindingResult.hasErrors()) {
            return AppUtils.errors(bindingResult);
        }

        try {
            User user = userDTO.toUser();

            user.setId(user1.get().getId());
            user.setDeleted(user1.get().isDeleted());

            userDTO = user.toUserDTO();

            userService.save(user);

            return new ResponseEntity<>(userDTO, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Server không xử lý được", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/block/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doBlock(@PathVariable Long id, @Validated @RequestBody UserDTO userDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return AppUtils.errors(bindingResult);
        }

        try{
            userService.deleteCustomerById(id);

            return new ResponseEntity<>(id,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Server không xử lý được", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
