package simplecarsharing.dao;

import simplecarsharing.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    boolean insert(Customer customer);
    boolean update(Customer customer);
    boolean delete(int id);
    Optional<Customer> getById(int id);
    List<Customer> getAllAsList();
}
