package com.pep.restaurant.ms.manager.domain;

import java.time.LocalTime;

public class ScheduleTime {
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Get ScheduleTime start.
     * @return schedule time start.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Set ScheduleTime end.
     * @param startTime time end.
     */
    public void setStartTime(final LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Builder Schedule Time for start time.
     * @param startTime startTime to build.
     * @return schedule time with start time.
     */
    public ScheduleTime startTime(final LocalTime startTime){
        this.startTime = startTime;
        return this;
    }

    /**
     * Get ScheduleTime end.
     * @return schedule time end.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Set ScheduleTime end.
     * @param endTime time end.
     */
    public void setEndTime(final LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Builder Schedule Time for end time.
     * @param endTime end time to build.
     * @return schedule time with end time.
     */
    public ScheduleTime endTime(final LocalTime endTime){
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
