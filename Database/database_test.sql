CREATE SCHEMA dtb_demo;

CREATE TABLE dtb_demo.`wallet` (
  id INT NOT NULL auto_increment,
  user_id INT NULL,
  balance DECIMAL NULL default 0,
  PRIMARY KEY (id));
  
  CREATE TABLE dtb_demo.`deposit` (
  id int NOT NULL,
  wallet_id INT NULL,
  amount DECIMAL NULL,
  description VARCHAR(500) NULL,
  create_datetime timestamp NULL,
  PRIMARY KEY (id));

CREATE TABLE dtb_demo.`withdrawal` (
  id INT NOT NULL AUTO_INCREMENT,
  wallet_id INT NULL,
  amount DECIMAL NULL,
  create_datetime timestamp NULL,
  status NVARCHAR(100) NULL,
  bank_user NVARCHAR(100) NULL,
  bank_number NVARCHAR(100) NULL,
  bank_name NVARCHAR(100) NULL,
  update_datetime timestamp NULL,
  PRIMARY KEY (id));

CREATE TABLE dtb_demo.`purchase_order` (
  id INT NOT NULL,
  status VARCHAR(100) NULL,
  product_id INT NULL,
  quantity INT NULL,
  price DECIMAL NULL,
  total_price DECIMAL NULL,
  create_date DATETIME NULL,
  update_date DATETIME NULL,
  user_id INT NULL,
  PRIMARY KEY (id));
  
CREATE TABLE dtb_demo.`product` (
  id INT NOT NULL,
  name NVARCHAR(45) NULL,
  quantity INT NULL,
  category_id INT NULL,
  code VARCHAR(100) NULL,
  price DECIMAL NULL,
  description TEXT NULL,
  link_share TEXT NULL,
  image TEXT NULL,
  PRIMARY KEY (id));
  
CREATE TABLE dtb_demo.`categories` (
  id INT NOT NULL,
  name NVARCHAR(100) NULL,
  PRIMARY KEY (id));
 
CREATE TABLE dtb_demo.`account` (
  id INT NOT NULL AUTO_INCREMENT,
  name NVARCHAR(100) NULL,
  avatar VARCHAR(500) NULL,
  username VARCHAR(50) NULL,
  email VARCHAR(500) NULL,
  password text NULL,
  phonenumber VARCHAR(10) CHECK (phonenumber REGEXP '^[0-9]{10}$'),
  story text,
  role_id INT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE dtb_demo.`account_role` (
  id INT NOT NULL auto_increment,
  role NVARCHAR(50) NULL,
  PRIMARY KEY (id));

CREATE TABLE dtb_demo.`report` (
  id INT NOT NULL,
  title NVARCHAR(200) NULL,
  intermediary_order_id INT NULL,
  fee_type BIT NULL,
  content TEXT NULL,
  create_at DATETIME NULL,
  update_at DATETIME NULL,
  account_report_id INT NULL,
  PRIMARY KEY (id));

create table dtb_demo.`intermediary_order` (
id int not null auto_increment,
description nvarchar(500),
price decimal,
fee_type bit,
contact nvarchar(500),
hide_description nvarchar(500),
public_status bit,
created_at datetime,
updated_at datetime,
account_sold_id int,
account_buy_id int,
processed bit default 1,
order_status varchar(500),
intermediary_fee decimal,
link_to text,
title text,
PRIMARY KEY (id)
);

create table dtb_demo.`feedback` (
id int,
title nvarchar(100),
content nvarchar(500),
create_at datetime,
account_id int,
intermediary_order_id int,
primary key (id)
);

create table dtb_demo.`state` (
uniqueId int,
created_by int,
created_at datetime null,
updated_by int,
updated_at datetime null,
is_deleted bit,
deleted_by int,
deleted_at datetime null,
PRIMARY KEY (uniqueId)
);

create table VNPay_Transaction (
id int not null auto_increment,
-- 3 status: pending, fail, success
Status varchar(20),
Wallet_id int,
Payment_code varchar(100),
Time text,
Description text,
BankCode varchar(10),
primary key(id)
);

create table dtb_demo.`status_history` (
id int not null auto_increment,
orderstatus nvarchar(500),
description text,
timechange datetime,
productid int,
PRIMARY KEY (id)
);

create table transaction_history (
  user_id int,
  id int not null auto_increment,
  money DECIMAL NULL,
  type BIT ,
  work BIT default 1,
  note varchar(50) ,
  create_by varchar(100) ,
  create_at TIMESTAMP ,
  update_at TIMESTAMP ,  
  PRIMARY KEY (id)
);

alter table status_history
add foreign key (productid) references intermediary_order(id);

alter table transaction_history
add foreign key (user_id) references account(id);

alter table VNPay_Transaction add foreign key (Wallet_id) references Wallet(id);

alter table deposit
add foreign key (wallet_id) references wallet(id);

alter table withdrawal
add foreign key (wallet_id) references wallet(id);

alter table wallet
add foreign key (user_id) references account(id);

alter table account
add foreign key (role_id) references account_role(id);

alter table purchase_order
add foreign key (product_id) references product(id);

alter table purchase_order
add foreign key (user_id) references account(id);

alter table product
add foreign key (category_id) references categories(id);

alter table feedback
add foreign key (account_id) references account(id);

alter table report
add foreign key (intermediary_order_id) references intermediary_order(id);

alter table report
add foreign key (account_report_id) references account(id);

alter table intermediary_order
add foreign key (account_sold_id) references account(id);

INSERT INTO dtb_demo.`account_role` (id, role) VALUES ('1', 'Admin');
INSERT INTO dtb_demo.`account_role` (id, role) VALUES ('2', 'User');

INSERT INTO dtb_demo.`account` (name, username, email, password, role_id) VALUES ('Tran Minh Quang', 'minhquang123', 'quangtmhe171602@fpt.edu.vn', 'sto6Moh/YDfs8nycY6iJOWBzDh4=', '2');
INSERT INTO dtb_demo.`account` (name, username, email, password, role_id) VALUES ('Cao Thanh Tung', 'thanhtung123', 'tungcthe176530@fpt.edu.vn', 'sto6Moh/YDfs8nycY6iJOWBzDh4=', '2');
INSERT INTO dtb_demo.`account` (name, username, email, password, role_id) VALUES ('Nguyen Xuan Phuc', 'xuanphuc123', 'phucnxhe171113@fpt.edu.vn', 'sto6Moh/YDfs8nycY6iJOWBzDh4=', '2');
INSERT INTO dtb_demo.`account` (name, username, email, password, role_id) VALUES ('Duong Van Hoan', 'vanhoan123', 'hoandvhe176022@fpt.edu.vn', 'sto6Moh/YDfs8nycY6iJOWBzDh4=', '1');
INSERT INTO dtb_demo.`account` (name, username, email, password, role_id) VALUES ('Bui Duc Toan', 'ductoan123', 'toanbd161559@fpt.edu.vn', 'sto6Moh/YDfs8nycY6iJOWBzDh4=', '2');

INSERT INTO dtb_demo.`wallet` (user_id, balance) VALUES ('1', '200000');
INSERT INTO dtb_demo.`wallet` (user_id, balance) VALUES ('2', '200000');
INSERT INTO dtb_demo.`wallet` (user_id, balance) VALUES ('3', '200000');
INSERT INTO dtb_demo.`wallet` (user_id, balance) VALUES ('4', '200000');
INSERT INTO dtb_demo.`wallet` (user_id, balance) VALUES ('5', '200000');
INSERT INTO dtb_demo.`wallet` (user_id, balance) VALUES ('5', '200000');

INSERT INTO dtb_demo.`intermediary_order` (description, price, fee_type, contact, hide_description, public_status, created_at, updated_at, account_sold_id, account_buy_id,processed, order_status, intermediary_fee, link_to, title)VALUES ('123', '200', 0 , '12345', 'cba', 0, '2024-1-28', '2024-1-28', '4', null,1, 'ready to trade','200', 'null', 'test2');
INSERT INTO dtb_demo.`intermediary_order` (description, price, fee_type, contact, hide_description, public_status, created_at, updated_at, account_sold_id, account_buy_id,processed, order_status, intermediary_fee, link_to, title)VALUES ('124', '200', 1 , '12345', 'cba', 1, '2024-1-28', '2024-1-28', '3', '2',1, 'transacted','200', 'null', 'test1');
INSERT INTO dtb_demo.`intermediary_order` (description, price, fee_type, contact, hide_description, public_status, created_at, updated_at, account_sold_id, account_buy_id,processed, order_status, intermediary_fee, link_to, title)VALUES ('125', '200', 0 , '12345', 'cba', 0, '2024-1-28', '2024-1-28', '2', '3',1, 'transacted','200', 'null', 'test1');