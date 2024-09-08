CREATE TABLE PAYMENT_SERVICE.ACCOUNT_HOLDER_ADDRESS
(
    id                 UUID                                           NOT NULL PRIMARY KEY,
    account_holder_id  UUID references payment_service.account_holder NOT NULL,
    country            VARCHAR(255)                                   NOT NULL,
    county             VARCHAR(255)                                   NOT NULL,
    city               VARCHAR(255)                                   NOT NULL,
    postal_code        VARCHAR(20)                                    NOT NULL,
    street             VARCHAR(255)                                   NOT NULL,
    number             VARCHAR(20)                                    NOT NULL,
    building           VARCHAR(255),
    floor              VARCHAR(20),
    apartment_number   VARCHAR(20),
    created_date       TIMESTAMP,
    created_by         VARCHAR(255),
    last_modified_date TIMESTAMP,
    last_modified_by   VARCHAR(255),
    version            INT,
    CONSTRAINT fk_account_holder
        FOREIGN KEY (account_holder_id)
            REFERENCES PAYMENT_SERVICE.ACCOUNT_HOLDER (id)
            ON DELETE CASCADE
);