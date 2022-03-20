package com.pep.restaurant.ms.manager.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;

public class ScheduleTimeDTO {

    @JsonProperty("startTime")
    private LocalTime startTime;

    @JsonProperty("endTime")
    private LocalTime endTime;

    /**
     * Method to get a ScheduleTimeDTO start time.
     * @return start time.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Method to set a ScheduleTimeDTO start time.
     * @param startTime start time to be set.
     */
    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Builder ScheduleTimeDTO for start time.
     * @param startTime startTime to build.
     * @return scheduletimeDTO with start time.
     */
    public ScheduleTimeDTO startTime(final LocalTime startTime){
        this.startTime = startTime;
        return this;
    }

    /**
     * Method to get a ScheduleTimeDTO end time.
     * @return end time.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Method to set a ScheduleTimeDTO end time.
     * @param endTime end time to be set.
     */
    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Builder ScheduleTimeDTO for end time.
     * @param endTime endTime to build.
     * @return scheduleTimeDTO with end Time.
     */
    public ScheduleTimeDTO endTime(final LocalTime endTime){
        this.endTime = endTime;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleTime{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
