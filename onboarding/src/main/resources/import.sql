CREATE TABLE customer (
                      id BIGINT PRIMARY KEY,
                      name VARCHAR(50),
                      address_id BIGINT REFERENCES address,
                      birthday DATE(),
                      id_document_id BIGINT REFERENCES id_document,
                      password VARCHAR(15),
                      token VARCHAR(255),
                      session_valid INT,
                      creation_date timestamp default current_timestamp
);

CREATE TABLE address (
                         id BIGINT PRIMARY KEY,
                         country VARCHAR(50),
                         city VARCHAR(50),
                         street VARCHAR(50),
                         postal_code VARCHAR(10),
                         door_number VARCHAR(4),
                         creation_date timestamp default current_timestamp
                     );

CREATE TABLE account (
                         id BIGINT PRIMARY KEY,
                         iban VARCHAR(22),
                         customer_id BIGINT REFERENCES customer,
                         account_type VARCHAR(22),
                         account_balance DECIMAL(15,5),
                         currency_id BIGINT REFERENCES currency,
                         CONSTRAINT unique_iban UNIQUE (iban),
                         CONSTRAINT unique_account_type CHECK (account_type IN ('Individual', 'Family', 'Young')),
                         creation_date timestamp default current_timestamp
);

CREATE TABLE id_document (
                         id BIGINT PRIMARY KEY,
                         citizen_id BIGINT,
                         nationality VARCHAR(50),
                         creation_date timestamp default current_timestamp
);

CREATE TABLE currency (
                             id BIGINT PRIMARY KEY,
                             currency_name VARCHAR(50),
                             creation_date timestamp default current_timestamp
);

CREATE TABLE allowed_country (
                          id BIGINT PRIMARY KEY,
                          country_name VARCHAR(50),
                          country_code VARCHAR(2),
                          CONSTRAINT country_unique UNIQUE (country_code, country_name)
);
