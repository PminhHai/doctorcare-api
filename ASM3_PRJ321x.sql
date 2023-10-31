create database if not exists doctorcare;
use doctorcare;
drop database doctorcare; 
 
create table if not exists specializations (
	id int auto_increment primary key,
    `name` varchar(255),
    `description` text);

select * from specializations;
    
insert into specializations (name, description)
values ("Cơ xương khớp", "Chuyên chữa cơ xương khớp"),
	   ("Tiêu hóa", "Chuyên chữa tiêu hóa"),
	   ("Tim mạch", "Chuyên chữa tim mạch"),
	   ("Cột sống", "Chuyên chữa cột sống"),
	   ("Y học cổ truyền", "Chuyên chữa với thảo dược"),
	   ("Châm cứu", "Xua tan mệt mỏi với châm cứu"),
	   ("Sản phụ khoa", "Chăm sóc sức khỏe sản phụ"),
	   ("Siêu âm thai", "Chăm sóc sớm sức khỏe cho trẻ"),
	   ("Nhi khoa", "Để trẻ luôn khỏe"),
	   ("Da liễu", "Chuyên chữa da liễu"),
	   ("Hô hấp", "Chuyên chữa hô hấp"),
	   ("Chuyên khoa mắt", "Chuyên chữa mắt"),
	   ("Nha khoa", "Chuyên chữa răng"),
	   ("Phục hồi chức năng", "Vật lý trị liệu và phục hồi chức năng");
       
select * from specializations;       
    
create table if not exists clinics (
	id int auto_increment primary key,
    `name` varchar(255),
    address varchar(255),
    phone varchar(255),
    work_time text);
    
select * from clinics; 
    
insert into clinics (name, address, phone, work_time) 
	values ("Phòng khám Cơ xương khớp Bảo Ngọc", "Số 73 ngõ 109 Hoàng Ngân - Thanh Xuân - Hà Nội", 
			"0321645798", "17:30-19:00"),
            ("Hệ thống Y tế Thu Cúc cơ sở Thụy Khuê", "286 Thụy Khuê, quận Tây Hồ, Hà Nội", 
			"0213245651", "8:00-11:00, 13:30-16:00 thứ 2 tới thứ 7"),
            ("Phòng khám Đa khoa Mediplus", "99 phố Tân Mai, Tân Mai, Hoàng Mai, Hà Nội", 
			"0321354552", "13:30-17:00"),
            ("Phòng khám Đa khoa SIM Medical Center", "241đường Hòa Bình, P. Hiệp Tân, Q.Tân Phú, TP. HCM", 
			"0324512457", "13:30-17:00 thứ 2, 4, 6"),
            ("Phòng khám Đa khoa Việt Mỹ", "Hoàng Mai - Hà Nội", 
			"0354512478", "18:30-21:00"),
            ("Phòng Khám Đa Khoa MSC Clinic", "204 Nguyễn Tuân, phường Nhân Chính, quận Thanh Xuân, TP Hà Nội", 
			"0324521451", "17:30-19:00"),
            ("Phòng khám chuyên khoa phụ sản - Siêu âm Dr Chường", "16 Hoàng Ngân, Trung Hoà, Cầu Giấy, Hà Nội", 
			"0231514512", "8:00-17:30");    
            
create table clinic_specialties(
	id int not null auto_increment primary key,
    clinic_id int not null,
    specialization_id int not null,
    foreign key(clinic_id) references clinics(id),
    foreign key(specialization_id) references specializations(id)
);            

insert into clinic_specialties(clinic_id, specialization_id)
	values  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 10),
			(2, 1), (2, 3), (2, 5), (2, 6), (2, 8), (2, 10),
            (3, 1), (3, 5), (3, 7), (3, 9), (3, 11), (3, 12),
            (4, 3), (4, 5), (4, 8), (4, 12), (4, 13), (4, 14),
            (5, 1), (5, 6), (5, 11), (5, 12), (5, 13), (5, 14),
            (6, 4), (6, 5), (6, 8), (6, 10), (6, 11), (6, 13),
            (5, 2), (5, 6), (5, 7), (5, 9), (5, 13), (5, 14), (7,7),(7,8);
				
create table if not exists users (
	id int auto_increment primary key,
    email varchar(255),
    `password` varchar(255),
    `role` varchar(255) DEFAULT NULL,
    `name` varchar(255),
    address varchar(255),
    phone varchar(255),
    avatar varchar(255),
    gender varchar(255),
    `description` text,
    is_not_blocked tinyint not null default 1,
    created_at datetime default now(),
    token varchar(255),
    `token_creation_date` timestamp);
    
 drop table users;
 
 insert into users (email, password, role, name, address, phone, avatar, gender, `description`, is_not_blocked, created_at, token, token_creation_date) values 
 ('doctor1@gmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_DOCTOR', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('123@gmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_DOCTOR', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('patient1@gmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_USER', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('admin@gmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_ADMIN', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('doctor3@yopmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_DOCTOR', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('doctor4@yopmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_DOCTOR', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('patient2@yopmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_USER', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('patient233@yopmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_USER', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('patient234@yopmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_USER', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('admin2@gmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_ADMIN', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null),
 ('doctor5@gmail.com', '$2a$10$COF3sNjL34zBAghhdpTN8erjmxbmMlUnVH5wLCAwq/KM1SXZCQ6xy', 'ROLE_DOCTOR', 'Hobie Dranfield', '496 Golf Course Way', '774-863-8702', 'http://dummyimage.com/171x100.png/ff4444/ffffff', 'Male', 'Goldenrod', '1', now(), null, null);
 select * from users;
 
 create table if not exists doctors (
	id int auto_increment primary key,
    user_id int not null,
    clinic_id int not null,
    specialization_id int not null,
    constraint fk_user_doctor foreign key(`user_id`) references `users`(id),
    constraint fk_clinic_doctor foreign key(`clinic_id`) references `clinics`(id),
    constraint fk_specialization_doctor foreign key(`specialization_id`) references `specializations`(id));
    
insert into doctors (user_id, clinic_id, specialization_id) values 
('1', '1', '1'),
('2', '2', '5'),    
('5', '3', '7'),
('6', '6', '10'),
('11','4','13');

select * from doctors;
    
 create table if not exists schedules (
	id int auto_increment primary key,
    doctor_id int not null,
    user_id int not null,
    clinic_id int not null,
    specialization_id int not null,
    `date` date not null,
    `time` varchar(255) not null,
	created_at datetime default now(),
	doctor_confirm int not null,
    reason_cancel varchar(255) ,
    constraint fk_schedule_patient foreign key(`user_id`) references `users`(id),
    constraint fk_schedule_doctor foreign key(`doctor_id`) references `doctors`(id),
	constraint fk_schedule_clinic foreign key(`clinic_id`) references `clinics`(id),
    constraint fk_schedule_specialization foreign key(`specialization_id`) references `specializations`(id));
    
select * from schedules;    

insert into schedules (doctor_id,user_id,clinic_id,specialization_id,created_at,`date`,`time`,doctor_confirm) values
('1','3','1','1',now(),now(),"8:00",'0'),
('2','3','2','5',now(),now(),"9:00",'0'),
('2','7','2','5',now(),now(),"9:00",'0'),
('3','7','3','7',now(),now(),"9:00",'0'),
('4','8','6','10',now(),now(),"9:00",'0'),
('1','8','1','1',now(),now(),"9:00",'0'),
('5','9','4','13',now(),now(),"9:00",'0'),
('5','9','4','13',now(),now(),"9:00",'0'),
('4','7','6','10',now(),now(),"9:00",'0'),
('3','9','3','7',now(),now(),"10:00",'0'),
('1','8','1','1',now(),now(),"10:00",'0'),
('5','3','4','13',now(),now(),"10:00",'0');

update schedules set doctor_confirm = '1' where id > 0;

create table if not exists health_results(
	id int not null auto_increment primary key,
    pathology varchar(255) not null,
    `pathological_description` text not null,
    pill varchar(255),
    `feed_back` varchar(255),
	doctor_id int not null,
    schedules_id int not null,
    foreign key(schedules_id) references schedules(id),
    foreign key(doctor_id) references doctors(id)
);

insert into health_results (pathology,`pathological_description`,pill,`feed_back`,doctor_id,schedules_id)
values
('abc','abc','200.000','abc',1,1),
('abc','abc','200.000','abc',2,2),
('abc','abc','200.000','abc',2,3),
('abc','abc','200.000','abc',3,4),
('abc','abc','200.000','abc',4,5),
('abc','abc','200.000','abc',1,6),
('abc','abc','200.000','abc',5,7),
('abc','abc','200.000','abc',5,8),
('abc','abc','200.000','abc',4,9),
('abc','abc','200.000','abc',3,10),
('abc','abc','200.000','abc',1,11),
('abc','abc','200.000','abc',5,12);

SELECT sp.id, sp.name, sp.description, COUNT(*) AS count
FROM specializations sp
JOIN schedules sc ON sp.id = sc.specialization_id
GROUP BY sp.id, sp.name, sp.description
ORDER BY count DESC
LIMIT 4;

SELECT c.id, c.name, c.address, c.phone, c.work_time, COUNT(*) AS count
FROM clinics c
JOIN schedules sc ON c.id = sc.clinic_id
GROUP BY c.id, c.name, c.address, c.phone, c.work_time
ORDER BY count DESC
LIMIT 4;

select * from schedules where user_id = 3;

SELECT DISTINCT c.* 
FROM clinics c INNER JOIN clinic_specialties cs ON c.id = cs.clinic_id 
INNER JOIN specializations s ON cs.specialization_id = s.id
WHERE c.address LIKE '%a%' 
or c.name LIKE '%a%'
or (s.name LIKE '%a%'
OR s.description LIKE '%a%');

SELECT distinct c.*
FROM clinics c
JOIN clinic_specialties cs ON c.id = cs.clinic_id
JOIN specializations s ON cs.specialization_id = s.id
WHERE s.name like '%Khoa%';

SELECT * from doctors d inner join users u on d.user_id = u.id  where `name` like '%a%';

SELECT * from doctors d inner join users u on d.user_id = u.id where u.id = 2;

select u.* from users u 
join schedules s on u.id = s.user_id where s.doctor_id = '1' and s.doctor_confirm = '1';

SELECT * FROM schedules s WHERE s.doctor_id = 1;

SELECT * FROM schedules s WHERE s.user_id = 3;

SELECT * FROM schedules s WHERE s.id = 1 AND s.user_id = 5;
