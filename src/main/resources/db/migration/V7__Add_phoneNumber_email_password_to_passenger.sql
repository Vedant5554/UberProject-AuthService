ALTER TABLE booking_review
DROP
FOREIGN KEY FK_BOOKINGREVIEW_ON_BOOKING;

ALTER TABLE passenger_review
DROP
FOREIGN KEY FK_PASSENGERREVIEW_ON_ID;

ALTER TABLE passenger
    ADD email VARCHAR(255) NULL;

ALTER TABLE passenger
    ADD password VARCHAR(255) NULL;

ALTER TABLE passenger
    ADD phone_number VARCHAR(255) NULL;

ALTER TABLE passenger
    MODIFY email VARCHAR (255) NOT NULL;

ALTER TABLE passenger
    MODIFY password VARCHAR (255) NOT NULL;

ALTER TABLE passenger
    MODIFY phone_number VARCHAR (255) NOT NULL;

DROP TABLE booking_review;

DROP TABLE passenger_review;

ALTER TABLE driver
DROP
COLUMN address;

ALTER TABLE driver
DROP
COLUMN phone_number;

ALTER TABLE booking
DROP
COLUMN review_id;

ALTER TABLE booking
DROP
COLUMN booking_status;

ALTER TABLE booking
    ADD booking_status VARCHAR(255) NULL;

ALTER TABLE passenger
    MODIFY name VARCHAR (255) NOT NULL;