insert into casopis (id, clanarina, ime, issn, aktivan, glavni_urednik) values (1, 1, 'casopis1', 1111, 1, 'urednik');
insert into casopis (id, clanarina, ime, issn, aktivan, glavni_urednik) values (2, 1, 'casopis2', 2222, 1, 'urednik2');
insert into casopis (id, clanarina, ime, issn, aktivan, glavni_urednik) values (3, 0, 'casopis3', 3333, 1, 'urednik');

insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('demo', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'demo', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'demo', 'demo', 'ADMIN');

insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('urednik', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'urednik', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'urednik', 'urednik', 'UREDNIK' );
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('urednik2', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'urednik2', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'urednik2', 'urednik2', 'UREDNIK');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('urednikBiolog', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'urednikBiolog', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'urednikBiolog', 'urednikBiolog', 'UREDNIK');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('urednikFizicar', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'urednikFizicar', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'urednikFizicar', 'urednikFizicar', 'UREDNIK');


insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentMatematika1', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentMatematika1', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentMatematika1', 'recenzentMatematika1', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentMatematika2', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentMatematika2', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentMatematika2', 'recenzentMatematika2', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentMatematika3', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentMatematika3', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentMatematika3', 'recenzentMatematika3', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentFizika1', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentFizika1', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentFizika1', 'recenzentFizika1', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentFizika2', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentFizika2', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentFizika2', 'recenzentFizika2', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentFizika3', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentFizika3', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentFizika3', 'recenzentFizika3', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentBiologija1', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentBiologija1', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentBiologija1', 'recenzentBiologija1', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentBiologija2', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentBiologija2', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentBiologija2', 'recenzentBiologija2', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentBiologija3', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentBiologija3', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentBiologija3', 'recenzentBiologija3', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentMedicina1', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentMedicina1', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentMedicina1', 'recenzentMedicina1', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentMedicina2', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentMedicina2', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentMedicina2', 'recenzentMedicina2', 'RECENZENT');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('recenzentMedicina3', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'recenzentMedicina3', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'recenzentMedicina3', 'recenzentMedicina3', 'RECENZENT');

insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('autor1', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'autor1', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'autor1', 'autor1', 'AUTOR');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('autor2', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'autor2', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'autor2', 'autor2', 'AUTOR');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('autor3', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'autor3', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'autor3', 'autor3', 'AUTOR');

insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('korisnik1', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'korisnik1', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'korisnik1', 'korisnik1', 'Korisnik');
insert into korisnik (username, drzava, email, grad, ime, password, prezime, titula, type) values ('korisnik2', 'srbija', 'nmalencic@gmail.com', 'novi sad', 'korisnik2', '$2a$10$7Zx6Aj43YbmbpNMlLyItfeHAJcMCkEU3rHuoLTt9jsfVu4yOOYo/e', 'korisnik2', 'korisnik2', 'Korisnik');

insert into naucna_oblast values (1, 'Matematika');
insert into naucna_oblast values (2, 'Fizika');
insert into naucna_oblast values (3, 'Biologija');
insert into naucna_oblast values (4, 'Medicina');

insert into role values (1, 'ROLE_KORISNIK');
insert into role values (2, 'ROLE_AUTOR');
insert into role values (3, 'ROLE_RECENZENT');
insert into role values (4, 'ROLE_UREDNIK');
insert into role values (5, 'ROLE_ADMIN');

insert into privilege values (1, 'SET_KORISNIK_TASK');
insert into privilege values (2, 'SET_AUTOR_TASK');
insert into privilege values (3, 'SET_RECENZENT_TASK');
insert into privilege values (4, 'SET_UREDNIK_TASK');
insert into privilege values (5, 'SET_ADMIN_TASK');

insert into roles_privileges values (1, 1);
insert into roles_privileges values (2, 1);
insert into roles_privileges values (2, 2);
insert into roles_privileges values (3, 1);
insert into roles_privileges values (3, 2);
insert into roles_privileges values (3, 3);
insert into roles_privileges values (4, 1);
insert into roles_privileges values (4, 2);
insert into roles_privileges values (4, 3);
insert into roles_privileges values (4, 4);
insert into roles_privileges values (5, 1);
insert into roles_privileges values (5, 2);
insert into roles_privileges values (5, 3);
insert into roles_privileges values (5, 4);
insert into roles_privileges values (5, 5);

insert into user_roles values ('demo', 5);

insert into user_roles values ('urednik', 4);
insert into user_roles values ('urednik2', 4);
insert into user_roles values ('urednikBiolog', 4);
insert into user_roles values ('urednikFizicar', 4);


insert into user_roles values ('recenzentMatematika1', 3);
insert into user_roles values ('recenzentMatematika2', 3);
insert into user_roles values ('recenzentMatematika3', 3);
insert into user_roles values ('recenzentFizika1', 3);
insert into user_roles values ('recenzentFizika2', 3);
insert into user_roles values ('recenzentFizika3', 3);
insert into user_roles values ('recenzentBiologija1', 3);
insert into user_roles values ('recenzentBiologija2', 3);
insert into user_roles values ('recenzentBiologija3', 3);
insert into user_roles values ('recenzentMedicina1', 3);
insert into user_roles values ('recenzentMedicina2', 3);
insert into user_roles values ('recenzentMedicina3', 3);

insert into user_roles values ('autor1', 2);
insert into user_roles values ('autor2', 2);
insert into user_roles values ('autor3', 2);

insert into user_roles values ('korisnik1', 1);
insert into user_roles values ('korisnik2', 1);

insert into casopis_autori_platili values (1, 'autor1');

insert into nacin_placanja values (1, 'BANK');
insert into nacin_placanja values (2, 'PAYPAL');
insert into nacin_placanja values (3, 'BITCOIN');

insert into casopis_nacini_placanja values (1, 1);
insert into casopis_nacini_placanja values (1, 2);
insert into casopis_nacini_placanja values (2, 1);
insert into casopis_nacini_placanja values (2, 3);
insert into casopis_nacini_placanja values (3, 2);
insert into casopis_nacini_placanja values (3, 3);

insert into casopis_recenzenti values (1, 'recenzentMatematika1');
insert into casopis_recenzenti values (1, 'recenzentMatematika3');
insert into casopis_recenzenti values (1, 'recenzentFizika2');
insert into casopis_recenzenti values (1, 'recenzentFizika3');
insert into casopis_recenzenti values (2, 'recenzentMedicina1');
insert into casopis_recenzenti values (2, 'recenzentMedicina2');
insert into casopis_recenzenti values (3, 'recenzentFizika1');
insert into casopis_recenzenti values (3, 'recenzentFizika2');
insert into casopis_recenzenti values (3, 'recenzentBiologija2');
insert into casopis_recenzenti values (3, 'recenzentBiologija1');

insert into casopis_urednici values (1, 'urednikBiolog');
insert into casopis_urednici values (1, 'urednikFizicar');
insert into casopis_urednici values (2, 'urednikBiolog');
insert into casopis_urednici values (3, 'urednikFizicar');

insert into korisnik_nobl values ('recenzentMatematika1', 1);
insert into korisnik_nobl values ('recenzentMatematika2', 1);
insert into korisnik_nobl values ('recenzentMatematika3', 1);
insert into korisnik_nobl values ('recenzentFizika1', 2);
insert into korisnik_nobl values ('recenzentFizika2', 2);
insert into korisnik_nobl values ('recenzentFizika3', 2);
insert into korisnik_nobl values ('recenzentBiologija1', 3);
insert into korisnik_nobl values ('recenzentBiologija2', 3);
insert into korisnik_nobl values ('recenzentBiologija3', 3);
insert into korisnik_nobl values ('recenzentMedicina1', 4);
insert into korisnik_nobl values ('recenzentMedicina2', 4);
insert into korisnik_nobl values ('recenzentMedicina3', 4);
insert into korisnik_nobl values ('urednikBiolog', 3);
insert into korisnik_nobl values ('urednikFizicar', 2);



insert into casopis_oblasti values (1, 1);
insert into casopis_oblasti values (1, 2);
insert into casopis_oblasti values (1, 3);
insert into casopis_oblasti values (1, 4);
insert into casopis_oblasti values (2, 3);
insert into casopis_oblasti values (2, 4);
insert into casopis_oblasti values (3, 2);
insert into casopis_oblasti values (3, 1);
















