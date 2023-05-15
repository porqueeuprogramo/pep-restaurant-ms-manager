package com.pep.restaurant.ms.manager.domain;

import com.pep.restaurant.ms.manager.domain.util.ScheduleRoutineSerializer;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    private String uid;

    @Column(name = "role")
    private String role;

    @Column(name = "schedule")
    private byte[] schedule;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "employeeList")
    private Set<Restaurant> restaurantList = new HashSet<>();

    /**
     * Get id employee.
     * @return employee id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set id employee.
     * @param id employee id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Builder Employee for id.
     * @param id id to build.
     * @return employee with id.
     */
    public Employee id(final long id){
        this.id = id;
        return this;
    }

    /**
     * Get uid employee.
     * @return employee uid.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Set id employee.
     * @param uid employee uid.
     */
    public void setUid(final String uid) {
        this.uid = uid;
    }

    /**
     * Builder Employee for uid.
     * @param uid uid to build.
     * @return employee with uid.
     */
    public Employee uid(final String uid){
        this.uid = uid;
        return this;
    }

    /**
     * Get employee role.
     * @return employee role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Set employee role.
     * @param role employee role.
     */
    public void setRole(final String role) {
        this.role = role;
    }

    /**
     * Builder Employee for role.
     * @param role role to build.
     * @return employee with role.
     */
    public Employee role(final String role){
        this.role = role;
        return this;
    }

    /**
     * Get Schedule byte array.
     * @return schedule byte array.
     */
    public byte[] getSchedule() {
        return schedule;
    }

    /**
     * Set Schedule byte array.
     * @param schedule byte array.
     */
    public void setSchedule(final byte[] schedule) {
        this.schedule = schedule;
    }

    /**
     * Get ScheduleRoutine for Location.
     * @return Location scheduleRoutine.
     */
    public ScheduleRoutine getScheduleRoutine() {
        ScheduleRoutine scheduleRoutine = null;
        if(schedule != null) {
            try{
                scheduleRoutine = ScheduleRoutineSerializer.fromByteArray(schedule);
            }catch(IOException ioException){
                scheduleRoutine = null;
            }
        }
        return scheduleRoutine;
    }

    /**
     * Set Location ScheduleRoutine.
     * @param scheduleRoutine scheduleRoutine.
     * @throws IOException exception.
     */
    public void setScheduleRoutine(final ScheduleRoutine scheduleRoutine) throws IOException {
        if(scheduleRoutine != null){{
            try{
                this.schedule = ScheduleRoutineSerializer.toByteArray(scheduleRoutine);
            }catch(IOException ioException){
                this.schedule = null;
            }
        }}
    }

    /**
     * Builder Employee for schedule routine.
     * @param scheduleRoutine schedule routine dto to build.
     * @return employee with schedule routine.
     */
    public Employee schedule(final ScheduleRoutine scheduleRoutine){
        if(scheduleRoutine != null){
            try{
                this.schedule = ScheduleRoutineSerializer.toByteArray(scheduleRoutine);
            }catch(IOException ioException){
                this.schedule = null;
            }
        }else{
            this.schedule = null;
        }
        return this;
    }

    /**
     * Get employee restaurant list.
     * @return employee restaurant List.
     */
    public Set<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    /**
     * Set employee restaurant list.
     * @param restaurantList employee restaurant list.
     */
    public void setRestaurantList(final Set<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    /**
     * Builder Employee for restaurantList.
     * @param restaurantList restaurantList to build.
     * @return employee with restaurantList.
     */
    public Employee restaurantList(final Set<Restaurant> restaurantList){
        this.restaurantList = restaurantList;
        return this;
    }

    /**
     * Method to add Restaurant to Employee.
     * @param restaurant restaurant.
     */
    public void addRestaurant(final Restaurant restaurant) {
        this.restaurantList.add(restaurant);
        restaurant.getEmployeeList().add(this);
    }

    /**
     * Method to remove Restaurant from Employee.
     * @param restaurant restaurant.
     */
    public void removeRestaurant(final Restaurant restaurant) {
        this.restaurantList.remove(restaurant);
        restaurant.getEmployeeList().remove(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", uid=" + uid +
                ", role='" + role + '\'' +
                ", schedule=" + Arrays.toString(schedule) +
                ", restaurantList=" + restaurantList +
                '}';
    }
}
