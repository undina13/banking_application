insert into client(client_id,last_name,first_name,middle_name,birth_date,email,passport)
values (11,'Petrov','Petr','Petrovich','2000-08-13','petrov@mail.ru','{"number":"123456", "series":"1234"}');

insert into application(application_id,client_id,status,creation_date,applied_offer,status_history)
values (11,11,'PREAPPROVAL','2023-08-09',null,'[{"time": [2023, 8, 9, 18, 12, 23, 312592400], "status": "PREAPPROVAL", "changeType": "AUTOMATIC"}]');