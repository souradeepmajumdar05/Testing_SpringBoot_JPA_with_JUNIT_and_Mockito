package com.daily.essentials.itemservice.controller;

import com.daily.essentials.itemservice.model.Item;
import com.daily.essentials.itemservice.model.Items;
import com.daily.essentials.itemservice.repository.ItemRepository;
import com.daily.essentials.itemservice.service.implementation.Itemserviceimplementation;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ItemsControllerUnitTest
{
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    ItemsController itemsController;

    @Mock
    Itemserviceimplementation itemserviceimplementation;

    @Mock
    ItemRepository itemRepository;

    @Test
    public void testAddNewItem()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Item item = new Item();
        item.setId(1);

        when(itemserviceimplementation.addNewItem(any(Item.class))).thenReturn(item);

        Item itemToAdd = new Item("Rice", "CEREAL", "basmati rice", "E:/photo.jpg", true);
        ResponseEntity<Object> responseEntity = itemsController.addNewItem(itemToAdd);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    public void testGetAllItems()
    {
        // given
        Item item1 = new Item("Rice", "CEREAL", "basmati rice", "E:/photo.jpg", true);
        Item item2 = new Item("dal", "CEREAL", "basmati rice", "E:/photo.jpg", true);
        List<Item> list = new ArrayList<Item>();
        list.addAll(Arrays.asList(item1, item2));
        Items items=new Items();
        items.setItemList(list);

        when(itemserviceimplementation.getAllItems()).thenReturn(list);
        // when
        Items itemsr = itemsController.getAllItems();
        // then
        assertThat(itemsr.getItemList().size()).isEqualTo(2);
        assertThat(itemsr.getItemList().get(0).getName().equals(item1.getName()));
        assertThat(itemsr.getItemList().get(1).getName().equals(item2.getName()));
    }
    @Test
    public void testGetItemByid() throws Exception
    {
        Item item  = new Item(1,"Rice", "CEREAL", "basmati rice", "E:/photo.jpg", true);
        when(itemserviceimplementation.getItemByid(1)).thenReturn(item);
        Item itemr=itemsController.getItemByid(1);
        assertThat(itemr.getName().equals(item.getName()));
    }
}
