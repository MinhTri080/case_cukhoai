package com.cg.repository;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
//    @Query(value = "SELECT " +
//            "c.id, " +
//            "c.full_name, " +
//            "c.email, " +
//            "c.phone, " +
//            "c.address " +
//            "FROM customers AS c " +
//            "WHERE c.id LIKE :searchKey " +
//            "OR c.full_name LIKE :searchKey " +
//            "OR c.email LIKE :searchKey " +
//            "OR c.phone LIKE :searchKey " +
//            "OR c.address LIKE :searchKey", nativeQuery = true
//    )
//    List<Customer> findAllBySearchKey(@Param("searchKey") String searchKey);

    @Query("SELECT NEW com.cg.model.dto.CustomerDTO (" +
            "c.id, " +
            "c.fullName, " +
            "c.email, " +
            "c.phone, " +
            "c.address) " +
            "FROM Customer AS c WHERE c.deleted = false")
    List<CustomerDTO> findAllCustomerDTO();

    @Query("SELECT NEW com.cg.model.dto.CustomerDTO (" +
            "c.id, " +
            "c.fullName, " +
            "c.email, " +
            "c.phone, " +
            "c.address) " +
            "FROM Customer AS c WHERE c.id = :id")
    Optional<CustomerDTO> findCustomerDTOById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Customer AS c SET c.deleted = true WHERE c.id = :id")
    void deleteCustomerById(@Param("id") Long id);

}
