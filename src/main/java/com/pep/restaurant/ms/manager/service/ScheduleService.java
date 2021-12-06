package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Employee;
import com.pep.restaurant.ms.manager.domain.Schedule;
import com.pep.restaurant.ms.manager.logging.Logger;
import com.pep.restaurant.ms.manager.logging.enumeration.LogTag;
import com.pep.restaurant.ms.manager.repository.EmployeeRepository;
import com.pep.restaurant.ms.manager.repository.ScheduleRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class ScheduleService {

    private static final Logger LOGGER = new Logger(ScheduleService.class);
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ScheduleService(final ScheduleRepository scheduleRepository,
                           final EmployeeRepository employeeRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Create Schedule.
     * @param schedule schedule.
     * @return schedule created.
     */
    public Schedule createSchedule(final Schedule schedule){
        final Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule.getId());
        if(scheduleOptional.isEmpty()){

            LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.SCHEDULE, LogTag.PERSISTED),
                    "Create Schedule: " + schedule.toString());

            return scheduleRepository.save(schedule);
        }
        throw new NullPointerException("Schedule already exists!!!");
    }

    /**
     * Get Schedule.
     * @param scheduleId schedule id.
     * @return schedule retrieved.
     */
    public Schedule getSchedule(final long scheduleId){
        final Optional<Schedule> scheduleOptional = getScheduleById(scheduleId,
                "Schedule to get not exists!!!");

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.SCHEDULE, LogTag.RETRIEVED),
                "Get Schedule by id: " + scheduleId);

        return scheduleOptional.get();
    }

    /**
     * Edit Schedule.
     * @param scheduleId schedule id.
     * @param scheduleNew schedule new.
     * @return schedule edited.
     */
    public Schedule editSchedule(final long scheduleId,  final Schedule scheduleNew){
        final Optional<Schedule> scheduleOptional = getScheduleById(scheduleId,
                "Schedule to be edited not exists!!!");
        //edit schedule
        scheduleOptional.get()
                .type(scheduleNew.getType())
                .setEmployeeList(scheduleNew.getEmployeeList());

        LOGGER.info(MDC.get("correlationId"),  Arrays.asList(LogTag.SCHEDULE, LogTag.EDITED),
                "Edit Schedule by id " + scheduleId);

        return scheduleRepository.save(scheduleOptional.get());
    }

    /**
     * Delete Schedule.
     * @param scheduleId schedule id.
     * @return schedule deleted.
     */
    public Schedule deleteSchedule(final long scheduleId){
        final Optional<Schedule> scheduleOptional = getScheduleById(scheduleId,
                "Schedule to be deleted not exists!!!");
        scheduleRepository.deleteById(scheduleId);

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.SCHEDULE, LogTag.DELETED),
                "Delete Schedule by id: " + scheduleId);

        return scheduleOptional.get();
    }

    /**
     * Get All Schedules.
     * @return List of schedules.
     */
    public List<Schedule> getAllSchedules(){
        final List<Schedule> scheduleList = scheduleRepository.findAll();
        if(scheduleList.isEmpty()){
            throw new NullPointerException("No Schedules persisted!!!");
        }

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.SCHEDULE, LogTag.RETRIEVED),
                "Get All Schedule from db");

        return scheduleList;
    }

    /**
     * Add employee from schedule.
     * @param scheduleId schedule id.
     * @param employeeId employee id.
     * @return Schedule.
     */
    public Schedule addEmployee(final long scheduleId,  final long employeeId){
        final Optional<Schedule> scheduleOptional = getScheduleById(scheduleId,
                "Schedule to add Employee does not exists!!!");

        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty()){
            throw new NullPointerException("Employee to add on schedule does not exists!!!");
        }

        scheduleOptional.get()
                .addEmployee(employeeOptional.get());

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.SCHEDULE, LogTag.EDITED),
                "Add Employee with id: " + employeeId + " to Schedule with id: " + scheduleId);

        return scheduleRepository.save(scheduleOptional.get());
    }

    /**
     * Remove employee from schedule.
     * @param scheduleId schedule id.
     * @param employeeId employee id.
     * @return Schedule.
     */
    public Schedule removeEmployee(final long scheduleId, final long employeeId){
        final Optional<Schedule> scheduleOptional = getScheduleById(scheduleId,
                "Schedule to remove Employee does not exists!!!");

        final Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isEmpty()){
            throw new NullPointerException("Employee to remove from schedule does not exists!!!");
        }

        scheduleOptional.get()
                .removeEmployee(employeeOptional.get());

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.SCHEDULE, LogTag.EDITED),
                "Remove Employee with id: " + employeeId + " from Schedule with id: " + scheduleId);

        return scheduleRepository.save(scheduleOptional.get());
    }

    /**
     * Find Schedule on Repository.
     * @param scheduleId schedule Id.
     * @param exceptionMessage exception Message.
     * @return Optional of schedule.
     */
    private Optional<Schedule> getScheduleById(final long scheduleId, final String exceptionMessage) {
        final Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if (scheduleOptional.isEmpty()) {
            throw new NullPointerException(exceptionMessage);
        }
        return scheduleOptional;
    }

}
