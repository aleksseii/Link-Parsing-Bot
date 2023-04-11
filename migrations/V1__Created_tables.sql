-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE chat(
    chat_id         SERIAL PRIMARY KEY NOT NULL,
    tg_chat_id      INTEGER            NOT NULL
);

CREATE TABLE link(
    link_id         SERIAL PRIMARY KEY NOT NULL,
    url             VARCHAR(255)       NOT NULL,
);

CREATE TABLE chat_link(
    chat_id BIGINT      NOT NULL
        REFERENCES chat(chat_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    link_id BIGINT      NOT NULL
        REFERENCES link(link_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
