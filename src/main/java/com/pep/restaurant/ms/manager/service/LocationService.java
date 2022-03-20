package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Location;
import com.pep.restaurant.ms.manager.logging.Logger;
import com.pep.restaurant.ms.manager.logging.enumeration.LogTag;
import com.pep.restaurant.ms.manager.repository.LocationRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private static final Logger LOGGER = new Logger(LocationService.class);
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(final LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    /**
     * Create Location.
     * @param location location.
     * @return location created.
     */
    public Location createLocation(final Location location){
        final Optional<Location> locationOptional = locationRepository.findById(location.getId());
        if(locationOptional.isEmpty()){

            LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.LOCATION, LogTag.PERSISTED),
                    "Create Location: " + location.toString());

            return locationRepository.save(location);
        }
        throw new NullPointerException("Location already exists!!!");
    }

    /**
     * Get Location.
     * @param locationId location id.
     * @return location retrieved.
     */
    public Location getLocation(final long locationId){
        final Optional<Location> locationOptional = locationRepository.findById(locationId);
        if(locationOptional.isEmpty()){
            throw new NullPointerException("Location to get not exists!!!");
        }

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.LOCATION, LogTag.RETRIEVED),
                "Get Location by id: " + locationId);

        return locationOptional.get();
    }

    /**
     * Edit Location.
     * @param locationId location id.
     * @param locationNew location new.
     * @return location edited.
     */
    public Location editLocation(final long locationId,  final Location locationNew){
        final Optional<Location> locationOptional = locationRepository.findById(locationId);
        if(locationOptional.isEmpty()){
            throw new NullPointerException("Location to be edited not exists!!!");
        }
        //edit location
        locationOptional.get()
                .address(locationNew.getAddress())
                .locationCoordinate(locationNew.getCoordinate());

        LOGGER.info(MDC.get("correlationId"),  Arrays.asList(LogTag.LOCATION, LogTag.EDITED),
                "Edit Location by id " + locationId);

        return locationRepository.save(locationOptional.get());
    }

    /**
     * Delete Location.
     * @param locationId location id.
     * @return location deleted.
     */
    public Location deleteLocation(final long locationId){
        final Optional<Location> locationOptional = locationRepository.findById(locationId);
        if(locationOptional.isEmpty()){
            throw new NullPointerException("Location to be deleted not exists!!!");
        }
        locationRepository.deleteById(locationId);

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.LOCATION, LogTag.DELETED),
                "Delete Location by id: " + locationId);

        return locationOptional.get();
    }

    /**
     * Get All Location.
     * @return List of locations.
     */
    public List<Location> getAllLocations(){
        final List<Location> locationList = locationRepository.findAll();
        if(locationList.isEmpty()){
            throw new NullPointerException("No Location persisted!!!");
        }

        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.LOCATION, LogTag.RETRIEVED),
                "Get All Location from db");

        return locationList;
    }

}
