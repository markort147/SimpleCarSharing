package simplecarsharing.dao;

import simplecarsharing.model.Car;
import simplecarsharing.model.Company;

import java.util.List;
import java.util.Optional;

public interface CarDao {
    boolean insert(Car car);

    boolean update(Car car);

    boolean delete(int id);

    Optional<Car> getById(int id);

    List<Car> getAllAsList();

    List<Car> getAllByCompany(Company company);

    boolean isRented(Car car);

    List<Car> getAvailableCarsByCompany(Company company);
}
