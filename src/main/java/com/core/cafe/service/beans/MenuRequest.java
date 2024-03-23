package com.core.cafe.service.beans;

import java.io.Serializable;
import java.util.List;

public class MenuRequest implements Serializable {

    private static final long serialVersionUID = -95958959349345L;


    private List<MenuDetails> menuDetailsList;

    public List<MenuDetails> getMenuDetailsList() {
        return menuDetailsList;
    }

    public void setMenuDetailsList(List<MenuDetails> menuDetailsList) {
        this.menuDetailsList = menuDetailsList;
    }

    @Override
    public String toString() {
        return "MenuRequest{" +
                "menuDetailsList=" + menuDetailsList +
                '}';
    }
}
