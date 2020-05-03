package com.daily.essentials.itemservice.controller;

import com.daily.essentials.itemservice.ItemserviceApplication;
import com.daily.essentials.itemservice.model.Item;
import com.daily.essentials.itemservice.model.Items;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ItemserviceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"server.port:0", "eureka.client.enabled:false"})
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql({ "classpath:schema.sql", "classpath:data.sql" })
public class ItemControllerIntegrationTest
{
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAllItems()
    {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/v1/items", Items.class)
                        .getItemList().size() == 2);
    }

    @Test
    public void testAddItem() {
        Item item = new Item(3, "milk", "DAIRY", "amul milk", "E:amul.jpg",true);
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/v1/items", item, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetItemById_200() {
        assertTrue( this.restTemplate
                .getForObject("http://localhost:" + port + "/v1/items/1", Item.class)
                .getName().equals("rice"));

    }
}
