package com.pep.restaurant.web.rest;

import com.pep.restaurant.service.UserService;
import com.pep.restaurant.service.mapper.UserMapper;
import com.pep.restaurant.service.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class UserController {

    public static final String USER_USER_ID = "/user/{userId}";
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Constructor for User Controller.
     * @param userService User Service.
     * @param userMapper  User mapper.
     */
    public UserController(final UserService userService, final UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Controller to get a user by id.
     *
     * @param userId id of user to get.
     * @return UserDTO with the provided id.
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value = USER_USER_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<UserDTO> getUser(@PathVariable final String userId) {
        return ResponseEntity.ok(userMapper.mapUserToUserDTO(userService.getUser(userId)));
    }

    /**
     * Controller to edit a user by id.
     *
     * @param userId id of user to edit.
     * @param userDTO user info to update.
     * @return User with user info edited.
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping(value = USER_USER_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<UserDTO> editUser(@PathVariable final String userId, @RequestBody final UserDTO userDTO) {
        return ResponseEntity.ok(userMapper.mapUserToUserDTO(userService
                        .editUser(userId,userMapper.mapUserDTOToUser(userDTO))));
    }

    /**
     * Controller to delete a user by id.
     *
     * @param userId id of user to delete.
     * @return String with username deleted.
     */
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping(value = USER_USER_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<String> deleteUser(@PathVariable final String userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

}
