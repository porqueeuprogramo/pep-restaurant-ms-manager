package com.pep.restaurant.service;

import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.logging.Logger;
import com.pep.restaurant.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScheduleService {

    private static final Logger LOGGER = new Logger(ScheduleService.class);
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(final ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * Create Schedule.
     * @param schedule schedule.
     * @return schedule created.
     */
    public Schedule createSchedule(final Schedule schedule){
        final Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule.getId());
        if(scheduleOptional.isEmpty()){
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
        final Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if(scheduleOptional.isEmpty()){
            throw new NullPointerException("Schedule to get not exists!!!");
        }
        return scheduleOptional.get();
    }

    /**
     * Edit Schedule.
     * @param scheduleId schedule id.
     * @param scheduleNew schedule new.
     * @return schedule edited.
     */
    public Schedule editSchedule(final long scheduleId,  final Schedule scheduleNew){
        final Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if(scheduleOptional.isEmpty()){
            throw new NullPointerException("Schedule to be edited not exists!!!");
        }
        //edit schedule
        scheduleOptional.get()
                .type(scheduleNew.getType())
                .setEmployeeList(scheduleNew.getEmployeeList());

        return scheduleRepository.save(scheduleOptional.get());
    }

    /**
     * Delete Schedule.
     * @param scheduleId schedule id.
     * @return schedule deleted.
     */
    public Schedule deleteSchedule(final long scheduleId){
        final Optional<Schedule> scheduleOptional = scheduleRepository.findById(scheduleId);
        if(scheduleOptional.isEmpty()){
            throw new NullPointerException("Schedule to be deleted not exists!!!");
        }
        scheduleRepository.deleteById(scheduleId);
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
        return scheduleList;
    }
}
