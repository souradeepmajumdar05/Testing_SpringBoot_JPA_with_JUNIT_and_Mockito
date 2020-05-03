package com.daily.essentials.itemservice.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "item", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID") })
//@Data
public class Item implements Serializable
{
	
	 public Item() {
			//super();
		}
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;
    @Column(name = "category", unique = true, nullable = false, length = 100)
    private String category;
    @Column(name = "itemdetails", unique = true, nullable = false, length = 100)
    private String itemdetails;
    @Column(name = "image_url", unique = true, nullable = false, length = 100)
    private String imageUrl;
    @Column(name = "first_enrollement_date", unique = true, nullable = false, length = 100)
    private Timestamp first_enrollement_date;
    @Column(name = "in_stock", unique = true, nullable = false, length = 100)
    private Boolean inStock;

    //@OneToMany
    //@JoinColumn(name="item_id")
    @OneToMany(/*cascade=CascadeType.ALL, */mappedBy = "itemId", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    private Set<Itemcatalog> itemcatalogs;

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", category=" + category + ", itemdetails=" + itemdetails
				+ ", image_url=" + imageUrl + ", first_enrollement_date=" + first_enrollement_date + ", in_stock="
				+ inStock + ", itemcatalogs=" + itemcatalogs + "]";
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemdetails() {
        return itemdetails;
    }

    public void setItemdetails(String itemdetails) {
        this.itemdetails = itemdetails;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getFirst_enrollement_date() {
        return first_enrollement_date;
    }

    public void setFirst_enrollement_date(Timestamp first_enrollement_date) {
        this.first_enrollement_date = first_enrollement_date;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public Set<Itemcatalog> getItemcatalogs() {
        return itemcatalogs;
    }

    public void setItemcatalogs(Set<Itemcatalog> itemcatalogs) {
        this.itemcatalogs = itemcatalogs;
    }

    public Item( String name, String category, String itemdetails, String imageUrl,Boolean inStock) {
        this.name = name;
        this.category = category;
        this.itemdetails = itemdetails;
        this.imageUrl = imageUrl;
        this.inStock = inStock;
    }
    public Item(Integer id, String name, String category, String itemdetails, String imageUrl,Boolean inStock) {
	    this.id=id;
        this.name = name;
        this.category = category;
        this.itemdetails = itemdetails;
        this.imageUrl = imageUrl;
        this.inStock = inStock;
    }
}
