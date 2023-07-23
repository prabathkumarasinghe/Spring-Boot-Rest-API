package lk.pk.cruddemo.rest;

import lk.pk.cruddemo.dao.EmployeeDAO;
import lk.pk.cruddemo.entity.Employee;
import lk.pk.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    //quick and dirty: inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //expose "/employee" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return  employeeService.findAll();
    }

    //add mapping for GET/employees/(employeeId)
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee Id not found - " + employeeId);
        }
        return employee;
    }

        //add mapping for POST /employees - add new employee

        @PostMapping("/employees")
        public Employee addEmployee(@RequestBody Employee employee) {
            //also just in case they pass an id in JASON .. set id to 0
            //this is to force a save of new item .. instead of update

            employee.setId(0);

            Employee dbEmployee = employeeService.save(employee);

            return dbEmployee;
        }

        //add mapping for PUT/employee- update existing employee

    @PutMapping("/employees")
    public  Employee updateEmployee(@RequestBody Employee employee){

        Employee dbEmployee = employeeService.save(employee);

        return dbEmployee;
    }

    //add mapping for DELETE/employees/{employeeid} - delete employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee employee = employeeService.findById(employeeId);

        //throw exception if null

        if(employee == null){
            throw new RuntimeException("Employee id not found - "+ employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;

    }



}
