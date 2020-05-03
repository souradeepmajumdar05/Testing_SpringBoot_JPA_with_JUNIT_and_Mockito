INSERT INTO item (id, name, category, itemdetails,image_url,in_stock) VALUES
  (1, 'rice', 'CEREALS','Basmati Rice','E:Basmati-Rice-Long-Grain1kg.jpg',true),
  (2, 'dal', 'CEREALS','moong gal','E:India-Gate-Classic-dal1kg.jpg',true),
  (3, 'fortune oil', 'OILS','moong gal','E:fortune_oil.jpg',false);


INSERT INTO  itemcatalog (item_id, price, currency_short, qty, qty_type, time_of_entry, time_of_expire)
VALUES(1, 10, 'INR', 1000, 'kg',CURRENT_TIME(), CURRENT_TIME() );