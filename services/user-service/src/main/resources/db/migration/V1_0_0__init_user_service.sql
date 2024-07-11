CREATE TABLE IF NOT EXISTS users
(
    id         VARCHAR(255) NOT NULL PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    role       VARCHAR(50)  NOT NULL,
    full_name  VARCHAR(255) NOT NULL,
    biography  TEXT,
    avatar_url VARCHAR(255),
    is_active  BOOLEAN      NOT NULL,
    created_at BIGINT       NOT NULL DEFAULT 0,
    updated_at BIGINT
);