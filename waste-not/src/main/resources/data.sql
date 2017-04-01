delete from groups;
delete from groups_members;
delete from groups_products_shared_to_group;
delete from location;
delete from products;
delete from user_wish_list;
delete from users;
delete from users_friends;
delete from users_groups;
delete from users_products_recieved;
delete from users_products_requested_by_others;
delete from users_products_requested_by_user;
delete from users_products_shared;

-- location
insert into location(id,street,streetNO,postalcode,city,country) values (1,'wackawacka',12,8045,'Zurich','Schweiz');
insert into location(id,street,streetNO,postalcode,city,country) values (2,'Alphaweg',122,80045,'LalaCity','LalaLand');
insert into location(id,street,streetNO,postalcode,city,country) values (3,'Alphaweg',122,80045,'LalaCity','LalaLand');
insert into location(id,street,streetNO,postalcode,city,country) values (4,'Alphaweg',122,80045,'LalaCity','LalaLand');
insert into location(id,street,streetNO,postalcode,city,country) values (5,'Alphaweg',122,80045,'LalaCity','alaLand');
insert into location(id,street,streetNO,postalcode,city,country) values (6,'Alphaweg',122,80045,'LalaCity','LalaLand');
insert into location(id,street,streetNO,postalcode,city,country) values (7,'Alphaweg',122,80045,'LalaCity','LalaLand');

-- users
 
insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
 values (1,'fake1@fake.com','Lola','Lolly','a','a',1,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/128/Female-Face-FA-4-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
 values (2,'fake2@fake.com','Jenny','Jade','a','jenny',2,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/128/Female-Face-FH-4-slim-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
values (3,'fake3@fake.com','Delyla','Delux','a','della','3','http://icons.iconarchive.com/icons/hopstarter/face-avatars/128/Female-Face-FA-4-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
values (4, 'fake4@fake.com','Rose',  'Dorn','a','rosy',4,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/128/Female-Face-FA-4-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar)  
values (5, 'fake5@fake.com','Abbey',  'Broken','a','abbey', 5,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/128/Male-Face-A2-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
values (6, 'fake6@fake.com', 'Francine',  'Smith','a','francy',6,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/128/Female-Face-FA-4-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
values (7,'fake7@fake.com','Pemmela','Rock','a','pemmy',2,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/256/Female-Face-FI-2-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
values (8,'fake8@fake.com','Courtney','Sweets','a','courtney',5,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/256/Female-Face-FI-2-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
values (9,'fake9@fake.com','Jennifer','Jody','a','jennifer',7,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/256/Female-Face-FI-2-icon.png');

insert into users(id, email,first_name,last_name,password,username,location_id, avatar) 
values (10,'fake10@fake.com','Christy','Pew','a','christy',6,'http://icons.iconarchive.com/icons/hopstarter/face-avatars/256/Female-Face-FI-2-icon.png');


-- groups
insert into groups(id,description,group_name,admin_id,group_image) values (1,'Group From Work','Food Mates',1,'https://t3.ftcdn.net/jpg/00/68/95/46/240_F_68954664_mlJE0mMHPjSetDUbABgRjMULZMnKmgCO.jpg');
insert into groups(id,description,group_name,admin_id,group_image) values (2,'Group From Neighbourhood','Neighbours',1,'https://t4.ftcdn.net/jpg/00/33/04/77/240_F_33047796_94uyyU5k8vi2thoiIMrBlF7UeoDCzhUz.jpg');
insert into groups(id,description,group_name,admin_id,group_image) values (3,'Group of Friends','ReUseIT',2,'https://t3.ftcdn.net/jpg/00/84/41/72/240_F_84417253_oXspqHSvoQERIkM53oNRDghSSkQI3PWh.jpg');
insert into groups(id,description,group_name,admin_id,group_image) values (4,'Group From Winti','Winti against Waste',3,'https://t4.ftcdn.net/jpg/01/06/57/29/240_F_106572993_NYr7c8INV64R7T6RHTrSpybOO8taLRRZ.jpg');
insert into groups(id,description,group_name,admin_id,group_image) values (5,'Group From Verein','Verein against Waste',3,'https://t3.ftcdn.net/jpg/01/36/75/58/240_F_136755886_KNnuZ8e8Y41Y7SUnafAgm9bhAhXoLB5V.jpg');
insert into groups(id,description,group_name,admin_id,group_image) values (6,'Group From School','ReUseIT - School',4,'https://t4.ftcdn.net/jpg/01/06/57/29/240_F_106572993_NYr7c8INV64R7T6RHTrSpybOO8taLRRZ.jpg');
insert into groups(id,description,group_name,admin_id,group_image) values (7,'Group From College','ReUseIT - College',5,'https://t4.ftcdn.net/jpg/00/33/47/51/240_F_33475194_bWazKqY4GqnKWh0ZW1VWHjdi7bfWVLzx.jpg');
insert into groups(id,description,group_name,admin_id,group_image) values (8,'Group From Church','ReUseIT - Church',5,'https://t3.ftcdn.net/jpg/01/25/11/64/240_F_125116470_wClwGpNIwmElCIgbtV6OYcL5HPWYXZjJ.jpg');

--products *location_id not added

insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (1,'Chair','Good Condition, Reason - Shifting to new house',true,'2017-01-01','Bahnhof-Winti', '17:00:00', 'FURNITURE', 1,'https://t4.ftcdn.net/jpg/00/89/61/13/240_F_89611334_wLGS8fNld3aLm7YhBjX17pZaNFyS4lCr.jpg');
  
insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (2,'Assorted Teas','Good Condition, Reason - Dont drink Tea',true,'2017-02-02','Bahnhof-Winti', '17:00:00', 'FOOD', 2,'https://t4.ftcdn.net/jpg/00/94/01/43/240_F_94014353_9h3fG8vWsPv82parrCo1GO7fVCZDY4cY.jpg');
    
insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (3,'Vegetables- Assorted','Good Condition, Reason - Going away on vacation',true,'2017-03-03','Bahnhof-Winti', '17:00:00', 'FOOD', 3,'https://t4.ftcdn.net/jpg/01/11/77/45/240_F_111774535_GomROlCAeTnMNNI61ESbu00qtCRoQkaX.jpg');
  
insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (4,'Lamps','Good Condition, Reason - Shifting to new house',true,'2017-04-04','Bahnhof-Winti', '17:00:00', 'FURNITURE', 1,'https://t3.ftcdn.net/jpg/00/70/54/72/240_F_70547285_MvLjoaPdtPNLlkqbNM1wUJh2xO7rfA5s.jpg');

insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (5,'Baby Toys','Good Condition, Reason - Baby doesnt like them anymore',true,'2017-05-05','Bahnhof-Winti', '17:00:00', 'BABIES', 1,'https://t3.ftcdn.net/jpg/00/61/54/54/240_F_61545484_ZiHTpyMwtErpuxztO1hN3pzbsLVuWDJv.jpg');
  
insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (6,'Sunscreen','Good Condition, Reason - Allergic to constituents',true,'2017-05-05','Bahnhof-Winti', '17:00:00', 'MISCELLANEOUS', 1,'https://t4.ftcdn.net/jpg/00/67/63/23/240_F_67632399_brFC1GmA3ceq848YkjjdeBjYRAAE6oHY.jpg');

insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (7,'Muffins','Good Condition, Reason - Made too many',true,'2017-03-03','Bahnhof-Winti', '17:00:00', 'FOOD', 1,'https://t4.ftcdn.net/jpg/00/55/21/61/240_F_55216135_E9NKJQCQyqOJFGpJ51ktp6gHrYONvBOz.jpg');
  
insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (8,'Croissants','Good Condition, Reason - Over Production',true,'2017-06-06','Bahnhof-Winti', '17:00:00', 'FOOD', 2,'http://www.bangbangent.com/images/product-placeholder.jpg');

insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (9,'Tomatoes','Good Condition, Reason - Going away on vacation',true,'2017-03-03','Bahnhof-Winti', '17:00:00', 'FOOD', 2,'https://t4.ftcdn.net/jpg/00/69/28/27/240_F_69282769_nnGX7SidAFQs8SwUgmZFx5Zlz6sXRkl4.jpg');
  
insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (10,'Lamps','Good Condition, Reason - Shifting to new house',true,'2017-04-04','Bahnhof-Winti', '17:00:00', 'FURNITURE', 3,'https://t3.ftcdn.net/jpg/00/70/54/72/240_F_70547285_MvLjoaPdtPNLlkqbNM1wUJh2xO7rfA5s.jpg');

insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (11,'Baby Toys','Good Condition, Reason - Baby doesnt like them anymore',true,'2017-05-05','Bahnhof-Winti', '17:00:00', 'BABIES', 2,'http://www.bangbangent.com/images/product-placeholder.jpg');
  
insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (12,'Sunscreen','Good Condition, Reason - Allergic to constituents',true,'2017-05-05','Bahnhof-Winti', '17:00:00', 'MISCELLANEOUS', 2,'http://www.bangbangent.com/images/product-placeholder.jpg');

insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (13,'Muffins','Good Condition, Reason - Made too many',true,'2017-03-03','Bahnhof-Winti', '17:00:00', 'FOOD', 3,'https://t4.ftcdn.net/jpg/00/55/21/61/240_F_55216135_E9NKJQCQyqOJFGpJ51ktp6gHrYONvBOz.jpg');
  
insert into products(id,product_name, description,available,date_posted,pickup_location,pickup_time,product_category,product_owner_id,product_image_url) 
values (14,'Croissants','Good Condition, Reason - Over Production',true,'2017-06-06','Bahnhof-Winti', '17:00:00', 'FOOD', 4,'http://www.bangbangent.com/images/product-placeholder.jpg');
 
 
-- users_groups;
insert into users_groups(user_id, groups_id) values (1,1);
insert into users_groups(user_id, groups_id) values (2,1);
insert into users_groups(user_id, groups_id) values (3,1);
insert into users_groups(user_id, groups_id) values (1,2);
insert into users_groups(user_id, groups_id) values (1,3);
insert into users_groups(user_id, groups_id) values (2,2);
insert into users_groups(user_id, groups_id) values (3,3);
insert into users_groups(user_id, groups_id) values (4,1);
insert into users_groups(user_id, groups_id) values (4,5);
insert into users_groups(user_id, groups_id) values (5,4);
insert into users_groups(user_id, groups_id) values (6,3);
insert into users_groups(user_id, groups_id) values (7,5);

-- groups_members 
insert into groups_members(group_id, members_id) values (1,1);
insert into groups_members(group_id, members_id) values (1,2);
insert into groups_members(group_id, members_id) values (1,3);
insert into groups_members(group_id, members_id) values (2,1);
insert into groups_members(group_id, members_id) values (3,1);
insert into groups_members(group_id, members_id) values (2,2);
insert into groups_members(group_id, members_id) values (3,3);
insert into groups_members(group_id, members_id) values (1,4);
insert into groups_members(group_id, members_id) values (5,4);
insert into groups_members(group_id, members_id) values (4,5);
insert into groups_members(group_id, members_id) values (3,6);
insert into groups_members(group_id, members_id) values (5,7);

--  users_friends;
insert into  users_friends(user_id, friends_id) values (1,1);
insert into  users_friends(user_id, friends_id) values (2,1);
insert into  users_friends(user_id, friends_id) values (3,1);
insert into  users_friends(user_id, friends_id) values (1,2);
insert into  users_friends(user_id, friends_id) values (1,3);
insert into  users_friends(user_id, friends_id) values (2,2);
insert into  users_friends(user_id, friends_id) values (3,3);
insert into  users_friends(user_id, friends_id) values (4,1);
insert into  users_friends(user_id, friends_id) values (4,5);
insert into  users_friends(user_id, friends_id) values (5,4);
insert into  users_friends(user_id, friends_id) values (6,3);
insert into  users_friends(user_id, friends_id) values (7,5);

-- user_wish_list
insert into  user_wish_list(user_id, wish_list) values (1,'Veggies,Tools,Craft');
insert into  user_wish_list(user_id, wish_list) values (2,'Coffee,Furniture');
insert into  user_wish_list(user_id, wish_list) values (3,'Fruits,Sofa');
insert into  user_wish_list(user_id, wish_list) values (4,'Nuts,Kitchen Appliances');
insert into  user_wish_list(user_id, wish_list) values (5,'Coffee,Baby Stuff');
insert into  user_wish_list(user_id, wish_list) values (6,'Charger,Tools,Craft');


-- users_products_shared
--insert into  users_products_shared(user_id, products_shared_id) values (1,1);
--insert into  users_products_shared(user_id, products_shared_id) values (1,4);
--insert into  users_products_shared(user_id, products_shared_id) values (2,2);
--insert into  users_products_shared(user_id, products_shared_id) values (3,6);

-- users_products_recieved 
--insert into  users_products_recieved(user_id,  products_recieved_id ) values (1,6);
--insert into  users_products_recieved(user_id,  products_recieved_id ) values (2,4);
--insert into  users_products_recieved(user_id,  products_recieved_id ) values (3,2);
--insert into  users_products_recieved(user_id,  products_recieved_id ) values (4,1);




-- groups_products_shared_to_group

insert into groups_products_shared_to_group(group_id,  products_shared_to_group_id ) values (1,1);
insert into groups_products_shared_to_group(group_id,  products_shared_to_group_id ) values (2,2);
insert into groups_products_shared_to_group(group_id,  products_shared_to_group_id ) values (1,3);
insert into groups_products_shared_to_group(group_id,  products_shared_to_group_id ) values (2,4);
insert into groups_products_shared_to_group(group_id,  products_shared_to_group_id ) values (3,5);
insert into groups_products_shared_to_group(group_id,  products_shared_to_group_id ) values (2,6);


 


















   