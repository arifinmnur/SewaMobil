/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 10.1.22-MariaDB : Database - db_sbd12_sewa_mobil
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_sbd12_sewa_mobil` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_sbd12_sewa_mobil`;

/*Data for the table `detail_transaksi` */

insert  into `detail_transaksi`(`no_transaksi`,`no_pol`,`id_petugas_pengembalian`,`tglkembali`,`denda`,`catatan`) values 
('TRX0000001','D4021TZ','PG003','2018-01-24 21:37:41',0,'s'),
('TRX0000002','D4652TZ','PG001','2018-01-20 23:47:01',50000,NULL),
('TRX0000002','D4666AS',NULL,NULL,NULL,NULL),
('TRX0000003','D2111FZ',NULL,NULL,NULL,NULL);

/*Data for the table `login` */

insert  into `login`(`id`,`username`,`password`,`nama`,`email`,`level`,`status`) values 
(1,'admin','admin','Divananda Zikry Fadilla','admin@gmail.com','Admin','Tidak Login'),
(2,'ivan','ivan','icut','divanandazf@gmail.com','Operator','Tidak Login'),
(3,'mila','milamonika','Mila Monika Berlyana','mila@mila.com','User','Tidak Login');

/*Data for the table `tbl_data_mobil` */

insert  into `tbl_data_mobil`(`no_pol`,`id_merk_mobil`,`id_owner`) values 
('D2111FZ','HND06','OW01'),
('D2111TE','MTS03','OW01'),
('D2222ER','HND05','OW01'),
('D2223KJ','SZI07','OW02'),
('D2223RT','TYT13','OW01'),
('D2321KL','SZI03','OW02'),
('D2324KO','SZI02','OW02'),
('D2424HD','HND03','OW02'),
('D2428HD','HND04','OW02'),
('D2442HD','HND10','OW01'),
('D4021TZ','TYT01','OW03'),
('D4025FS','TYT01','OW03'),
('D4053RS','HND01','OW03'),
('D4221FA','TYT03','OW03'),
('D4222FZ','TYT01','OW03'),
('D4223FZ','TYT04','OW03'),
('D4252KL','SZI01','OW02'),
('D4444RE','TYT14','OW01'),
('D4612FA','TYT05','OW01'),
('D4652TZ','HND02','OW03'),
('D4666AS','HND02','OW03'),
('D5543EE','TYT15','OW01'),
('D5555KL','SZI04','OW02'),
('D6541FZ','TYT06','OW01'),
('D7777HD','HND09','OW01'),
('D8181FR','TYT07','OW01'),
('D8888FS','TYT08','OW01'),
('D8888HD','HND08','OW01'),
('D8888KI','SZI08','OW02'),
('D8998KL','SZI05','OW02'),
('D9000MT','MTS02','OW01'),
('D9765TY','TYT12','OW01'),
('D9787KL','MTS04','OW01'),
('D9792RZ','TYT11','OW01'),
('D9898RF','TYT10','OW01'),
('D9999DE','TYT09','OW01'),
('D9999ED','HND11','OW02'),
('D9999HD','HND07','OW01'),
('D9999IJ','SZI06','OW02'),
('D9999TE','MTS01','OW01');

/*Data for the table `tbl_data_transaksi` */

insert  into `tbl_data_transaksi`(`no_transaksi`,`id_kostumer`,`id_pegawai`,`tglpinjam`,`tglkembali`,`harga_sebelum_diskon`,`harga_diskon`,`tgltransaksi`,`Status`) values 
('TRX0000001','KZ0000036','PG002','2018-01-24','2018-01-24',250000,25000,'2018-01-15 09:03:41','BELUM SELESAI'),
('TRX0000002','KZ000041B','PG001','2018-01-18','2018-01-18',400000,40000,'2018-01-15 09:08:53','BELUM SELESAI'),
('TRX0000003','KZ000041D','PG002','2018-01-18','2018-01-20',0,0,'2018-01-16 23:59:12','BELUM SELESAI');

/*Data for the table `tbl_jenis_member_dan_diskon` */

insert  into `tbl_jenis_member_dan_diskon`(`counter`,`id_jenis_member`,`nama_jenis_member`,`diskon`) values 
(1,'NON','Non-Member',0),
(3,'PRM','Premium',0.2),
(2,'REG','Regular',0.1);

/*Data for the table `tbl_jenis_mobil` */

insert  into `tbl_jenis_mobil`(`id_jenis_mobil`,`nama_jenis`,`harga`) values 
('BUS1','BUS AC',2000000),
('FMY1','Family Ekonomi',250000),
('FMY2','Family Eksekutif',350000),
('FMY3','Family Premium',450000),
('MBS1','Mini Bus',900000),
('SDN1','Sedan Ekonomi',300000),
('SDN2','Sedan Eksekutif',400000),
('SDN3','Sedan Premium',600000);

/*Data for the table `tbl_kostumer` */

insert  into `tbl_kostumer`(`id_kostumer`,`no_ktp`,`id_member`,`jenis_member`,`nama_depan_k`,`nama_belakang_k`,`jenis_kelamin_k`,`alamat_k`,`tanggal_lahir_k`,`no_telepon_k`,`tanggal_join`) values 
('KZ0000036','101020201298','REGL00001','REG','Rendy','Gilang','L','Jalan Dago','1991-07-23','081256347888','2016-03-08'),
('KZ0000037','101020201212','REGP00001','REG','Raisa','Reid','P','Jalan Batu','1989-11-07',NULL,'2016-07-14'),
('KZ0000096','101020201266','PRML00001','PRM','Angga','Wahyu','L','Jalan Dipatiukur','1994-07-21','081256347823','2015-12-24'),
('KZ000041B','1011143512','REGP00002','REG','Hannah','Melody','P','Jalan Jakarta','1991-08-08','089652524002','2017-12-24'),
('KZ000041C','121212121212',NULL,'NON','Andi','Gilang','L','Jalan Surabaya','1991-06-24',NULL,'2017-12-24'),
('KZ000041D','121212121213',NULL,'NON','Prima','Rahayu','P','Jalan Antapani','1989-07-04',NULL,'2017-12-24'),
('KZ000041E','44444444','PRML00002','PRM','Rivat','Sungkar','L','Jalan Pabuaran','1991-12-31',NULL,'2017-12-31'),
('KZ000041F','3333333333',NULL,'NON','Ghina','Hannah','P','Jalan Setiabudi','1992-12-23','0896','2017-12-31'),
('KZ0000420','5555555555555555',NULL,'NON','Sani','Arisani','P','','1997-03-24',NULL,'2017-12-31');

/*Data for the table `tbl_merk_mobil` */

insert  into `tbl_merk_mobil`(`id_merk_mobil`,`id_produsen_mobil`,`id_jenis_mobil`,`nama_mobil`) values 
('HND01','HND','SDN3','Accord'),
('HND02','HND','SDN2','Civic'),
('HND03','HND','SDN1','Mobil Baru'),
('HND04','HND','FMY2','Jazz RS'),
('HND05','HND','FMY1','Mobilio'),
('HND06','HND','FMY1','Brio'),
('HND07','HND','FMY2','HR-V'),
('HND08','HND','FMY2','CR-V'),
('HND09','HND','FMY1','BR-V'),
('HND10','HND','SDN2','City'),
('HND11','HND','FMY3','Oddysey'),
('MTS01','MTS','FMY1','Xpander'),
('MTS02','MTS','FMY2','Pajero Sport'),
('MTS03','MTS','FMY2','Outlander'),
('MTS04','MTS','FMY1','Mirage'),
('SZI01','SZI','FMY1','Ertiga'),
('SZI02','SZI','FMY1','APV'),
('SZI03','SZI','FMY1','Karimun'),
('SZI04','SZI','FMY3','SX4'),
('SZI05','SZI','FMY2','Ignis'),
('SZI06','SZI','FMY1','Swift'),
('SZI07','SZI','SDN1','Baleno'),
('SZI08','SZI','FMY2','Grand Vitara'),
('TYT01','TYT','FMY1','Avanza'),
('TYT02','TYT','FMY1','Avanza Velonz'),
('TYT03','TYT','SDN3','Lancer Evo'),
('TYT04','TYT','FMY2','Rush'),
('TYT05','TYT','FMY1','Calya'),
('TYT06','TYT','FMY2','Kijang Innova'),
('TYT07','TYT','FMY3','Fortuner'),
('TYT08','TYT','FMY2','Yaris'),
('TYT09','TYT','FMY2','Sienta'),
('TYT10','TYT','FMY2','Voxy'),
('TYT11','TYT','MBS1','Hiace'),
('TYT12','TYT','FMY3','Vellfire'),
('TYT13','TYT','SDN2','Camry'),
('TYT14','TYT','SDN1','Vios'),
('TYT15','TYT','SDN2','Corrola Altis');

/*Data for the table `tbl_owner_mobil` */

insert  into `tbl_owner_mobil`(`id_owner`,`no_ktp_ow`,`nama_ow`,`jenis_kelamin_ow`,`alamat_ow`,`no_telepon_ow`) values 
('OW01','101020201234','Afrizal','L','Jalan Setiabudi','081256123434'),
('OW02','101020201235','Risa','P','Jalan Geger Kalong','089656123443'),
('OW03','101020201245','Sarah','P','Jalan Dipatiukur','0896');

/*Data for the table `tbl_pegawai` */

insert  into `tbl_pegawai`(`id_pegawai`,`no_ktp_p`,`nama_depan_p`,`nama_belakang_p`,`jenis_kelamin_p`,`alamat_p`,`tanggal_lahir_p`,`no_telepon_p`,`username`,`password`,`tanggal_join_p`) values 
('PG001','101020201277','Mira','Damayanti','P','Jalan Jakarta','1990-03-21','081257592325','mira54','petugas','2015-03-05'),
('PG002','101020201288','Dira','Sukma','L','Jalan Bogor','1997-05-06','081256565640','sukma25','petugas','2015-05-08'),
('PG003','222222222222222','Sari ','Ardisa','P','jalan Dago','1998-01-10','0896','Sari46','petugas','2018-01-02');

/*Data for the table `tbl_pembayaran` */

insert  into `tbl_pembayaran`(`no_transaksi`,`id_pembayaran`,`jenis_pembayaran`,`jumlah`,`tglpembayaran`,`keterangan`) values 
('TRX0000001',1,'PELUNASAN',225000,'2018-01-15 09:03:41','Pelunasan kode_transaksi=TRX0000001 Nama Kostumer=Rendy Gilang'),
('TRX0000002',1,'PELUNASAN',360000,'2018-01-15 09:08:53','Pelunasan kode_transaksi=TRX0000002 Nama Kostumer=Hannah Melody');

/*Data for the table `tbl_pengembalian` */

/*Data for the table `tbl_produsen_mobil` */

insert  into `tbl_produsen_mobil`(`id_produsen_mobil`,`nama_produsen`) values 
('HND','Honda'),
('MTS','Mitsubishi'),
('SZI','Suzuki'),
('TYT','TOYOTA');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
