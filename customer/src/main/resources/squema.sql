CREATE TABLE IF NOT EXISTS `customer` (
                                          `customer_id` int AUTO_INCREMENT  PRIMARY KEY,
                                          `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL
    );

INSERT INTO CUSTOMER (CUSTOMER_ID, NAME, EMAIL, MOBILE_NUMBER)
VALUES (1, 'Teixaa', 'teixa@email.com', '11999998888');