use blog;

CREATE TABLE `user` (
  `seq` int primary key auto_increment,
  `id` varchar(50),
  `password` varchar(50),
  `email` varchar(100),
  `nickname` varchar(50),
  `tel` varchar(20),
  `profile_img_url` varchar(256),
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
	`seq` int primary key auto_increment,
    `blog_name` varchar(100),
    `seq_user` int not null,
    `blog_detail` varchar(100),
    FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `blog_tag`(
	`seq` int primary key auto_increment,
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
    `content` text not null,
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`seq_blog`) REFERENCES `blog` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `project`(
    `seq` int primary key auto_increment,
    `seq_blog` int not null,
    `title` varchar(512) not null,
    `content` text not null,
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`seq_blog`) REFERENCES `blog` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `portfolio`(
    `seq` int primary key auto_increment,
    `seq_blog` int not null,
    `title` varchar(512) not null,
    `content` text not null,
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`seq_blog`) REFERENCES `blog` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comment_project`(
	`seq` int primary key auto_increment,
    `seq_project` int not null,
    `seq_user` int not null,
    `content` text,
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`seq_project`) REFERENCES `project` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comment_post`(
	`seq` int primary key auto_increment,
    `seq_post` int not null,
    `seq_user` int not null,
    `content` text,
    `regtime` datetime DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`seq_post`) REFERENCES `post` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`seq_user`) REFERENCES `user` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;