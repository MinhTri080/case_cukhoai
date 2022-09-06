package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    List<CustomerDTO> findAllCustomerDTO();

    Optional<CustomerDTO> findCustomerDTOById(Long id);

    void deleteCustomerById(Long id);
//    List<Customer> findAllBySearchKey(@Param("searchKey") String searchKey);
}
