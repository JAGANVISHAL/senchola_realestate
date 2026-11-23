CREATE DATABASE real_estate_db;

USE real_estate_db;

CREATE TABLE properties (
    property_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    property_type VARCHAR(50),
    location VARCHAR(100),
    price DOUBLE,
    bedrooms INT,
    area_sqft INT,
    description TEXT,
    status VARCHAR(20) DEFAULT 'Available'
);

CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    property_id INT,
    customer_name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    visit_date DATE,
    booking_status VARCHAR(20),
    FOREIGN KEY (property_id) REFERENCES properties(property_id)
);
INSERT INTO properties (title, property_type, location, price, bedrooms, area_sqft, description)
VALUES ('2BHK Flat', 'Apartment', 'Salem', 3500, 2, 1000, 'Near bus stand');

SELECT * FROM properties;
SELECT * FROM bookings;