-- auto-generated definition
create table info
(
    inid       int                                 not null comment '信息主键'
        primary key,
    `key`      varchar(64)                         not null comment '键',
    value      text                                null comment '值',
    created_at timestamp default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '程序信息表';

-- auto-generated definition
create table na_user
(
    uuid       varchar(36)                         not null comment '用户主键'
        primary key,
    username   varchar(36)                         not null comment '用户名',
    email      varchar(254)                        not null comment '邮箱',
    password   char(60)                            not null comment '密码',
    created_at timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    constraint na_user_email_uindex
        unique (email),
    constraint na_user_username_uindex
        unique (username)
)
    comment '用户表';

-- auto-generated definition
create table na_article
(
    aid        varchar(36)                         not null comment '文章主键'
        primary key,
    auid       varchar(36)                         not null comment '作者id',
    title      varchar(30)                         not null comment '标题',
    tags       json                                null comment '文章标签',
    `desc`     text                                not null comment '内容',
    created_at timestamp default CURRENT_TIMESTAMP not null comment '发布时间',
    updated_at timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
    constraint na_article_na_user_uuid_fk
        foreign key (auid) references na_user (uuid)
            on update cascade on delete cascade
)
    comment '文章';

-- auto-generated definition
create table na_comment
(
    cin        varchar(36)                         not null comment '评论主键'
        primary key,
    cname      varchar(36)                         not null comment '评论者昵称',
    cdesc      varchar(256)                        not null comment '评论内容',
    created_at timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    reply      varchar(256)                        null comment '回复',
    constraint na_comment_na_comment_cin_fk
        foreign key (reply) references na_comment (cin)
)
    comment '评论';

-- auto-generated definition
create table na_tag
(
    tid   int auto_increment comment '标签主键'
        primary key,
    tname varchar(36) not null comment '标签名称',
    constraint na_tag_tname_uindex
        unique (tname)
)
    comment '标签';

-- auto-generated definition
create table na_token
(
    token_uuid varchar(36)                         not null comment '令牌主键'
        primary key,
    user_uuid  varchar(36)                         not null comment '用户id',
    created_at timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    expired_at timestamp                           not null comment '过期时间',
    constraint na_token_na_user_uuid_fk
        foreign key (user_uuid) references na_user (uuid)
            on update cascade on delete cascade
)
    comment '令牌表';
