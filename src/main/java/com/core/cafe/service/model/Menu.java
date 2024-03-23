package com.core.cafe.service.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "menu",schema = "cafe")
public class Menu {


    @Id
    @SequenceGenerator(name = "menu_itemkey_sequence", sequenceName = "cafe.menu_itemkey_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_itemkey_sequence")
    private Integer itemkey;

    private String itemname;

    private BigDecimal itemprice;


    private String itemcategory;

    private String vegornonveg;


    public Menu() {
    }

    public Menu(Integer itemkey, String itemname, BigDecimal itemprice, String itemcategory, String vegornonveg) {
        this.itemkey = itemkey;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemcategory = itemcategory;
        this.vegornonveg = vegornonveg;
    }

    public Menu(String itemname, BigDecimal itemprice, String itemcategory, String vegornonveg) {
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemcategory = itemcategory;
        this.vegornonveg = vegornonveg;
    }

    public Integer getItemkey() {
        return itemkey;
    }

    public void setItemkey(Integer itemkey) {
        this.itemkey = itemkey;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public BigDecimal getItemprice() {
        return itemprice;
    }

    public void setItemprice(BigDecimal itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemcategory() {
        return itemcategory;
    }

    public void setItemcategory(String itemcategory) {
        this.itemcategory = itemcategory;
    }

    public String getVegornonveg() {
        return vegornonveg;
    }

    public void setVegornonveg(String vegornonveg) {
        this.vegornonveg = vegornonveg;
    }

    @Override
    public String toString() {
        return "MenuDetails{" +
                "itemkey=" + itemkey +
                ", itemname='" + itemname + '\'' +
                ", itemprice=" + itemprice +
                ", itemcategory='" + itemcategory + '\'' +
                ", vegornonveg='" + vegornonveg + '\'' +
                '}';
    }
}
