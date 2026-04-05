CREATE TABLE users (
                       id       BIGSERIAL    NOT NULL,
                       username VARCHAR(150) NOT NULL,
                       password VARCHAR(250) NOT NULL,
                       role     VARCHAR(20)  NOT NULL DEFAULT 'USER',
                       PRIMARY KEY (id)
);

CREATE TABLE user_profiles (
                               user_id         BIGINT       PRIMARY KEY NOT NULL,
                               bio             VARCHAR(256),
                               favorite_animal VARCHAR(256),
                               magic_place     VARCHAR(256),
                               age             INTEGER,
                               CONSTRAINT fk_user_profiles_user
                                   FOREIGN KEY (user_id) REFERENCES users(id)
                                       ON DELETE CASCADE
                                       ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tag (
                                   id   BIGSERIAL    PRIMARY KEY,
                                   nome VARCHAR(50)  NOT NULL UNIQUE
    );

CREATE TABLE IF NOT EXISTS profile_tags (
                                            profile_id BIGINT NOT NULL,
                                            tag_id     BIGINT NOT NULL,
                                            PRIMARY KEY (profile_id, tag_id),
    FOREIGN KEY (profile_id) REFERENCES user_profiles(user_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE
    );