package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.ApplicationDataProvider;
import com.pep.restaurant.ms.manager.RestaurantMsManagerApplication;
import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.domain.Schedule;
import com.pep.restaurant.ms.manager.domain.enumeration.ScheduleType;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
import com.pep.restaurant.ms.manager.repository.MenuRepository;
import com.pep.restaurant.ms.manager.repository.RestaurantRepository;
import com.pep.restaurant.ms.manager.repository.ScheduleRepository;
import com.pep.restaurant.ms.manager.service.mapper.ScheduleMapper;
import com.pep.restaurant.ms.manager.service.model.ScheduleDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestaurantMsManagerApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ScheduleControllerIntegrationTest {

    @Autowired
    private ScheduleController scheduleController;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    ApplicationDataProvider applicationDataProvider = new ApplicationDataProvider();

    @Before
    public void clearDBRestaurant() {
        restaurantRepository.deleteAll();
        menuRepository.deleteAll();
        employeeRepository.deleteAll();
        scheduleRepository.deleteAll();
    }

    @Test
    public void requestingAScheduleDTO_checkScheduleSaved(){

        //Given
        Schedule schedule = applicationDataProvider.getSchedule();

        ScheduleDTO scheduleDTO = scheduleMapper.mapScheduleToScheduleDTO(schedule);

        //When
        ResponseEntity<ScheduleDTO> scheduleDTOResponseEntity = scheduleController.createSchedule(scheduleDTO);

        //Then
        Assert.assertEquals(schedule.getType(), Objects.requireNonNull(scheduleDTOResponseEntity.getBody()).getType());
    }

    @Test
    public void requestingAScheduleId_getScheduleById(){

        //Given
        Schedule schedule = applicationDataProvider.getSchedule();

        //save schedule
        scheduleRepository.save(schedule);

        //When
        ResponseEntity<ScheduleDTO> scheduleDTOResponseEntity = scheduleController.getSchedule(schedule.getId());

        //Then
        Assert.assertEquals(schedule.getType(), Objects.requireNonNull(scheduleDTOResponseEntity.getBody()).getType());
    }

    @Test
    public void requestingAScheduleToEdit_getScheduleEdited(){
        //Given
        Schedule schedule = applicationDataProvider.getSchedule();

        //save schedule
        scheduleRepository.save(schedule);

        //restaurant edited
        Schedule scheduleEdited = applicationDataProvider
                .getSchedule()
                .type(ScheduleType.PARTTIME);

        //When
        ResponseEntity<ScheduleDTO> scheduleDTOResponseEntity =
                scheduleController.editSchedule(schedule.getId(),
                        scheduleMapper.mapScheduleToScheduleDTO(scheduleEdited));

        //Then
        Assert.assertEquals(scheduleEdited.getType(),
                Objects.requireNonNull(scheduleDTOResponseEntity.getBody()).getType());

    }

    @Test
    public void requestingAScheduleIdToDelete_checkScheduleDeleted(){

        //Given
        Schedule schedule = applicationDataProvider.getSchedule();

        //save schedule
        scheduleRepository.save(schedule);

        //When
        ResponseEntity<ScheduleDTO> scheduleDTOResponseEntity =
                scheduleController.deleteSchedule(schedule.getId());

        //Then
        Assert.assertEquals(schedule.getType(), Objects.requireNonNull(scheduleDTOResponseEntity.getBody()).getType());

    }

    @Test
    public void callingGetAllSchedule_checkScheduleList(){

        //Given
        Schedule schedule = applicationDataProvider.getSchedule();

        Schedule schedule2 = applicationDataProvider.getSchedule();

        //save schedule
        scheduleRepository.save(schedule);

        //save schedule 2
        scheduleRepository.save(schedule2);

        //When
        ResponseEntity<List<ScheduleDTO>> scheduleDTOResponseEntity =
                scheduleController.getAllSchedules();

        //Then
        Assert.assertEquals(2, Objects.requireNonNull(scheduleDTOResponseEntity.getBody()).size());

    }

    @Test
    public void requestingScheduleIdAndEmployeeId_removeEmployeeFromScheduleList() {

        //Given
        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employeeRepository.save(employee);

        Schedule schedule = applicationDataProvider.getSchedule();
        schedule.addEmployee(employee);
        //save schedule
        scheduleRepository.save(schedule);

        //When
        ResponseEntity<ScheduleDTO> scheduleDTOResponseEntity =
                scheduleController.removeEmployee(1L, 1L);

        //Then
        Assert.assertEquals(0,
                Objects.requireNonNull(scheduleDTOResponseEntity.getBody()).getEmployeeList().size());

    }

    @Test
    public void requestingScheduleIdAndEmployeeId_addEmployeeToScheduleList() {

        //Given
        Menu menu = applicationDataProvider.getMenu();
        //save menu
        menuRepository.save(menu);

        Schedule schedule = applicationDataProvider.getSchedule();
        //save schedule
        scheduleRepository.save(schedule);

        Employee employee = applicationDataProvider.getEmployeeWithoutRestaurantListAndWithoutSchedule();
        employeeRepository.save(employee);

        //When
        ResponseEntity<ScheduleDTO> scheduleDTOResponseEntity =
                scheduleController.addEmployee(1L, 1L);

        //Then
        Assert.assertEquals(1,
                Objects.requireNonNull(scheduleDTOResponseEntity.getBody()).getEmployeeList().size());

    }

}
