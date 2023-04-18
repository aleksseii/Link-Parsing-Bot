-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE IF NOT EXISTS chat(
    chat_id         BIGINT      NOT NULL,

    CONSTRAINT chat_pk PRIMARY KEY(chat_id)
);

CREATE TABLE IF NOT EXISTS link(
    link_id         BIGSERIAL       NOT NULL,
    url             VARCHAR         NOT NULL,

    CONSTRAINT link_pk PRIMARY KEY(link_id)
);

CREATE TABLE IF NOT EXISTS chat_link(
    chat_id         BIGINT          NOT NULL
        REFERENCES chat(chat_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    link_id         BIGINT          NOT NULL
        REFERENCES link(link_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT chat_link_pk PRIMARY KEY(chat_id, link_id)
);
