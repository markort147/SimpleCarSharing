package carsharing.dao.impl;

import carsharing.dao.CompanyDao;
import carsharing.db.DbClient;
import carsharing.model.Company;

import java.util.ArrayList;
import java.util.List;

public class DbCompanyDao implements CompanyDao {
    @Override
    public boolean insert(Company company) {
        String sql = String.format("INSERT INTO COMPANY (name) VALUES ('%s')",
                company.getName()
        );
        int id = DbClient.getInstance().executeSingleInsert(sql);
        company.setId(id);
        return id > 0;
    }

    @Override
    public boolean update(Company company) {
        String sql = String.format("UPDATE COMPANY SET name = '%s' WHERE ID = %d",
                company.getName(),
                company.getId()
        );
        return DbClient.getInstance().execute(sql);
    }

    @Override
    public boolean delete(int id) {
        String sql = String.format("DELETE FROM COMPANY WHERE ID = %d", id);
        return DbClient.getInstance().execute(sql);
    }

    @Override
    public Company getById(int id) {
        Company company = new Company();
        String sql = String.format("SELECT * FROM COMPANY WHERE ID = %d", id);
        boolean success = DbClient.getInstance().executeQueryAndHandle(sql,
                resultSet -> company.setName(resultSet.getString("name")));
        if(success) {
            company.setId(id);
        }
        return company;
    }

    @Override
    public List<Company> getAllAsList() {
        String sql = "SELECT * FROM COMPANY";
        List<Company> companyList = new ArrayList<>();
        DbClient.getInstance().executeQueryAndHandle(sql, resultSet -> {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                companyList.add(new Company(id, name));
            }
        });
        return companyList;
    }
}
