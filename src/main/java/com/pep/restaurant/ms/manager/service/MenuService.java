package com.pep.restaurant.ms.manager.service;

import com.pep.restaurant.ms.manager.domain.Menu;
import com.pep.restaurant.ms.manager.logging.Logger;
import com.pep.restaurant.ms.manager.logging.enumeration.LogTag;
import com.pep.restaurant.ms.manager.repository.MenuRepository;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        LOGGER.info(MDC.get("correlationId"), Arrays.asList(LogTag.MENU, LogTag.PERSISTED),
                "Create Menu: " + menu);

        return menuRepository.save(menu);
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

        LOGGER.info(MDC.get("correlationId"),  Arrays.asList(LogTag.MENU, LogTag.EDITED),
                "Edit Menu by id " + menuId);

        return menuRepository.save(menuOptional.get());
    }

}
