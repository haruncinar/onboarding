
INSERT INTO allowed_country (id,country_name,country_code)
VALUES (1, 'Netherlands', 'NL' ),
       (2, 'Belgium', 'BG');


INSERT INTO currency (id,currency_name,creation_date)
VALUES (1, 'EURO', now() );

--
-- INSERT INTO id_document (
--                          id,
--                          citizen_id,
--                          nationality,
--     creation_date
-- ) VALUES (1, 1234, 'Turk', now());
--
--
--
--
-- INSERT INTO address (
--                          id,
--                          country,
--                          city,
--                          street,
--                          postal_code,
--                          door_number,
--     creation_date
--                      )
--         VALUES (1, 'The Netherlands', 'Utrecht', 'Amstel', '3522AB', 10, now());
--
-- INSERT INTO customer (
--     id,
--     name,
--     address_id,
--     birthday,
--     id_document_id,
--     password,
--     token,creation_date
-- ) VALUES (1, 'harun', 1, now() , 1, 'cinar', null, now());
--
-- INSERT INTO account (
--     id,
--     iban,
--     customer_id,
--     account_balance,
--     account_type,
--     currency_id,
--     creation_date
-- ) VALUES (1, 'NL01ABNA12345678001234', 1, 1234.5678, 'Family', 1, now());