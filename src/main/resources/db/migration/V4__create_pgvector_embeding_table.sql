-- migrations/001_create_profile_embeddings.sql

BEGIN;

CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE profile_embeddings (
                                    id           BIGSERIAL       PRIMARY KEY,
                                    user_id      BIGSERIAL           NOT NULL UNIQUE,
                                    embedding    vector(1536)    NOT NULL
);

CREATE INDEX idx_profile_embeddings_hnsw
    ON profile_embeddings
    USING hnsw (embedding vector_cosine_ops);

CREATE INDEX idx_profile_embeddings_user_id
    ON profile_embeddings (user_id);

COMMIT;
