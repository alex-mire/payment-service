CREATE TABLE PAYMENT_SERVICE.BANK_ACCOUNT
(
    id                 UUID                                           NOT NULL PRIMARY KEY,
    account_holder_id  UUID references payment_service.account_holder NOT NULL,
    account_number     VARCHAR(255)                                   NOT NULL,
    type               VARCHAR(50)                                    NOT NULL,
    currency           VARCHAR(3)                                     NOT NULL,
    balance            DECIMAL(19, 4)                                 NOT NULL,
    interest_rate      DECIMAL(5, 4)                                  NOT NULL,
    open_date          TIMESTAMP                                      NOT NULL,
    close_date         TIMESTAMP,
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