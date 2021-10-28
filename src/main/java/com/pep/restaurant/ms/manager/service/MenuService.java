package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.logging.Logger;
import com.pep.restaurant.ms.manager.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private static final Logger LOGGER = new Logger(MenuService.class);
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(final MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * Create Menu.
     * @param menu menu.
     * @return menu created.
     */
    public Menu createMenu(final Menu menu){
        final Optional<Menu> menuOptional = menuRepository.findByLanguage(menu.getLanguage());
        if(menuOptional.isEmpty()){
            return menuRepository.save(menu);
        }
        throw new NullPointerException("Menu already exists!!!");
    }

    /**
     * Get Menu.
     * @param menuId menu id.
     * @return menu retrieved.
     */
    public Menu getMenu(final long menuId){
        final Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if(menuOptional.isEmpty()){
            throw new NullPointerException("Menu to get not exists!!!");
        }
        return menuOptional.get();
    }

    /**
     * Edit Menu.
     * @param menuId menu id.
     * @param menuNew menu new.
     * @return menu edited.
     */
    public Menu editMenu(final long menuId,  final Menu menuNew){
        final Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if(menuOptional.isEmpty()){
            throw new NullPointerException("Menu to be edited not exists!!!");
        }
        //edit menu
        menuOptional.get()
                .language(menuNew.getLanguage());

        return menuRepository.save(menuOptional.get());
    }

    /**
     * Delete Menu.
     * @param menuId menu id.
     * @return menu deleted.
     */
    public Menu deleteMenu(final long menuId){
        final Optional<Menu> menuOptional = menuRepository.findById(menuId);
        if(menuOptional.isEmpty()){
            throw new NullPointerException("Menu to be deleted not exists!!!");
        }
        menuRepository.deleteById(menuId);
        return menuOptional.get();
    }

    /**
     * Get All Menu.
     * @return List of menus.
     */
    public List<Menu> getAllMenus(){
        final List<Menu> menuList = menuRepository.findAll();
        if(menuList.isEmpty()){
            throw new NullPointerException("No Menu persisted!!!");
        }
        return menuList;
    }

}
