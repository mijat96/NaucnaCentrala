INSERT INTO `user_table` (`id`,`username`,`password`,`drzava`,`email`,`grad`,`hash_code`,`ime`,`prezime`,`titula`,`uloga`) VALUES (1,'admin','admin', 'srb','marko.mijatovic.1996@gmail.com','novi sad','','admin','admin','dipl inz','admin');
INSERT INTO `user_table` (`id`,`username`,`password`,`drzava`,`email`,`grad`,`hash_code`,`ime`,`prezime`,`titula`,`uloga`) VALUES (2,'urednik','urednik', 'srb','marko.mijatovic.1996@gmail.com','novi sad','','urednik','urednik','dipl inz','urednik');
INSERT INTO `user_table` (`id`,`username`,`password`,`drzava`,`email`,`grad`,`hash_code`,`ime`,`prezime`,`titula`,`uloga`) VALUES (3,'urednik1','urednik1', 'srb','marko.mijatovic.1996@gmail.com','novi sad','','urednik1','urednik1','dipl inz','urednik');
INSERT INTO `user_table` (`id`,`username`,`password`,`drzava`,`email`,`grad`,`hash_code`,`ime`,`prezime`,`titula`,`uloga`) VALUES (4,'urednik2','urednik2', 'srb','marko.mijatovic.1996@gmail.com','novi sad','','urednik2','urednik2','dipl inz','urednik');
INSERT INTO `user_table` (`id`,`username`,`password`,`drzava`,`email`,`grad`,`hash_code`,`ime`,`prezime`,`titula`,`uloga`) VALUES (5,'recenzent1','recenzent1', 'srb','marko.mijatovic.1996@gmail.com','novi sad','','recenzent1','recenzent1','dipl inz','recenzent');
INSERT INTO `user_table` (`id`,`username`,`password`,`drzava`,`email`,`grad`,`hash_code`,`ime`,`prezime`,`titula`,`uloga`) VALUES (6,'recenzent2','recenzent2', 'srb','marko.mijatovic.1996@gmail.com','novi sad','','recenzent2','recenzent2','dipl inz','recenzent');

INSERT INTO `magazine_table` (`id`,`naziv`,`issn`,`naucne_oblasti`,`nacin_placanja`,`glavni_urednik`,`aktivan`,`recenzent1`,`recenzent2`,`urednik1`, `urednik2`) VALUES (1,'casopis1', 123, 'biologija','korisnik', 'urednik1', 'da','recenzent1','recenzent2','urednik1', 'urednik2');
INSERT INTO `magazine_table` (`id`,`naziv`,`issn`,`naucne_oblasti`,`nacin_placanja`,`glavni_urednik`,`aktivan`,`recenzent1`,`recenzent2`,`urednik1`, `urednik2`) VALUES (2,'casopis2', 231, 'biologija','korisnik', 'urednik1', 'da','recenzent1','recenzent2','urednik1', 'urednik2');
INSERT INTO `magazine_table` (`id`,`naziv`,`issn`,`naucne_oblasti`,`nacin_placanja`,`glavni_urednik`,`aktivan`,`recenzent1`,`recenzent2`,`urednik1`, `urednik2`) VALUES (3,'casopis3', 312, 'hemija','korisnik', 'urednik1', 'da','recenzent1','recenzent2','urednik1', 'urednik2');