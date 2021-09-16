package com.pep.restaurant.service;

import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.logging.Logger;
import com.pep.restaurant.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

private static final Logger LOGGER = new Logger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Create Employee.
     *
     * @param employee employee.
     * @return employee created.
     */
    public Employee createEmployee(final Employee employee) {
        final Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        if (employeeOptional.isEmpty()) {
            return employeeRepository.save(employee);
        }
        throw new NullPointerException("Employee already exists!!!");
    }

    /**
     * Get Employee.
     *
     * @param employeeId employee id.
     * @return employee retrieved.
     */
    public Employee getEmployee(final long employeeId) {
        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new NullPointerException("Employee to get not exists!!!");
        }
        return employeeOptional.get();
    }

    /**
     * Edit Employee.
     *
     * @param employeeId  employee id.
     * @param employeeNew employee new.
     * @return employee edited.
     */
    public Employee editEmployee(final long employeeId, final Employee employeeNew) {
        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new NullPointerException("Employee to be edited not exists!!!");
        }
        //edit employee
        employeeOptional.get()
                .role(employeeNew.getRole())
                .restaurant(employeeNew.getRestaurant());

        return employeeRepository.save(employeeOptional.get());
    }

    /**
     * Delete Employee.
     *
     * @param employeeId employee id.
     * @return employee deleted.
     */
    public Employee deleteEmployee(final long employeeId) {
        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new NullPointerException("Employee to be deleted not exists!!!");
        }
        employeeRepository.deleteById(employeeId);
        return employeeOptional.get();
    }

    /**
     * Get All Employees.
     *
     * @return List of employees.
     */
    public List<Employee> getAllEmployees() {
        final List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) {
            throw new NullPointerException("No Employees persisted!!!");
        }
        return employeeList;
    }
}
