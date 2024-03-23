package com.core.cafe.service.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class MenuDetails implements Serializable {


    private static final long serialVersionUID = -8724874823727482L;



    private Integer itemKey;

    private String itemName;


    private BigDecimal itemPrice;


    private String itemCategory;


    private String vegOrNonVeg;

    public MenuDetails() {
    }

    public MenuDetails(Integer itemKey,String itemName, BigDecimal itemPrice, String itemCategory, String vegOrNonVeg) {
        this.itemKey = itemKey;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.vegOrNonVeg = vegOrNonVeg;
    }


    public Integer getItemKey() {
        return itemKey;
    }

    public void setItemKey(Integer itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getVegOrNonVeg() {
        return vegOrNonVeg;
    }

    public void setVegOrNonVeg(String vegOrNonVeg) {
        this.vegOrNonVeg = vegOrNonVeg;
    }

    @Override
    public String toString() {
        return "MenuDetails{" +
                "itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemCategory='" + itemCategory + '\'' +
                ", vegOrNonVeg='" + vegOrNonVeg + '\'' +
                '}';
    }
}
