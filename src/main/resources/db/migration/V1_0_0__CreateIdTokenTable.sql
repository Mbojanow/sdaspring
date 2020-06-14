CREATE TABLE id_tokens (
    sub VARCHAR(255),
    iss VARCHAR(255),
    iat BIGINT,
    exp BIGINT,
    primary key (sub)
);