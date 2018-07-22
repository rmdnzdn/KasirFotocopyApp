-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 01, 2017 at 01:52 AM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_fotocopy`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `qw_transaksi`
--
CREATE TABLE IF NOT EXISTS `qw_transaksi` (
`kode_transaksi` varchar(11)
,`kode_barang` varchar(12)
,`jumlah` int(11)
,`subtotal` int(11)
,`status` int(1)
,`nama_barang` varchar(50)
,`harga` int(11)
);
-- --------------------------------------------------------

--
-- Table structure for table `tbl_barang`
--

CREATE TABLE IF NOT EXISTS `tbl_barang` (
  `kode_barang` varchar(12) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `stok` int(11) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_barang`
--

INSERT INTO `tbl_barang` (`kode_barang`, `nama_barang`, `stok`, `harga`) VALUES
('BRG201706001', 'kopi pajit', 10, 1000),
('BRG201706002', 'buku 58 lembar', 100, 2000),
('BRG201706003', 'pulpen', 10, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaksi`
--

CREATE TABLE IF NOT EXISTS `tbl_transaksi` (
  `kode_transaksi` varchar(11) NOT NULL,
  `kode_barang` varchar(12) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_transaksi`
--

INSERT INTO `tbl_transaksi` (`kode_transaksi`, `kode_barang`, `jumlah`, `subtotal`, `status`) VALUES
('20170601001', 'BRG201706001', 3, 3000, 0),
('20170601001', 'BRG201706003', 3, 30000, 0);

-- --------------------------------------------------------

--
-- Structure for view `qw_transaksi`
--
DROP TABLE IF EXISTS `qw_transaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `qw_transaksi` AS select `tbl_transaksi`.`kode_transaksi` AS `kode_transaksi`,`tbl_transaksi`.`kode_barang` AS `kode_barang`,`tbl_transaksi`.`jumlah` AS `jumlah`,`tbl_transaksi`.`subtotal` AS `subtotal`,`tbl_transaksi`.`status` AS `status`,`tbl_barang`.`nama_barang` AS `nama_barang`,`tbl_barang`.`harga` AS `harga` from (`tbl_transaksi` join `tbl_barang` on((`tbl_barang`.`kode_barang` = `tbl_transaksi`.`kode_barang`)));

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
