package com.core.cafe.service.controller;


import com.core.cafe.service.beans.MenuDetails;
import com.core.cafe.service.beans.MenuRequest;
import com.core.cafe.service.service.MenuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cafeservice")
public class CafeServiceController {


    @Autowired
    MenuService menuService;


    @ApiOperation(value = "Get All menu Details", notes = "Get All menu Details", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200,message="Fetched Menu Details Successfully",response = MenuDetails.class)
    })
    @Secured(value = {"ROLE_CUSTOMER","ROLE_EMPLOYEE","ROLE_ADMIN"})
    @CrossOrigin
    @GetMapping(path = "/menu",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenu(){
        List<MenuDetails> menuDetails = menuService.getMenu();
        return new ResponseEntity<>(menuDetails, HttpStatus.OK);
    }


    @ApiOperation(value = "Add new items(s) to menu", notes = "Add new item(s) to menu", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 201,message="Added Item(s) to menu successfully",response = MenuDetails.class)
    })
    @Secured(value = {"ROLE_ADMIN"})
    @PostMapping(path = "/menu",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addToMenu(@RequestBody List<MenuDetails> menuDetails){
        List<MenuDetails> menuDetailsList = menuService.addToMenu(menuDetails);
        return new ResponseEntity<>(menuDetailsList,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update item(s) in the menu", notes = "Update item(s) in the menu", httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code = 201,message="Updated item(s) in the menu successfully",response = MenuDetails.class),
            @ApiResponse(code = 404,message = "Item(s) not found for provided item keys(s)")
    })
    @Secured(value = {"ROLE_ADMIN"})
    @PutMapping(path = "/menu",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMenu(@RequestBody List<MenuDetails> menuDetails){
        List<MenuDetails> menuDetailsList = menuService.updateMenu(menuDetails);
        return new ResponseEntity<>(menuDetailsList,HttpStatus.CREATED);
    }


    @ApiOperation(value = "Remove items(s) from the menu",notes = "Remove item(s) from the menu.", httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 204,message = "Item(s) delete from menu successfully.")
    })
    @Secured(value = {"ROLE_ADMIN"})
    @DeleteMapping(path = "/menu")
    @CrossOrigin
    public ResponseEntity<?> removeFromMenu(@RequestParam(value = "itemkey") Integer itemKey){
        menuService.removeFromMenu(itemKey);
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
    }
}
