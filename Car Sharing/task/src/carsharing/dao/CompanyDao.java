package carsharing.dao;

import carsharing.model.Company;

import java.util.List;

public interface CompanyDao {
    boolean insert(Company company);
    boolean update(Company company);
    boolean delete(int id);
    Company getById(int id);

    List<Company> getAllAsList();
}
