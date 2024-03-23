package com.core.cafe.service.util;

import com.core.cafe.service.beans.MenuDetails;
import com.core.cafe.service.model.Menu;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ModelDto{



    public static List<MenuDetails> toDto(List<Menu> menuList) {
        List<MenuDetails> menuDetailsList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(menuList)){
            menuList.forEach(x -> {
                MenuDetails menuDetails = new MenuDetails(x.getItemkey(),x.getItemname(),x.getItemprice(),
                        x.getItemcategory(),x.getVegornonveg());
                menuDetailsList.add(menuDetails);
            });
        }

        return menuDetailsList;
    }


    public static Menu toModel(MenuDetails menuDetails){
        Menu menu = null;
        if(menuDetails != null){
            if(menuDetails.getItemKey()!=null) {
                menu = new Menu(menuDetails.getItemKey(), menuDetails.getItemName(), menuDetails.getItemPrice(),
                        menuDetails.getItemCategory(), menuDetails.getVegOrNonVeg());
            }
            else {
                menu = new Menu(menuDetails.getItemName(),menuDetails.getItemPrice(),menuDetails.getItemCategory(),menuDetails.getVegOrNonVeg());
            }
        }
        return menu;
    }

    public static List<Menu> toModel(List<MenuDetails> menuDetailsList){
        List<Menu> menuList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(menuDetailsList)){
            menuDetailsList.forEach(x -> {
                Menu menu = new Menu(x.getItemName(),x.getItemPrice(),
                        x.getItemCategory(),x.getVegOrNonVeg());
                menuList.add(menu);
            });
        }

        return menuList;

    }
}

