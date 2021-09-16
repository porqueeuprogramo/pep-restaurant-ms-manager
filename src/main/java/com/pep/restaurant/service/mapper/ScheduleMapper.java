package com.pep.restaurant.service.mapper;

import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.domain.Schedule;
import com.pep.restaurant.service.model.ScheduleDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule mapScheduleDTOToSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO mapScheduleToScheduleDTO(Schedule schedule);

    List<Schedule> mapScheduleDTOListToScheduleList(List<ScheduleDTO> scheduleDTOList);

    List<ScheduleDTO> mapScheduleListToScheduleDTOList(List<Schedule> scheduleList);

}