package com.daily.essentials.itemservice.controller;

import com.daily.essentials.itemservice.model.Item;
import com.daily.essentials.itemservice.model.Itemcatalog;
import com.daily.essentials.itemservice.model.Items;
import com.daily.essentials.itemservice.service.implementation.Itemserviceimplementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1")
public class ItemsController {

    Itemserviceimplementation itemserviceimplementation;

    ItemsController(Itemserviceimplementation itemserviceimplementation)
    {
        this.itemserviceimplementation=itemserviceimplementation;
    }

    @CrossOrigin("*")
    @GetMapping("/items")
    @ResponseBody
    public Items getAllItems() {
        List<Item> listOfItems = itemserviceimplementation.getAllItems();
        Items items= new Items();
        items.setItemList(listOfItems);
        return items;
    }

    @CrossOrigin("*")
    @GetMapping("/items/{itemId}")
    @ResponseBody
    public Item getItemByid(@PathVariable long itemId) {
        return itemserviceimplementation.getItemByid(itemId);
    }

    @CrossOrigin("*")
    @GetMapping("/items/{itemId}/available")
    @ResponseBody
    public Item checkItemAvailability(@PathVariable long itemId) {
        return itemserviceimplementation.getItemAvailableByid(itemId);
    }

    @CrossOrigin("*")
    @PostMapping("/items")
    @ResponseBody
    public ResponseEntity<Object> addNewItem(@RequestBody Item item)
    {
        item=itemserviceimplementation.addNewItem(item);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(item.getId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();

    }

    @CrossOrigin("*")
    @PostMapping("/items/catalog")
    @ResponseBody
    public void addNewItemCatalog(@RequestBody Itemcatalog itemcatalog)
    {
        itemserviceimplementation.addNewItemCatalog(itemcatalog);
    }
}
