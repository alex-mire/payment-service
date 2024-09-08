CREATE TABLE PAYMENT_SERVICE.ACCOUNT_HOLDER_USER
(
    id                 UUID                                           NOT NULL PRIMARY KEY,
    user_id            UUID                                           NOT NULL,
    account_holder_id  UUID references payment_service.account_holder NOT NULL,
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