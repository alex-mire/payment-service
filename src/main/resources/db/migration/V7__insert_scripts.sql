
insert into payment_service.account_holder(id, first_name, last_name, birth_date, created_date, created_by, last_modified_date, last_modified_by, version)
values ('0ee5ab4c-c321-439b-99a2-0a756e1afbb2', 'Alexandru', 'Mireuta', '1998-10-18', now(), 'admin', now(), 'admin', 0);

insert into payment_service.account_holder(id, first_name, last_name, birth_date, created_date, created_by, last_modified_date, last_modified_by, version)
values ('d339df31-9f00-4673-8ab7-6acf9f29f075', 'Vasile', 'Popescu', '1995-11-15', now(), 'admin', now(), 'admin', 0);

insert into payment_service.account_holder_address(id, account_holder_id, country, county, city, postal_code, street, number, building, floor, apartment_number, created_date, created_by, last_modified_date, last_modified_by, version)
values ('43af7031-1330-4a92-bdfc-e32e9aa50525', '0ee5ab4c-c321-439b-99a2-0a756e1afbb2', 'Romania', 'Bucuresti', 'Sector 6', '623350',
        'Bd. Timisoara', '16B', '2', '2', '2', now(), 'admin', now(), 'admin', 0);

insert into payment_service.account_holder_address(id, account_holder_id, country, county, city, postal_code, street, number, building, floor, apartment_number, created_date, created_by, last_modified_date, last_modified_by, version)
values ('0d404a45-cdd9-463d-8af3-7d9e55bbbfcb', 'd339df31-9f00-4673-8ab7-6acf9f29f075', 'Romania', 'Bucuresti', 'Sector 5', '553350',
        'Bd. Timisoara', '12B', '1', '1', '1', now(), 'admin', now(), 'admin', 0);

insert into payment_service.bank_account(id, account_holder_id, account_number, type, currency, balance, interest_rate, open_date, close_date, created_date, created_by, last_modified_date, last_modified_by, version)
values ('b5b42a46-5df0-4168-886a-6c0f69ebd9a5', '0ee5ab4c-c321-439b-99a2-0a756e1afbb2', '231332133212', 'CHECKING',
        'RON', 3213.22, 0, now(), '2030-10-10', now(), 'admin', now(), 'admin', 0);

insert into payment_service.bank_account(id, account_holder_id, account_number, type, currency, balance, interest_rate, open_date, close_date, created_date, created_by, last_modified_date, last_modified_by, version)
values ('e2ed44b3-bfe6-45a1-aa1f-f82cdbce188b', 'd339df31-9f00-4673-8ab7-6acf9f29f075', '765732133212', 'CHECKING',
        'RON', 534213.22, 0, now(), '2035-10-10', now(), 'admin', now(), 'admin', 0);
