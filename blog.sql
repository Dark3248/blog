-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 25
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`     int(11) NOT NULL AUTO_INCREMENT,
    `nickname`   varchar(255) DEFAULT NULL,
    `username`    varchar(255) DEFAULT NULL,
    `password`    varchar(255) DEFAULT NULL,
    `email`       varchar(255) DEFAULT NULL,
    `avatar`      varchar(255) DEFAULT NULL,
    `type`        int(11)      DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `title`           varchar(255) DEFAULT NULL,
    `content`         longtext,
    `first_picture`   varchar(255) DEFAULT NULL,
    `flag`            varchar(255) DEFAULT NULL,
    `views`           int(11)      DEFAULT NULL,
    `appreciation`    tinyint(1)   DEFAULT '0',
    `share_statement` tinyint(1)   DEFAULT '0',
    `comment`         tinyint(1)   DEFAULT '0',
    `publish`         tinyint(1)   DEFAULT '0',
    `recommend`       tinyint(1)   DEFAULT '0',
    `create_time`     datetime     DEFAULT NULL,
    `update_time`     datetime     DEFAULT NULL,
    `type_id`         int(11)      DEFAULT NULL,
    `user_id`         int(11)      DEFAULT NULL,
    `description`     longtext,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_Reference_1` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
    CONSTRAINT `FK_Reference_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `blog_id`     int(11) DEFAULT NULL,
    `tag_id`      int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_Reference_4` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
    CONSTRAINT `FK_Reference_5` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 70
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `nickname`         varchar(255) DEFAULT NULL,
    `email`             varchar(255) DEFAULT NULL,
    `content`           varchar(255) DEFAULT NULL,
    `avatar`            varchar(255) DEFAULT NULL,
    `create_time`       datetime     DEFAULT NULL,
    `blog_id`           int(11)      DEFAULT NULL,
    `parent_comment_id` int(11)      DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_Reference_3` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = utf8;
