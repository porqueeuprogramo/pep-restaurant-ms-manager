package com.pep.restaurant.ms.manager.service.model;

import java.util.List;
import java.util.Map;

public class ScheduleRoutineDTO {

    private Map<String, List<ScheduleTimeDTO>> daysScheduleMap;

    /**
     * Method to get a ScheduleRoutineDTO days schedule Map.
     * @return days schedule map.
     */
    public Map<String, List<ScheduleTimeDTO>> getScheduleRoutine() {
        return daysScheduleMap;
    }

    /**
     * Method to set a ScheduleRoutineDTO days schedule map.
     * @param daysScheduleMap to be set.
     */
    public void setScheduleRoutine(final Map<String, List<ScheduleTimeDTO>> daysScheduleMap) {
        this.daysScheduleMap = daysScheduleMap;
    }

    /**
     * Builder ScheduleRoutineDTO for days schedule Map.
     * @param daysScheduleMap days Schedule List to build.
     * @return ScheduleRoutineDTO with days map.
     */
    public ScheduleRoutineDTO daysScheduleMap(final Map<String, List<ScheduleTimeDTO>> daysScheduleMap){
        this.daysScheduleMap = daysScheduleMap;
        return this;
    }

    @Override
    public String toString() {
        return "ScheduleRoutineDTO{" +
                "daysScheduleMap=" + daysScheduleMap +
                '}';
    }
}
