package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Restaurant;
import com.pep.restaurant.ms.manager.logging.Logger;
import com.pep.restaurant.ms.manager.repository.RestaurantRepository;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

private static final Logger LOGGER = new Logger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public EmployeeService(final EmployeeRepository employeeRepository,
                           final RestaurantRepository restaurantRepository) {
        this.employeeRepository = employeeRepository;
        this.restaurantRepository = restaurantRepository;
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
        final Optional<Employee> employeeOptional = getEmployeeById(employeeId, "Employee to get not exists!!!");
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
        final Optional<Employee> employeeOptional = getEmployeeById(employeeId, "Employee to be edited not exists!!!");
        //edit employee
        employeeOptional.get()
                .role(employeeNew.getRole())
                .schedule(employeeNew.getSchedule());

        return employeeRepository.save(employeeOptional.get());
    }

    /**
     * Delete Employee.
     *
     * @param employeeId employee id.
     * @return employee deleted.
     */
    public Employee deleteEmployee(final long employeeId) {
        final Optional<Employee> employeeOptional = getEmployeeById(employeeId, "Employee to be deleted not exists!!!");
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

    /**
     * Add Restaurant from employee.
     * @param employeeId employee id.
     * @param restaurantId restaurant id.
     * @return Employee.
     */
    public Employee addRestaurant(final long employeeId, final long restaurantId) {
        final Optional<Employee> employeeOptional = getEmployeeById(employeeId, "Employee to add restaurant!!!");

        final Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isEmpty()){
            throw new NullPointerException("Restaurant to add employee!!!");
        }

        employeeOptional.get()
                .addRestaurant(restaurantOptional.get());


        return employeeRepository.save(employeeOptional.get());
    }

    /**
     * Remove restaurant from employee.
     * @param employeeId employee id.
     * @param restaurantId restasurant id.
     * @return Employee.
     */
    public Employee removeRestaurant(final long employeeId, final long restaurantId) {
        final Optional<Employee> employeeOptional = getEmployeeById(employeeId, "Employee to remove restaurant!!!");

        final Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isEmpty()){
            throw new NullPointerException("Restaurant to remove employee!!!");
        }
        employeeOptional.get()
                .removeRestaurant(restaurantOptional.get());

        return employeeRepository.save(employeeOptional.get());
    }

    /**
     * Find Employee on Repository.
     * @param employeeId employee Id.
     * @param exceptionMessage exception Message.
     * @return Optional of employee.
     */
    private Optional<Employee> getEmployeeById(final long employeeId, final String exceptionMessage) {
        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new NullPointerException(exceptionMessage);
        }
        return employeeOptional;
    }

}
