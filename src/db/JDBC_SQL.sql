select * from tab;

create table dodge_rank(
	dname	varchar2(40)	constraint dodge_rank_dname_nn	not null,
	ddate		Date				default sysdate,
	drecord	number(4)		constraint dodge_rank_drecord_nn	not null
);

drop table dodge_rank purge;

select * from dodge_rank;

insert into dodge_rank
values('하두세네다여일여아열하둘셋', sysdate, 47);
-- 한글 13단어.

delete from dodge_rank;



insert into dodge_rank
values('익명', sysdate, 12);

insert into dodge_rank
values('aaa', sysdate, 5);

insert into dodge_rank
values('bbb', sysdate, 7);

insert into dodge_rank
values('익명', sysdate, 3);

insert into dodge_rank
values('익명', sysdate, 3);

insert into dodge_rank
values('익명', sysdate, 27);

insert into dodge_rank
values('익명', sysdate, 8);

insert into dodge_rank
values('익명', sysdate, 1);