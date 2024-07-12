CREATE TABLE IF NOT EXISTS contacts
(
    id               VARCHAR(255) NOT NULL PRIMARY KEY,
    user_id          VARCHAR(255) NOT NULL,
    saved_contact_id VARCHAR(100) NOT NULL,
    is_blocked       BOOLEAN      NOT NULL,
    is_active        BOOLEAN      NOT NULL,
    created_at       BIGINT       NOT NULL DEFAULT 0,
    updated_at       BIGINT
);