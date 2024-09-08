CREATE TABLE PAYMENT_SERVICE.INTERNAL_TRANSACTION
(
    id                 UUID           NOT NULL PRIMARY KEY,
    account_holder_id  UUID references payment_service.account_holder NOT NULL,
    source_id          UUID           NOT NULL,
    destination_id     UUID           NOT NULL,
    amount             DECIMAL(19, 4) NOT NULL,
    currency           VARCHAR(3)     NOT NULL,
    fee                DECIMAL(19, 4) NOT NULL,
    description        TEXT,
    transaction_date   TIMESTAMP      NOT NULL,
    type               VARCHAR(50)    NOT NULL,
    created_date       TIMESTAMP,
    created_by         VARCHAR(255),
    last_modified_date TIMESTAMP,
    last_modified_by   VARCHAR(255),
    version            INT
);