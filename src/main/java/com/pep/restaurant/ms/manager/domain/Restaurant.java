package com.pep.restaurant.ms.manager.domain;

import com.pep.restaurant.ms.manager.domain.util.ScheduleRoutineSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "here_id")
    private String hereId;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(unique = true)
    private Location location;

    @Column(name = "capacity")
    private int capacity;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(unique = true)
    private Menu menu;

    @Column(name = "schedule")
    private byte[] schedule;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "restaurant_employee",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employeeList = new HashSet<>();

    /**
     * Get Restaurant id.
     * @return restaurant id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set Restaurant Id.
     * @param id restaurant id.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Builder Restaurant for id.
     * @param id id to build.
     * @return restaurant with id.
     */
    public Restaurant id(final long id){
        this.id = id;
        return this;
    }

    /**
     * Get Restaurant here id.
     * @return restaurant here id.
     */
    public String getHereId() {
        return hereId;
    }

    /**
     * Set Restaurant here id.
     * @param hereId restaurant id.
     */
    public void setHereId(final String hereId) {
        this.hereId = hereId;
    }

    /**
     * Builder Restaurant for here id.
     * @param hereId here id to build.
     * @return restaurant with id.
     */
    public Restaurant hereId(final String hereId){
        this.hereId = hereId;
        return this;
    }

    /**
     * Get Restaurant name.
     * @return restaurant name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Restaurant name.
     * @param name restaurant name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Builder Restaurant for name.
     * @param name name to build.
     * @return restaurant with name.
     */
    public Restaurant name(final String name){
        this.name = name;
        return this;
    }

    /**
     * Get Restaurant Location.
     * @return restaurant location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set Restaurant Location.
     * @param location restaurant location.
     */
    public void setLocation(final Location location) {
        this.location = location;
    }

    /**
     * Builder Restaurant for location
     * @param location location to build.
     * @return restaurant with location.
     */
    public Restaurant location(final Location location){
        this.location = location;
        return this;
    }

    /**
     * Get Restaurant Schedule byte array.
     * @return schedule byte array.
     */
    public byte[] getSchedule() {
        return schedule;
    }

    /**
     * Set Restaurant schedule.
     * @param schedule restaurant schedule.
     */
    public void setSchedule(final byte[] schedule) {
        this.schedule = schedule;
    }

    /**
     * Builder Restaurant for schedule routine.
     * @param scheduleRoutine schedule routine dto to build.
     * @return restaurant with schedule routine.
     */
    public Restaurant schedule(final ScheduleRoutine scheduleRoutine){
        if(scheduleRoutine != null){{
            try{
                this.schedule = ScheduleRoutineSerializer.toByteArray(scheduleRoutine);
            }catch(IOException ioException){
                this.schedule = null;
            }
        }}
        return this;
    }

    /**
     * Get restaurant capacity.
     * @return restaurant capacity.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set restaurant capacity.
     * @param capacity restaurant capacity.
     */
    public void setCapacity(final int capacity) {
        this.capacity = capacity;
    }

    /**
     * Builder for restaurant capacity.
     * @param capacity capacity to build.
     * @return restaurant with capacity.
     */
    public Restaurant capacity(final int capacity){
        this.capacity = capacity;
        return this;
    }

    /**
     * Get restaurant menu.
     * @return menu.
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Set restaurant menu
     * @param menu restaurant menu.
     */
    public void setMenu(final Menu menu) {
        this.menu = menu;
    }

    /**
     * Builder Restaurant for menu.
     * @param menu menu to build.
     * @return restaurant with menu.
     */
    public Restaurant menu(final Menu menu){
        this.menu = menu;
        return this;
    }

    /**
     * Method to get Restaurant employee list.
     * @return employee list.
     */
    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * Method to set Restaurant employee List
     * @param employeeList employee list.
     */
    public void setEmployeeList(final Set<Employee> employeeList){
        this.employeeList = employeeList;
    }

    /**
     * Builder Restaurant for employeeList.
     * @param employeeList employeeList to build.
     * @return restaurant with employeeList.
     */
    public Restaurant employeeList(final Set<Employee> employeeList){
        this.employeeList = employeeList;
        return this;
    }

    /**
     * Method to add Employee to Restaurant.
     * @param employee employee.
     */
    public void addEmployee(final Employee employee) {
        this.employeeList.add(employee);
        employee.getRestaurantList().add(this);
    }

    /**
     * Method to remove Employee from Restaurant.
     * @param employee employee.
     */
    public void removeEmployee(final Employee employee) {
        this.employeeList.remove(employee);
        employee.getRestaurantList().remove(this);
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

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hereId='" + hereId + '\'' +
                ", location=" + location +
                ", capacity=" + capacity +
                ", menu=" + menu +
                ", schedule=" + Arrays.toString(schedule) +
                ", employeeList=" + employeeList +
                '}';
    }
}
