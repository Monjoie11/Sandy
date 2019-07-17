CREATE TABLE IF NOT EXISTS `Sandwich`
(
    `sandwich_id`    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `sandwich_style` INTEGER                           NOT NULL,
    `machine_eat`    INTEGER                           NOT NULL,
    `human_eat`      INTEGER                           NOT NULL,
    `file_name`      TEXT,
    `image_resource` INTEGER                           NOT NULL
);

CREATE TABLE IF NOT EXISTS `Response`
(
    `response_id`       INTEGER NOT NULL,
    `response_category` INTEGER NOT NULL,
    `sandy_speaks`      TEXT,
    PRIMARY KEY (`response_id`)
);