package simplecarsharing.dao.impl;

import simplecarsharing.db.DbClient;
import simplecarsharing.model.Car;
import simplecarsharing.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbCustomerDao implements simplecarsharing.dao.CustomerDao {
    @Override
    public boolean insert(Customer customer) {
        String sql = String.format("INSERT INTO CUSTOMER (name) VALUES ('%s')",
                customer.getName()
        );
        int id = DbClient.getInstance().executeSingleInsert(sql);
        customer.setId(id);
        return id > 0;
    }

    @Override
    public boolean update(Customer customer) {
        String sql = String.format("UPDATE CUSTOMER SET name = '%s', rented_car_id = %s WHERE ID = %d",
                customer.getName(),
                customer.getRentedCar() != null ? customer.getRentedCar().getId() : "NULL",
                customer.getId()
        );
        return DbClient.getInstance().execute(sql);
    }

    @Override
    public boolean delete(int id) {
        String sql = String.format("DELETE FROM CUSTOMER WHERE ID = %d", id);
        return DbClient.getInstance().execute(sql);
    }

    @Override
    public Optional<Customer> getById(int id) {
        Customer customer = new Customer();
        String sql = String.format("SELECT * FROM COMPANY WHERE ID = %d", id);
        boolean success = DbClient.getInstance().executeQueryAndHandle(sql,
                resultSet -> {
                    if (resultSet.next()) {
                        customer.setName(resultSet.getString("name"));
                        Optional<Car> car = new DbCarDao().getById(resultSet.getInt("rented_car_id"));
                        car.ifPresent(customer::setRentedCar);
                        customer.setId(id);
                    }
                });
        if (success) {
            return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> getAllAsList() {
        String sql = "SELECT * FROM CUSTOMER";
        List<Customer> customers = new ArrayList<>();
        DbClient.getInstance().executeQueryAndHandle(sql, resultSet -> {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Optional<Car> car = new DbCarDao().getById(resultSet.getInt("rented_car_id"));
                Customer customer = new Customer();
                customer.setName(name);
                customer.setId(id);
                car.ifPresent(customer::setRentedCar);
                customers.add(customer);
            }
        });
        return customers;
    }
}
