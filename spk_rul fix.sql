-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 22 Jun 2025 pada 16.26
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spk_rul`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `bobot`
--

CREATE TABLE `bobot` (
  `id` int(11) NOT NULL,
  `nik` int(11) NOT NULL,
  `c1` float NOT NULL,
  `c2` float NOT NULL,
  `c3` float NOT NULL,
  `c4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `bobot`
--

INSERT INTO `bobot` (`id`, `nik`, `c1`, `c2`, `c3`, `c4`) VALUES
(3, 3242, 80, 60, 100, 100),
(4, 5453, 40, 40, 40, 80),
(5, 3, 80, 60, 40, 100),
(7, 1114, 60, 80, 40, 40),
(10, 2, 90, 89, 67, 45),
(12, 324, 90, 89, 68, 57),
(13, 8945, 50, 60, 60, 34),
(14, 5, 50, 50, 50, 50),
(15, 345, 7, 80, 70, 90);

-- --------------------------------------------------------

--
-- Struktur dari tabel `hasil`
--

CREATE TABLE `hasil` (
  `nik` int(11) NOT NULL,
  `nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `hasil`
--

INSERT INTO `hasil` (`nik`, `nilai`) VALUES
(2, 1.092),
(3, 1.03707),
(5, 0.741074),
(324, 1.1375),
(345, 0.901829),
(1114, 0.82794),
(3242, 1.24707),
(5453, 0.732859),
(8945, 0.7594);

-- --------------------------------------------------------

--
-- Struktur dari tabel `jabatan`
--

CREATE TABLE `jabatan` (
  `nik` int(11) NOT NULL,
  `jabatan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `jabatan`
--

INSERT INTO `jabatan` (`nik`, `jabatan`) VALUES
(2, 'akunting'),
(3, 'teknisi'),
(5, 'karyawan'),
(324, 'Spv'),
(5453, 'Teknisi');

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `nik` int(11) NOT NULL,
  `nama` text NOT NULL,
  `alamat` text NOT NULL,
  `jenis_kelamin` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`nik`, `nama`, `alamat`, `jenis_kelamin`) VALUES
(2, 'sinta', 'jakarta selatan', 'Perempuan'),
(3, 'bambang', 'jakarta selatan', 'Laki-Laki'),
(5, 'kurnia', 'jakarta utara', 'Laki-Laki'),
(324, ' dikis', 'jakarta', 'Laki-Laki'),
(345, ' nina', 'depok', 'Perempuan'),
(543, ' diki', 'semarang', 'Laki-Laki'),
(1114, 'irfan', 'pesanggrahan', 'Laki-Laki'),
(3242, ' ardi', 'tangerang', 'Laki-Laki'),
(5453, 'bakri', 'jakarta selatan', 'Laki-Laki'),
(8945, ' anto', 'papua', 'Laki-Laki');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kriteria`
--

CREATE TABLE `kriteria` (
  `id_kriteria` int(11) NOT NULL,
  `nama_kriteria` varchar(255) NOT NULL,
  `bobot` float NOT NULL,
  `kode_kriteria` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kriteria`
--

INSERT INTO `kriteria` (`id_kriteria`, `nama_kriteria`, `bobot`, `kode_kriteria`) VALUES
(1, 'Kehadiran', 0.35, 'C1'),
(2, 'Sikap', 0.35, 'C2'),
(3, 'Kedisiplinan', 0.35, 'C3'),
(4, 'Hasil Kerja', 0.35, 'C4');

-- --------------------------------------------------------

--
-- Struktur dari tabel `sub_kriteria`
--

CREATE TABLE `sub_kriteria` (
  `id_sub_kriteria` int(11) NOT NULL,
  `C1` float NOT NULL,
  `C2` float NOT NULL,
  `C3` float NOT NULL,
  `C4` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `sub_kriteria`
--

INSERT INTO `sub_kriteria` (`id_sub_kriteria`, `C1`, `C2`, `C3`, `C4`) VALUES
(1, 0.35, 0.35, 0.35, 0.35);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `bobot`
--
ALTER TABLE `bobot`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nik` (`nik`);

--
-- Indeks untuk tabel `hasil`
--
ALTER TABLE `hasil`
  ADD PRIMARY KEY (`nik`),
  ADD KEY `nik` (`nik`);

--
-- Indeks untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD PRIMARY KEY (`nik`),
  ADD KEY `nik` (`nik`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`nik`),
  ADD UNIQUE KEY `nik` (`nik`);

--
-- Indeks untuk tabel `kriteria`
--
ALTER TABLE `kriteria`
  ADD KEY `id_kriteria` (`id_kriteria`),
  ADD KEY `kode_kriteria` (`kode_kriteria`);

--
-- Indeks untuk tabel `sub_kriteria`
--
ALTER TABLE `sub_kriteria`
  ADD PRIMARY KEY (`id_sub_kriteria`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `bobot`
--
ALTER TABLE `bobot`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `nik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123345;

--
-- AUTO_INCREMENT untuk tabel `sub_kriteria`
--
ALTER TABLE `sub_kriteria`
  MODIFY `id_sub_kriteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `bobot`
--
ALTER TABLE `bobot`
  ADD CONSTRAINT `bobot_alter` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `hasil`
--
ALTER TABLE `hasil`
  ADD CONSTRAINT `tbfkhasil` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `jabatan`
--
ALTER TABLE `jabatan`
  ADD CONSTRAINT `jabatan_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
