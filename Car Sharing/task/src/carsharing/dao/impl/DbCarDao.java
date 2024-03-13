package carsharing.dao.impl;

import carsharing.dao.CarDao;
import carsharing.db.DbClient;
import carsharing.model.Car;
import carsharing.model.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbCarDao implements CarDao {
    @Override
    public boolean insert(Car car) {
        String sql = String.format("INSERT INTO CAR (name, company_id) VALUES ('%s',%d)",
                car.getName(),
                car.getCompany().getId()
        );
        int id = DbClient.getInstance().executeSingleInsert(sql);
        car.setId(id);
        return id > 0;
    }

    @Override
    public boolean update(Car car) {
        String sql = String.format("UPDATE CAR SET name = '%s', company_id = %d WHERE ID = %d",
                car.getName(),
                car.getCompany().getId(),
                car.getId()
        );
        return DbClient.getInstance().execute(sql);
    }

    @Override
    public boolean delete(int id) {
        String sql = String.format("DELETE FROM CAR WHERE ID = %d", id);
        return DbClient.getInstance().execute(sql);
    }

    @Override
    public Optional<Car> getById(int id) {
        Car car = new Car();
        String sql = String.format("SELECT * FROM COMPANY WHERE ID = %d", id);
        DbClient.getInstance().executeQueryAndHandle(sql,
                resultSet -> {
                    if (resultSet.next()) {
                        car.setName(resultSet.getString("name"));
                        Company company = new DbCompanyDao().getById(resultSet.getInt("company_id"));
                        car.setCompany(company);
                        car.setId(id);
                    }
                });
        return car.getId() > 0 ? Optional.of(car) : Optional.empty();
    }

    @Override
    public List<Car> getAllAsList() {
        String sql = "SELECT * FROM CAR";
        List<Car> carList = new ArrayList<>();
        DbClient.getInstance().executeQueryAndHandle(sql, resultSet -> {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Company company = new DbCompanyDao().getById(resultSet.getInt("company_id"));
                carList.add(new Car(id, name, company));
            }
        });
        return carList;
    }

    @Override
    public List<Car> getAllByCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company must not be null");
        }
        int companyID = company.getId();
        String sql = "SELECT * FROM CAR WHERE company_id = " + companyID;
        List<Car> carList = new ArrayList<>();
        DbClient.getInstance().executeQueryAndHandle(sql, resultSet -> {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                carList.add(new Car(id, name, company));
            }
        });
        return carList;
    }

    @Override
    public boolean isRented(Car car) {
        String sql = "SELECT count(*) FROM CUSTOMER WHERE rented_id = " + car.getId();
        return DbClient.getInstance().executeAsInt(sql) > 0;
    }

    @Override
    public List<Car> getAvailableCarsByCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company must not be null");
        }
        int companyID = company.getId();
        String sql = String.format(
                """
                        SELECT CAR.*
                        FROM CAR
                        JOIN COMPANY ON CAR.company_id = COMPANY.id
                        LEFT JOIN CUSTOMER ON CAR.id = CUSTOMER.rented_car_id
                        WHERE company_id = %d
                        AND CUSTOMER.rented_car_id IS NULL""", companyID);
        List<Car> carList = new ArrayList<>();
        DbClient.getInstance().executeQueryAndHandle(sql, resultSet -> {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                carList.add(new Car(id, name, company));
            }
        });
        return carList;
    }

}
