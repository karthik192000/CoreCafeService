package com.core.cafe.service.service;

import com.core.cafe.service.beans.MenuDetails;

import java.util.List;

public interface MenuService {



    public List<MenuDetails> getMenu();

    public List<MenuDetails> addToMenu(List<MenuDetails> menuDetails);

    public List<MenuDetails> updateMenu(List<MenuDetails> menuDetails);

    public void removeFromMenu(List<MenuDetails> menuDetails);
}
