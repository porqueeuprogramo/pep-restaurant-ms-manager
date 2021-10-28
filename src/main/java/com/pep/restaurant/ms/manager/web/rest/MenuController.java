package com.pep.restaurant.ms.manager.web.rest;

import com.pep.restaurant.ms.manager.service.mapper.MenuMapper;
import com.pep.restaurant.ms.manager.service.model.MenuDTO;
import com.pep.restaurant.ms.manager.service.MenuService;
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
public class MenuController {

    public static final int OK = 200;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String MENU_MENU_ID = "/menu/{menuId}";
    public static final String MENU = "/menu";
    private final MenuService menuService;
    private final MenuMapper menuMapper;

    /**
     * Constructor for Menu Controller.
     *
     * @param menuService Menu Service.
     * @param menuMapper  Menu mapper.
     */
    public MenuController(final MenuService menuService, final MenuMapper menuMapper) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
    }

    /**
     * Controller to get a menu by id.
     *
     * @param menuId id of menu to get.
     * @return MenuDTO with the provided id.
     */
    @ApiOperation(
            value = "Get Menu by id",
            notes = "This method allows us to get menu by id")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = "Return menu got",
                    response = MenuDTO.class, responseContainer = "Menu"),
            @ApiResponse(code = INTERNAL_SERVER_ERROR, message = "Menu not exists",
                    response = MenuDTO.class, responseContainer = "Menu")
    })
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<MenuDTO> getMenu(@PathVariable final long menuId) {
        return ResponseEntity.ok(menuMapper.mapMenuToMenuDTO(
                menuService.getMenu(menuId)));
    }

    /**
     * Controller to create a menu.
     *
     * @param menuDTO menuDTO to create.
     * @return MenuDTO created.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = MENU,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<MenuDTO> createMenu(@RequestBody final MenuDTO menuDTO) {
        return ResponseEntity.ok(menuMapper.mapMenuToMenuDTO(
                menuService.createMenu(menuMapper.mapMenuDTOToMenu(menuDTO))));
    }

    /**
     * Controller to edit a menu by id.
     *
     * @param menuId     menu id to edit.
     * @param menuToEdit menu update.
     * @return MenuDTO edited.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<MenuDTO> editMenu(@PathVariable final long menuId,
                                                        @RequestBody final MenuDTO menuToEdit) {
        return ResponseEntity.ok(menuMapper.mapMenuToMenuDTO(
                menuService.editMenu(menuId,
                        menuMapper.mapMenuDTOToMenu(menuToEdit))));
    }

    /**
     * Controller to delete a menu by id.
     *
     * @param menuId menu id to be deleted.
     * @return MenuDTO deleted.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping(value = MENU_MENU_ID,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<MenuDTO> deleteMenu(@PathVariable final long menuId) {
        return ResponseEntity.ok(menuMapper.mapMenuToMenuDTO(
                menuService.deleteMenu(menuId)));
    }

    /**
     * Controller to get a list with all menus.
     *
     * @return MenusDTO list.
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = MENU,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return ResponseEntity.ok(menuMapper.mapMenuListToMenuDTOList(
                menuService.getAllMenus()));
    }

}
