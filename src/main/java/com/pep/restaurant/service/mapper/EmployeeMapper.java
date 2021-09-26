package com.pep.restaurant.service.mapper;

import com.pep.restaurant.domain.Employee;
import com.pep.restaurant.service.model.EmployeeDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee mapEmployeeDTOToEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO mapEmployeeToEmployeeDTO(Employee employee);

    List<Employee> mapEmployeeDTOListToEmployeeList(List<EmployeeDTO> EmployeeDTOList);

    List<EmployeeDTO> mapEmployeeListToEmployeeDTOList(List<Employee> employeeList);
    
}