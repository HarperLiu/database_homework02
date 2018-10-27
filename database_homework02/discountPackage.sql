lock tables `discount_package` write;
insert into `discount_package` values (1,100,0,0,0,20),(2,0,200,0,0,10),(3,0,0,0,2048,20),(4,0,0,2048,0,30);
unlock tables;
