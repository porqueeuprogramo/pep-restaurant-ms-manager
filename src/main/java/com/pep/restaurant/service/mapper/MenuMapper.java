package com.pep.restaurant.service.mapper;

import com.pep.restaurant.domain.Menu;
import com.pep.restaurant.service.model.MenuDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    Menu mapMenuDTOToMenu(MenuDTO MenuDTO);

    MenuDTO mapMenuToMenuDTO(Menu menu);

    List<Menu> mapMenuDTOListToMenuList(List<MenuDTO> MenuDTOList);

    List<MenuDTO> mapMenuListToMenuDTOList(List<Menu> menuList);

}