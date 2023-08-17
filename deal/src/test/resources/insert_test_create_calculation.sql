insert into client(client_id,last_name,first_name,middle_name,birth_date,email,passport)
values (12,'Sidorov','Sidor','Petrovich','2005-08-09','sidorov@mail.ru','{"number":"623456", "series":"9999"}');

insert into application(application_id,client_id,status,creation_date,applied_offer,status_history)
values (12,12,'APPROVED','2023-08-09','{"rate": 8, "term": 12, "totalAmount": 10120.0, "applicationId": 12, "isSalaryClient": true, "monthlyPayment": 880.32, "requestedAmount": 10000, "isInsuranceEnabled": true}','[{"time": [2023, 8, 9, 18, 12, 23, 312592400], "status": "PREAPPROVAL", "changeType": "AUTOMATIC"}, {"time": [2023, 8, 9, 18, 31, 51, 444076900], "status": "APPROVED", "changeType": "AUTOMATIC"}]');