CREATE DATABASE demo;
USE demo;

create table departman (
	id  int(3) NOT NULL AUTO_INCREMENT,
	ad varchar(120) NOT NULL,
	PRIMARY KEY (id)
);

create table personel (
	id  int(3) NOT NULL AUTO_INCREMENT,
	ad varchar(120) NOT NULL,
	soyad varchar(120) NOT NULL,
	email varchar(220) NOT NULL,
	telefon varchar(220) NOT NULL,
	sifre varchar(220) NOT NULL,
	cinsiyet int NOT NULL,
	isManager int NOT NULL,
	departman_id int NOT NULL,
	dogum_tarihi date,
	ise_baslama_tarihi date,
	PRIMARY KEY (id),
    FOREIGN KEY (departman_id) REFERENCES departman(id)
);

create table proje (
	id  int(3) NOT NULL AUTO_INCREMENT,
	ad varchar(120) NOT NULL,
	baslama_tarihi date NOT NULL,
	bitis_tarihi date NOT NULL,
	yonetici_kullanici_id int NOT NULL,
	butce bigint NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (yonetici_kullanici_id) REFERENCES personel(id)
);

create table proje_personel (
	id  int(3) NOT NULL AUTO_INCREMENT,
	proje_id int NOT NULL,
	personel_id int NOT NULL,
	gorev varchar(220) NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (proje_id) REFERENCES proje(id),
    FOREIGN KEY (personel_id) REFERENCES personel(id)
);

create table personel_izin (
	id  int(3) NOT NULL AUTO_INCREMENT,
	baslama_tarihi date NOT NULL,
	bitis_tarihi date NOT NULL,
	personel_id int NOT NULL,
	izin_sebebi varchar(220) NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (personel_id) REFERENCES personel(id)
);