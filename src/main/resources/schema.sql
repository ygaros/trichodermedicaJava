CREATE TABLE IF NOT EXISTS image(
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    path VARCHAR(255) NOT NULL
);
--CREATE TABLE if NOT EXISTS czynnosc (
--    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
--    name VARCHAR(255) NOT NULL
--);
CREATE TABLE IF NOT EXISTS zabieg (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    category VARCHAR(255) ,
    name VARCHAR(255) ,
    description MEDIUMTEXT,
    url_path VARCHAR(255) ,
    duration BIGINT ,
    price_once DECIMAL(10,2) ,
    price_series DECIMAL(10,2) ,
    image_id BIGINT
);
ALTER TABLE zabieg
    ADD FOREIGN KEY (image_id) REFERENCES image(id);
--CREATE TABLE IF NOT EXISTS zabieg_procedures(
--    zabieg_id BIGINT NOT NULL,
--    procedures_id BIGINT NOT NULL
--);
--ALTER TABLE zabieg_procedures
--    ADD FOREIGN KEY (zabieg_id) REFERENCES zabieg(id);
--ALTER TABLE zabieg_procedures
--    ADD FOREIGN KEY (procedures_id) REFERENCES czynnosc(id);
CREATE TABLE IF NOT EXISTS usluga (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    category VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price_once DECIMAL(10,2) NOT NULL,
    price_series DECIMAL(10,2) NOT NULL,
    zabieg_id BIGINT
);
ALTER TABLE usluga
    ADD FOREIGN KEY (zabieg_id) REFERENCES zabieg(id);
CREATE TABLE IF NOT EXISTS problem (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description MEDIUMTEXT NOT NULL,
    name TEXT(1000) NOT NULL,
    url_path VARCHAR(255) NOT NULL,
    image_id BIGINT NOT NULL
);
ALTER TABLE problem
    ADD FOREIGN KEY (image_id) REFERENCES image(id);
CREATE TABLE IF NOT EXISTS kontakt (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    house_number VARCHAR(25) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    zip_code VARCHAR(15) NOT NULL,
    street VARCHAR(128) NOT NULL,
    city VARCHAR(128) NOT NULL
);
CREATE TABLE IF NOT EXISTS open_hours (
    day VARCHAR(50) PRIMARY KEY,
    open VARCHAR(25) NOT NULL,
    close VARCHAR(25) NOT NULL
);
CREATE TABLE IF NOT EXISTS kontakt_open_hours (
    kontakt_id BIGINT NOT NULL,
    open_hours_day VARCHAR(50) NOT NULL
);
ALTER TABLE kontakt_open_hours
    ADD FOREIGN KEY (kontakt_id) REFERENCES kontakt(id);
ALTER TABLE kontakt_open_hours
    ADD FOREIGN KEY (open_hours_day) REFERENCES open_hours(day);
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_date timestamp NOT NULL,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(128) unique NOT NULL
);
CREATE TABLE IF NOT EXISTS authority (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    authority VARCHAR(128) NOT NULL,
    username VARCHAR(128) NOT NULL
);
ALTER TABLE authority
    ADD FOREIGN KEY (username) REFERENCES user(username);
CREATE TABLE IF NOT EXISTS sender (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    phone VARCHAR(25)
);
CREATE TABLE IF NOT EXISTS message (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_date timestamp NOT NULL,
    answered BOOL NOT NULL,
    message MEDIUMTEXT NOT NULL,
    sender_id BIGINT NOT NULL
);
ALTER TABLE message
    ADD FOREIGN KEY (sender_id) REFERENCES sender(id);
CREATE TABLE IF NOT EXISTS nav_bar_href (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    url_path VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS order_table (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    czas_trwania VARCHAR(25) NOT NULL,
    user_id BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS proza (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nazwa VARCHAR(255) NOT NULL,
    tresc MEDIUMTEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS slideshow (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description MEDIUMTEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS slideshow_images (
    slideshow_id BIGINT NOT NULL,
    images_id BIGINT NOT NULL
);
ALTER TABLE slideshow_images
    ADD FOREIGN KEY (slideshow_id) REFERENCES slideshow(id);
ALTER TABLE slideshow_images
    ADD FOREIGN KEY (images_id) REFERENCES image(id);



