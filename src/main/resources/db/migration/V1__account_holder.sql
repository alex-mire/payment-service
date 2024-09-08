CREATE TABLE PAYMENT_SERVICE.ACCOUNT_HOLDER
(
    id                 UUID         NOT NULL PRIMARY KEY,
    first_name         VARCHAR(255) NOT NULL,
    last_name          VARCHAR(255) NOT NULL,
    birth_date         DATE         NOT NULL,
    created_date       TIMESTAMP,
    created_by         VARCHAR(255),
    last_modified_date TIMESTAMP,
    last_modified_by   VARCHAR(255),
    version            INT
);