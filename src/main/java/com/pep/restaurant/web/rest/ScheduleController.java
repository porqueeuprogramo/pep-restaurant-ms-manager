package com.pep.restaurant.web.rest;

import com.pep.restaurant.service.ScheduleService;
import com.pep.restaurant.service.mapper.ScheduleManualMapper;
import com.pep.restaurant.service.mapper.ScheduleMapper;
import com.pep.restaurant.service.model.ScheduleDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
public class ScheduleController {

    public static final int OK = 200;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String SCHEDULE_SCHEDULE_ID = "/schedule/{scheduleId}";
    public static final String SCHEDULE = "/schedule";
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;
    private final ScheduleManualMapper scheduleManualMapper;

    /**
     * Constructor for Schedule Controller.
     *  @param scheduleService Schedule Service.
     * @param scheduleMapper  Schedule mapper.
     * @param scheduleManualMapper Schedule manual mapper;
     */
    public ScheduleController(final ScheduleService scheduleService, final ScheduleMapper scheduleMapper,
                              final ScheduleManualMapper scheduleManualMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
        this.scheduleManualMapper = scheduleManualMapper;
    }

    /**
     * Controller to get a schedule by id.
     *
     * @param scheduleId id of schedule to get.
     * @return ScheduleDTO with the provided id.
     */
    @ApiOperation(
            value = "Get Schedule by id",
            notes = "This method allows us to get schedule by id")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = "Return schedule got",
                    response = ScheduleDTO.class, responseContainer = "Schedule"),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Schedule not exists",
                    response = ScheduleDTO.class, responseContainer = "Schedule")
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = SCHEDULE_SCHEDULE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<ScheduleDTO> getSchedule(@PathVariable final long scheduleId) {
        return ResponseEntity.ok(scheduleManualMapper.mapScheduleToScheduleDTO(
                scheduleService.getSchedule(scheduleId)));
    }

    /**
     * Controller to create a schedule.
     *
     * @param scheduleDTO scheduleDTO to create.
     * @return ScheduleDTO created.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = SCHEDULE,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody final ScheduleDTO scheduleDTO) {
        return ResponseEntity.ok(scheduleMapper.mapScheduleToScheduleDTO(
                scheduleService.createSchedule(scheduleMapper.mapScheduleDTOToSchedule(scheduleDTO))));
    }

    /**
     * Controller to edit a schedule by id.
     *
     * @param scheduleId     schedule id to edit.
     * @param scheduleToEdit schedule update.
     * @return ScheduleDTO edited.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping(value = SCHEDULE_SCHEDULE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<ScheduleDTO> editSchedule(@PathVariable final long scheduleId,
                                                        @RequestBody final ScheduleDTO scheduleToEdit) {
        return ResponseEntity.ok(scheduleMapper.mapScheduleToScheduleDTO(
                scheduleService.editSchedule(scheduleId,
                        scheduleMapper.mapScheduleDTOToSchedule(scheduleToEdit))));
    }

    /**
     * Controller to delete a schedule by id.
     *
     * @param scheduleId schedule id to be deleted.
     * @return ScheduleDTO deleted.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping(value = SCHEDULE_SCHEDULE_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<ScheduleDTO> deleteSchedule(@PathVariable final long scheduleId) {
        return ResponseEntity.ok(scheduleMapper.mapScheduleToScheduleDTO(
                scheduleService.deleteSchedule(scheduleId)));
    }

    /**
     * Controller to get a list with all schedules.
     *
     * @return SchedulesDTO list.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = SCHEDULE,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleManualMapper.mapScheduleListToScheduleDTOList(
                scheduleService.getAllSchedules()));
    }

}
