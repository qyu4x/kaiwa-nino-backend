CREATE TABLE IF NOT EXISTS chats
(
    id                VARCHAR(255) NOT NULL PRIMARY KEY,
    user_sender_id    VARCHAR(255) NOT NULL,
    user_recipient_id VARCHAR(255) NOT NULL,
    room_id           VARCHAR(255) NOT NULL,
    message           TEXT         NOT NULL,
    read_at           BIGINT,
    is_active         BOOLEAN      NOT NULL,
    created_at        BIGINT       NOT NULL DEFAULT 0,
    updated_at        BIGINT
);