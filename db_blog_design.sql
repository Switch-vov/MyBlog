USE db_blog;
SHOW VARIABLES LIKE "char%";
SET character_set_server=utf8;

# Table structure for table user
CREATE TABLE user
(
	userId	 INT(11) UNSIGNED PRIMARY KEY auto_increment, # 用户ID
  userName varchar(20) NOT NULL UNIQUE, # 用户名
  password varchar(50) NOT NULL, #密码
  nickName varchar(20) DEFAULT '', #昵称
  question varchar(50) NOT NULL, #密码保护问题
  answer   varchar(50) NOT NULL #密码保护回答
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';
ALTER TABLE user AUTO_INCREMENT = 10000;

# 密码采用MD5加密，密码明文为123456
INSERT INTO user VALUES('','Switch','e10adc3949ba59abbe56e057f20f883e','Switch','你的家乡','湖南怀化市');
INSERT INTO user VALUES('','Switch2','e10adc3949ba59abbe56e057f20f883e','Switch2','你的家乡','湖南怀化市');
INSERT INTO user VALUES('','Switch3','e10adc3949ba59abbe56e057f20f883e','Switch3','你的家乡','湖南怀化市');
INSERT INTO user VALUES('','hulianwang','e10adc3949ba59abbe56e057f20f883e','互联网','你的家乡','湖南怀化市');


SELECT * FROM user;




# Table structure for table bloginfo
CREATE TABLE bloginfo (
	blogId INT(11) UNSIGNED PRIMARY KEY auto_increment, # 个性设置ID
	userId	 INT(11) UNSIGNED UNIQUE NOT NULL, #用户ID
	blogtitle varchar(50) default NULL, #博客标题
	idiograph varchar(50) default NULL #个性签名
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='个性设置表';
ALTER TABLE bloginfo AUTO_INCREMENT= 10000;

#  Foreign keys for table bloginfo
ALTER TABLE bloginfo ADD CONSTRAINT bloginfo_fk_1 FOREIGN KEY (userId) REFERENCES user(userId);

INSERT INTO `bloginfo` VALUES ('','10000','Switch的博客','分享并记录所学所见');
INSERT INTO `bloginfo` VALUES ('','10001','Switch1的博客','分享并记录所学所见');
INSERT INTO `bloginfo` VALUES ('','10002','Switch2的博客','分享并记录所学所见');

SELECT * FROM bloginfo;




# Table structure for table article
CREATE TABLE article (
	articleId int(11) UNSIGNED PRIMARY KEY auto_increment, #文章ID
	userId	 INT(11) UNSIGNED NOT NULL, #用户ID
  title varchar(50) NOT NULL, #文章标题
  content text NOT NULL, #文章内容
  date datetime default NOW(), #发表时间
  hasread int(11) default '0' #评论数
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='文章表';
ALTER TABLE article AUTO_INCREMENT= 10000;

#  Foreign keys for table article
ALTER TABLE article ADD CONSTRAINT article_fk_1 FOREIGN KEY (userId) REFERENCES user (userId);


INSERT INTO article VALUES ('',10000,'SSH整合方案（一）','<p>这段时间准备写一下SSH的整合方法</p>',CURRENT_TIMESTAMP,2);
INSERT INTO article VALUES ('',10000,'SSH整合方案（二）','<p>让我们一起感受SSH整合的魅力吧！！！！！</p>',CURRENT_TIMESTAMP,1);
INSERT INTO article VALUES ('',10001,'今天好冷啊！','<p>外面下雪了，今年的冬天来得太早了。</p>',CURRENT_TIMESTAMP,1);
INSERT INTO article VALUES ('',10002,'继续更新我的SSH整合方案！','',CURRENT_TIMESTAMP,1);
INSERT INTO article VALUES ('',10001,'测试日志！！','<p>测试日志！！</p>',CURRENT_TIMESTAMP,1);

SELECT * FROM article;



# Table structure for table critique
CREATE TABLE critique (
  critiqueId int(11) PRIMARY KEY auto_increment, #评论ID
  articleId int(11) UNSIGNED NOT NULL, #所属文章ID
	userId	 INT(11) UNSIGNED NOT NULL, #评论用户ID
  content text NOT NULL #评论内容
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';
ALTER TABLE critique AUTO_INCREMENT=10000;

#  Foreign keys for table critique
ALTER TABLE critique ADD CONSTRAINT critique_fk_1 FOREIGN KEY (articleId) REFERENCES article(articleId);
ALTER TABLE critique ADD CONSTRAINT critique_fk_2 FOREIGN KEY (userId) REFERENCES user(userId);


INSERT INTO critique VALUES ('',10000,10002,'<p>是啊，太冷了，我还好！！！</p>');
INSERT INTO critique VALUES ('',10000,10002,'<p>我们公司的暖气坏了，好冷啊！！</p>');
INSERT INTO critique VALUES ('',10001,10001,'<p>测试一下！</p>');
INSERT INTO critique VALUES ('',10002,10000,'<p>评论！！</p>');
INSERT INTO critique VALUES ('',10003,10002,'<p>测试一下！！</p>');
INSERT INTO critique VALUES ('',10004,10000,'<p>测试一下！！！！</p>');

SELECT * FROM critique;




# Table structure for table click

CREATE TABLE click(
  clickId int(11) UNSIGNED PRIMARY KEY auto_increment, #点击ID
  articleId int(11) UNSIGNED NOT NULL, #所属文章ID
  ip varchar(255) default NULL, #点击者IP
  date datetime default NOW() #点击时间
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点击量表';
ALTER TABLE click AUTO_INCREMENT = 10000;

#  Foreign keys for table click
ALTER TABLE click ADD CONSTRAINT `click_fk_1` FOREIGN KEY (articleId) REFERENCES article (articleId);


INSERT INTO click VALUES ('',10000,'127.0.0.1',CURRENT_TIMESTAMP);
INSERT INTO click VALUES ('',10001,'127.0.0.1',CURRENT_TIMESTAMP);
INSERT INTO click VALUES ('',10001,'127.0.0.1',CURRENT_TIMESTAMP);
INSERT INTO click VALUES ('',10002,'127.0.0.1',CURRENT_TIMESTAMP);
INSERT INTO click VALUES ('',10003,'127.0.0.1',CURRENT_TIMESTAMP);
INSERT INTO click VALUES ('',10004,'127.0.0.1',CURRENT_TIMESTAMP);
INSERT INTO click VALUES ('',10004,'127.0.0.1',CURRENT_TIMESTAMP);
INSERT INTO click(articleId,ip) VALUES (10004,'127.0.0.1');

SELECT * FROM click;


# 以下语句都不是数据库设计

# 按发表博客的最新时间，将发表过博客的用户都列出来
select userId,userName,password,nickName,question,answer from (select u.*,max(date) maxdate from user u,article a where u.userId = a.userId GROUP BY a.userId ORDER BY userId asc) temp ORDER BY temp.maxdate desc;
# 上面博客用户的总数量
select count(*) from (select u.*,max(date) maxdate from user u,article a where u.userId = a.userId GROUP BY a.userId ORDER BY userId asc) temp ORDER BY temp.maxdate desc;
# 查找10003的最新发表博客时间
SELECT max(date) from article where userId = 10003;

select * from (SELECT userId,max(date) maxdate from article group by userId) a,article WHERE article.date = a.maxdate;

# 通过文章ID找到用户
select u.* from user u,article a where u.userId = a.userId and a.articleId=10150;

