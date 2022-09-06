package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import com.cg.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }


    @Override
    public List<CustomerDTO> findAllCustomerDTO() {
      return  customerRepository.findAllCustomerDTO();
    }

    @Override
    public Optional<CustomerDTO> findCustomerDTOById(Long id) {
        return customerRepository.findCustomerDTOById(id);
    }

    @Override
    public void deleteCustomerById(Long id) {
    customerRepository.deleteCustomerById(id);
    }
//    @Override
//    public List<Customer> findAllBySearchKey(String searchKey) {
//        return customerRepository.findAllBySearchKey(searchKey);
//    }

}
