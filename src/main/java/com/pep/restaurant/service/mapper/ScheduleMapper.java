package com.pep.restaurant.service.mapper;

import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.service.model.ScheduleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule mapScheduleDTOToSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO mapScheduleToScheduleDTO(Schedule schedule);

}