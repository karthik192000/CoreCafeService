package com.core.cafe.service.repository;

import com.core.cafe.service.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {



    Menu findByItemkey(Integer itemkey);

}
