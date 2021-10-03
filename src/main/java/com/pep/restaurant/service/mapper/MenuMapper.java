package com.pep.restaurant.service.mapper;

import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.service.model.MenuDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuMapper {

    /**
     * Map menu list to menu list DTO.
     * @param menuList menu list.
     * @return Menu List DTO.
     */
    public List<MenuDTO> mapMenuListToMenuDTOList(final List<Menu> menuList) {
        return menuList != null
                ? menuList
                .stream()
                .map(this::mapMenuToMenuDTO)
                .collect(Collectors.toList())
                : null;
    }

    /**
     * Map Menu to Menu DTO.
     * @param menu menu.
     * @return MenuDTO.
     */
    public MenuDTO mapMenuToMenuDTO(final Menu menu) {
        return menu != null ?
                new MenuDTO()
                        .id(menu.getId())
                        .language(menu.getLanguage())
                : null;
    }

    /**
     * Map menu DTO to menu.
     * @param menuDTO menu DTO.
     * @return Menu.
     */
    public Menu mapMenuDTOToMenu(final MenuDTO menuDTO) {
        return menuDTO != null ?
                new Menu()
                        .id(menuDTO.getId())
                        .language(menuDTO.getLanguage())
                : null;
    }

}
