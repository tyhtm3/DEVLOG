use blog;

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

CREATE TABLE `user_neighbor` (
	`seq` int primary key auto_increment,
    `seq_user` int not null,
    `seq_neighbor` int not null,
    FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`seq_neighbor`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `blog` (
	`seq` int primary key,
    `blog_name` varchar(100),
    `blog_detail` varchar(100),
    FOREIGN KEY (`seq`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `blog_tag`(
	`seq` int primary key,
    `tag` varchar(256),
	FOREIGN KEY (`seq`) REFERENCES `blog` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
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
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`seq_blog`) REFERENCES `blog` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_basic`(
    `seq` int primary key,
    `content` text not null,
    FOREIGN KEY (`seq`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_project`(
    `seq` int primary key,
    `content` text not null,
    FOREIGN KEY (`seq`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post_portfolio`(
    `seq` int primary key,
    `content` text not null,
    FOREIGN KEY (`seq`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
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