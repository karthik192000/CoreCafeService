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
        List<Menu> menuList = menuRepository.findAll();
        List<MenuDetails> menuDetails = ModelDto.toDto(menuList);
        return menuDetails;
    }


    @Override
    @Transactional
    public List<MenuDetails> addToMenu(List<MenuDetails> menuDetails) {
        List<MenuDetails> addedToMenu = null;
        if(!CollectionUtils.isEmpty(menuDetails)){
           List<Menu> menuList =  ModelDto.toModel(menuDetails);
           menuList = menuRepository.saveAll(menuList);
          addedToMenu =  ModelDto.toDto(menuList);
        }

        return addedToMenu;
    }

    @Override
    @Transactional
    public List<MenuDetails> updateMenu(List<MenuDetails> menuDetails) {
        List<MenuDetails> updatedMenuList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(menuDetails)){
            List<Menu> updateMenuList = new ArrayList<>();
            menuDetails.forEach(x ->
                    {
                        Integer itemkey  = x.getItemKey();
                        Menu menu = menuRepository.findByItemkey(itemkey);
                        if(menu != null){
                            menu = ModelDto.toModel(x);
                            updateMenuList.add(menu);
                        }
                        else{
                            throw new CafeServiceException(environment.getProperty(CafeServiceConstants.CCSE_001), HttpStatus.NOT_FOUND);
                        }
                    }
            );
            updatedMenuList  = ModelDto.toDto(menuRepository.saveAll(updateMenuList));
        }

        return updatedMenuList;
    }


    @Override
    public void removeFromMenu(List<MenuDetails> menuDetails) {
        if(!CollectionUtils.isEmpty(menuDetails)){
            List<Menu> itemsTobeRemoved = ModelDto.toModel(menuDetails);
            menuRepository.deleteAll(itemsTobeRemoved);
        }
    }
}
