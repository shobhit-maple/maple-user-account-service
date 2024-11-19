CREATE TABLE user_account
(
    id              VARCHAR(255) PRIMARY KEY NOT NULL,
    organization_id VARCHAR(255)             NOT NULL,
    first_name      VARCHAR(255)             NOT NULL,
    last_name       VARCHAR(255),
    date_of_birth   VARCHAR(12)              NOT NULL
);

CREATE TABLE user_image
(
    id              VARCHAR(255) PRIMARY KEY NOT NULL,
    user_account_id VARCHAR(255)             NOT NULL UNIQUE,
    full_size       BYTEA                    NOT NULL,
    thumbnail       BYTEA                    NOT NULL,
    CONSTRAINT user_image_user_account_id_fk FOREIGN KEY (user_account_id) REFERENCES user_account (id)
);
