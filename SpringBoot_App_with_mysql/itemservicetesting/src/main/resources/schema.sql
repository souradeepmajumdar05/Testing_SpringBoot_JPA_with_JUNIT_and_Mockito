-- DROP TABLE IF EXISTS item;
DROP ALL OBJECTS;

CREATE TABLE item (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  category enum('DAIRY','CEREALS','FRUITS','VEGETABLES','OILS','SPICES','MEDICINE','MEATSANDFISH','EGGS')  NOT NULL,
  itemdetails varchar(255) NOT NULL,
  image_url varchar(255) NOT NULL,
  first_enrollement_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  in_stock tinyint(1) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE itemcatalog (
  id int NOT NULL AUTO_INCREMENT,
  item_id int DEFAULT NULL,
  price int NOT NULL,
  currency_short enum('INR','USD') DEFAULT NULL,
  qty int NOT NULL,
  qty_type enum('gm','kg','l','kl','psc') DEFAULT NULL,
  time_of_entry timestamp NOT NULL,
  time_of_expire timestamp NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT itemcatalog FOREIGN KEY (item_id) REFERENCES item (id)
);