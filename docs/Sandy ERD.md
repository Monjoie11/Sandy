### Sandy's ERD
Is a relatively simple one. Persisting within the app's learning will be a list of images to be called by the processor for comparison with each image.  

An initial deck of images will be downloaded upon install, with sandwiches unaligned with the user's sandwich personality being purged upon setup completion.

With continued use the app will track what images are rarely used as positive reference for a sandwich and purge them. Non-sandwiches will never be learned.

The ERD follows two one-to-many relationships as shown below:

![Sandy ERD PDF](Sandy%20ERD.pdf)

![Sandy ERD PNG](Sandy%20ERD.png)


###DDL For Data Model
```sqlite
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
```