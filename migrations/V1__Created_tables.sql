-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE chat(
    chat_id         BIGINT PRIMARY KEY NOT NULL,
);

CREATE TABLE link(
    link_id         BIGSERIAL PRIMARY KEY NOT NULL,
    url             VARCHAR       NOT NULL,
);

CREATE TABLE chat_link(
    chat_id         BIGINT NOT NULL
        REFERENCES chat(chat_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    link_id         BIGINT NOT NULL
        REFERENCES link(link_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
    PRIMARY KEY (chat_id, link_id)
);
