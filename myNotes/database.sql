CREATE DATABASE crud_accenture ENCODING 'utf8';
create user crud_cloves with encrypted password '131089';
grant all privileges on database crud_accenture to crud_cloves;

--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA crud_accenture. TO crud_cloves;