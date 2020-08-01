insert into users (id, CNP, name, IBAN) values (1, '6200408469076', 'Vasile Ioana', 'RO85RZBR1224457571422363');
insert into users (id, CNP, name, IBAN, wallet_id) values (2, '7081217355211', 'Popescu Ion','RO95RZBR5774711786896878','7252fe54-540a-4072-95a9-5d9d3669c718');
insert into users (id, CNP, name, IBAN, wallet_id) values (3, '1990609287704', 'Marinescu Denis', 'RO53RZBR7951943852793567', '93a30f57-d63e-4c0c-af64-0a2327af51b3');
insert into users (id, CNP, name, IBAN) values (4, '2990209305050', 'Anghelescu Maria', 'RO44PORL4484427743875623');

insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (1, 'Transaction details 1', 57890.677, 0, 4, 1);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (2, 'Transaction details 2', 200, 1, 4, 2);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (3, 'Transaction details 3',150, 2, 4, 1);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (4, 'Transaction details 4',330, 3, 4, 3);

insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (5, 'Transaction details 1',444, 0, 2, 1);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (6, 'Transaction details 2',344,0, 2, 3);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (7, 'Transaction details 3',555,0, 2, 4);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (8, 'Transaction details 4',333,1, 2, 3);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (9, 'Transaction details 5',667,2, 2, 3);

commit;