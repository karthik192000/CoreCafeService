package com.core.cafe.service.service.impl;

import com.core.cafe.service.beans.MenuDetails;
import com.core.cafe.service.helper.CafeServiceConstants;
import com.core.cafe.service.model.Menu;
import com.core.cafe.service.repository.MenuRepository;
import com.core.cafe.service.service.MenuService;
import com.core.cafe.service.util.CafeServiceException;
import com.core.cafe.service.util.ModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {



    @Autowired
    MenuRepository menuRepository;


    @Autowired
    Environment environment;


    @Override
    public List<MenuDetails> getMenu() {
        List<MenuDetails> menuDetails = null;

        try {
            List<Menu> menuList = menuRepository.findAllOrderByItemkey();
            menuDetails = ModelDto.toDto(menuList);
        }
        catch (Exception ex){
            throw new CafeServiceException("Exception occurred whilet fetching menu details",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return menuDetails;
    }


    @Override
    @Transactional
    public List<MenuDetails> addToMenu(List<MenuDetails> menuDetails) {
        List<MenuDetails> addedToMenu = null;
        try {
            if (!CollectionUtils.isEmpty(menuDetails)) {
                List<Menu> menuList = ModelDto.toModel(menuDetails);
                menuList = menuRepository.saveAll(menuList);
                addedToMenu = ModelDto.toDto(menuList);
            }
        }
        catch (Exception ex){
            throw new CafeServiceException("Something went wrong while adding item to menu",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return addedToMenu;
    }

    @Override
    @Transactional
    public List<MenuDetails> updateMenu(List<MenuDetails> menuDetails) {
        List<MenuDetails> updatedMenuList = new ArrayList<>();
        try {
            if (!CollectionUtils.isEmpty(menuDetails)) {
                List<Menu> updateMenuList = new ArrayList<>();
                menuDetails.forEach(x ->
                        {
                            Integer itemkey = x.getItemKey();
                            Menu menu = menuRepository.findByItemkey(itemkey);
                            if (menu != null) {
                                menu = ModelDto.toModel(x);
                                updateMenuList.add(menu);
                            } else {
                                throw new CafeServiceException(environment.getProperty(CafeServiceConstants.CCSE_001), HttpStatus.NOT_FOUND);
                            }
                        }
                );
                updatedMenuList = ModelDto.toDto(menuRepository.saveAll(updateMenuList));
            }
        }
        catch (Exception ex){
            throw new CafeServiceException("Exception occurred while Updating menu details",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return updatedMenuList;
    }


    @Override
    public void removeFromMenu(Integer itemKey) {
        try {
            if (itemKey != null) {
                Menu menu = menuRepository.findByItemkey(itemKey);
                if (menu != null) {
                    menuRepository.delete(menu);
                }
            }
        }
        catch (Exception ex){
            throw new CafeServiceException("Exception occured while removing item from menu",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
