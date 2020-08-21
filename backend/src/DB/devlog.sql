use devlog;

CREATE TABLE `user` (
  `seq` int primary key auto_increment,
  `id` varchar(50) not null,
  `password` varchar(50) not null,
  `email` varchar(100) not null,
  `nickname` varchar(50),
  `name` varchar(50) not null,
  `tel` varchar(20),
  `profile_img_url` varchar(256),
  `github_url` varchar(256),
  `birthday` varchar(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_stack`(
 	`seq` int primary key auto_increment,
    `seq_user` int not null,
    `stack` varchar(256),
    `stack_img_url` varchar(256),
    FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_neighbor` (
	`seq` int primary key auto_increment,
    `seq_user` int not null,
    `seq_neighbor` int not null,
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`seq_neighbor`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stack`(
 	`seq` int primary key auto_increment,
    `stack` varchar(256),
    `stack_img_url` varchar(256)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `blog` (
	`seq` int primary key,
    `blog_name` varchar(100),
    `blog_detail` varchar(100),
    FOREIGN KEY (`seq`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `blog_tag`(
	`seq` int primary key,
    `seq_blog` int not null,
    `tag` varchar(256),
	FOREIGN KEY (`seq_blog`) REFERENCES `blog` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_tag`(
	`seq` int primary key auto_increment,
    `seq_user` int not null,
    `tag` varchar(256),
	FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post`(
    `seq` int primary key auto_increment,
    `seq_blog` int not null,
    `title` varchar(512) not null,
    `img_url` varchar(256),
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    `disclosure` int,
    `like_count` int,
     FOREIGN KEY (`seq_blog`) REFERENCES `blog` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_basic`(
    `seq` int primary key,
    `content` text not null,
    FOREIGN KEY (`seq`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_project`(
    `seq` int primary key,
    `summary` text not null,
    `start_date` date not null,
    `finish_date` date,
    `role` text not null,
    `github_url` varchar(256),
    `etc_url` varchar(256),
    `rep_url` varchar(256),
    `content` text,
    FOREIGN KEY (`seq`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_project_stack`(
 	`seq` int primary key auto_increment,
    `seq_post_project` int not null,
    `stack` varchar(256),
    `stack_img_url` varchar(256),
    FOREIGN KEY (`seq_post_project`) REFERENCES `post_project` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio`(
    `seq` int primary key,
	`name` varchar(50),
	`profile_img_url` varchar(256),
	`github_url` varchar(256),
	`birthday` varchar(20),
    `content` text,
    FOREIGN KEY (`seq`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `post_portfolio_project`(
    `seq` int primary key auto_increment,
    `seq_post_portfolio` int not null,
    `seq_post_project` int not null,
    FOREIGN KEY (`seq_post_portfolio`) REFERENCES `post_portfolio` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`seq_post_project`) REFERENCES `post_project` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_comment`(
	`seq` int primary key auto_increment,
    `seq_post` int not null,
    `seq_user` int not null,
    `content` text,
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`seq_post`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_tag`(
	`seq` int primary key auto_increment,
    `seq_post` int not null,
    `tag` varchar(256),
	FOREIGN KEY (`seq_post`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_like`(
	`seq` int primary key auto_increment,
    `seq_post` int not null,
    `seq_user` int not null,
	FOREIGN KEY (`seq_post`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio_certification`(
    `seq` int primary key auto_increment,
    `seq_post_portfolio` int not null,
    `certification` varchar(256),
    `date` varchar(20),
    FOREIGN KEY (`seq_post_portfolio`) REFERENCES `post_portfolio` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio_skill`(
    `seq` int primary key auto_increment,
    `seq_post_portfolio` int not null,
    `skill` varchar(256),
    `level` varchar(10),
    FOREIGN KEY (`seq_post_portfolio`) REFERENCES `post_portfolio` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio_language`(
    `seq` int primary key auto_increment,
    `seq_post_portfolio` int not null,
    `language` varchar(256),
    `level` varchar(10),
    FOREIGN KEY (`seq_post_portfolio`) REFERENCES `post_portfolio` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio_info`(
    `seq` int primary key auto_increment,
    `seq_post_portfolio` int not null,
    `introduction` text,
    `address` varchar(256),
    FOREIGN KEY (`seq_post_portfolio`) REFERENCES `post_portfolio` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio_experience`(
    `seq` int primary key auto_increment,
    `seq_post_portfolio` int not null,
    `position` varchar(256),
    `company` varchar(64),
    `start` varchar(20),
    `end` varchar(20),
    FOREIGN KEY (`seq_post_portfolio`) REFERENCES `post_portfolio` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio_education`(
    `seq` int primary key auto_increment,
    `seq_post_portfolio` int not null,
    `major` varchar(256),
    `name` varchar(64),
    `start` varchar(20),
    `end` varchar(20),
    FOREIGN KEY (`seq_post_portfolio`) REFERENCES `post_portfolio` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio_social`(
    `seq` int primary key auto_increment,
    `seq_post_portfolio` int not null,
    `link` varchar(256),
    `icon` varchar(64),
    FOREIGN KEY (`seq_post_portfolio`) REFERENCES `post_portfolio` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `certification`(
	`seq` int primary key auto_increment,
    `key` varchar(128),
    `value` varchar(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    