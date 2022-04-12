-- Before running this script, running the following commands:
-- create user 'admin'@'localhost' identified by '123456';
--
-- grant all privileges on billshare.* to 'admin'@'localhost';
--
-- flush privileges;

create database if not exists BillShare;

use BillShare;

DROP TABLE IF EXISTS indebt;
DROP TABLE IF EXISTS own;
DROP TABLE IF EXISTS bill;
DROP TABLE IF EXISTS BillShare.user;


create table user (
                      uid             Integer,
                      lastname        varchar(30) not null,
                      firstname       varchar(30) not null,
                      nickname        varchar(30),
                      password        varchar(60) not null,
                      email           varchar(30) not null,
                      avatar          text,
                      tel             long,
                      primary key (uid)
);

create table bill (
                      bid             Integer,
                      oid             Integer not null,
                      amount          Integer not null,
                      receipt         text,
                      status          Integer not null,
                      create_time     timestamp,
                      finish_time     timestamp,
                      type            varchar(30),
                      comment         text,
                      primary key (bid),
                      foreign key (oid) references user(uid) on delete cascade
);

create table own (
                     uid             Integer,
                     bid             Integer,
                     primary key(bid),
                     foreign key (uid) references user(uid) on delete cascade,
                     foreign key (bid) references bill(bid) on delete cascade
);

create table indebt (
                        did             Integer,
                        bid             Integer,
                        status          Integer not null,
                        accept_time     timestamp,
                        pay_time        timestamp,
                        amount          Integer not null,
                        primary key(did, bid),
                        foreign key (did) references user(uid) on delete cascade,
                        foreign key (bid) references bill(bid) on delete cascade
);
