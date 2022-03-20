package com.pep.restaurant.ms.manager.domain;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class ScheduleRoutine {

    private Map<DayOfWeek, List<ScheduleTime>> daysScheduleMap;

    /**
     * Method to get Schedule Routine days schedule map.
     * @return schedule map.
     */
    public Map<DayOfWeek, List<ScheduleTime>> getScheduleRoutine() {
        return daysScheduleMap;
    }

    /**
     * Method to set Schedule routine days schedule Map.
     * @param daysScheduleMap schedule routine days.
     */
    public void setScheduleRoutine(final Map<DayOfWeek, List<ScheduleTime>> daysScheduleMap) {
        this.daysScheduleMap = daysScheduleMap;
    }

    /**
     * Builder Schedule Routine for days schedule Map.
     * @param daysScheduleMap days Schedule Map to build.
     * @return schedule map.
     */
    public ScheduleRoutine daysScheduleMap(final Map<DayOfWeek, List<ScheduleTime>> daysScheduleMap){
        this.daysScheduleMap = daysScheduleMap;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleRoutine{" +
                "daysScheduleMap=" + daysScheduleMap +
                '}';
    }
}
