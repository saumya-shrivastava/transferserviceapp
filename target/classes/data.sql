CREATE TABLE account (
    account_number bigint NOT NULL PRIMARY KEY,
    balance NUMERIC(10,3) NOT NULL,
    account_type varchar(50) NOT NULL
);

CREATE SEQUENCE transaction_seq START WITH 5;

CREATE TABLE transaction (
    transaction_id bigint NOT NULL PRIMARY KEY,
    source_account_number bigint NOT NULL REFERENCES account(account_number),
    destination_account_number bigint NOT NULL REFERENCES account(account_number),
    amount NUMERIC(10,3) NOT NULL,
    reference VARCHAR(255)
);

INSERT INTO account ( account_number, balance,account_type)
VALUES ( 111111, 1071.78,'CURRENT');
INSERT INTO account ( account_number, balance,account_type)
VALUES (222222, 1000.50,'SAVINGS');
INSERT INTO account ( account_number, balance,account_type)
VALUES (33333, 65,'SAVINGS');
INSERT INTO account ( account_number, balance,account_type)
VALUES (44444, 50,'CURRENT');

