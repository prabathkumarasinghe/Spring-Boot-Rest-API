package lk.pk.cruddemo.dao;

import lk.pk.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee employee);

    void  deleteById(int theId);
}
