CREATE TABLE tag (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE profile_tags(
    profile_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (profile_id, tag_id),
    FOREIGN KEY (profile_id) REFERENCES user_profiles(user_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES ltag(id) ON DELETE CASCADE
);