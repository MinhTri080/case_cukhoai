package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);
    List<UserDTO> finAllUserDTO();

    Optional<UserDTO> findUserDTOById(Long id);

    Optional<UserDTO> findUserDTOByUsername(String username);

    void deleteCustomerById(Long id);
}
