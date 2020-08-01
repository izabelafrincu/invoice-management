insert into users (id, CNP, name, IBAN) values (1, '6200408469076', 'Vasile Ioana', 'RO85RZBR1224457571422363');
insert into users (id, CNP, name, IBAN, wallet_id) values (2, '7081217355211', 'Popescu Ion','RO95RZBR5774711786896878','7252fe54-540a-4072-95a9-5d9d3669c718');
insert into users (id, CNP, name, IBAN, wallet_id) values (3, '1990609287704', 'Marinescu Denis', 'RO53RZBR7951943852793567', '93a30f57-d63e-4c0c-af64-0a2327af51b3');
insert into users (id, CNP, name, IBAN) values (4, '2990209305050', 'Anghelescu Maria', 'RO44PORL4484427743875623');

insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (1, 'Transaction 1', 57890.677, 'IBAN_TO_IBAN', 4, 1);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (2, 'Transaction 2', 200, 'IBAN_TO_WALLET', 4, 2);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (3, 'Transaction 3',150, 'WALLET_TO_IBAN', 4, 1);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (4, 'Transaction 4',330, 'WALLET_TO_WALLET', 4, 3);

insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (5, 'Transaction 1',444, 'IBAN_TO_IBAN', 2, 1);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (6, 'Transaction 2',333, 'IBAN_TO_WALLET', 2, 3);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (7, 'Transaction 3',667, 'WALLET_TO_IBAN', 2, 3);
insert into transactions (id, description, amount, transaction_type, payer_id, payee_id) values (8, 'Transaction 4',890, 'WALLET_TO_WALLET', 2, 3);

commit;