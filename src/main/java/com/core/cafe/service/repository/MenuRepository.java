package com.core.cafe.service.repository;

import com.core.cafe.service.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {



    Menu findByItemkey(Integer itemkey);


    @Query("select m from Menu m order by m.itemkey asc")
    List<Menu> findAllOrderByItemkey();

}
