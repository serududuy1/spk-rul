-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Jun 2025 pada 08.42
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
  `c4` float NOT NULL,
  `label_c1` varchar(255) NOT NULL,
  `label_c2` varchar(255) NOT NULL,
  `label_c3` varchar(255) NOT NULL,
  `label_c4` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `bobot`
--

INSERT INTO `bobot` (`id`, `nik`, `c1`, `c2`, `c3`, `c4`, `label_c1`, `label_c2`, `label_c3`, `label_c4`) VALUES
(2, 8945, 1, 1, 1, 1, 'sangat baik', 'sangat baik', 'sangat baik', 'sangat baik'),
(3, 3242, 0.8, 0.6, 1, 1, 'baik', 'cukup', 'sangat baik', 'sangat baik'),
(4, 5453, 0.4, 0.4, 0.4, 0.8, 'kurang', 'kurang', 'kurang', 'baik'),
(5, 3, 0.8, 0.6, 0.4, 1, 'baik', 'cukup', 'kurang', 'sangat baik'),
(6, 345, 1, 1, 1, 1, 'sangat baik', 'sangat baik', 'sangat baik', 'sangat baik'),
(7, 1114, 0.6, 0.8, 0.4, 0.4, 'cukup', 'baik', 'kurang', 'kurang');

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
(3, 0.98),
(345, 1.4),
(1114, 0.77),
(3242, 1.19),
(5453, 0.7),
(8945, 1.4);

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
(4, 'teknisi'),
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
(4, 'jono', 'jakarta selatan', 'Laki-Laki'),
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
-- Struktur dari tabel `penilaian_karyawan`
--

CREATE TABLE `penilaian_karyawan` (
  `id_penilaian` int(11) NOT NULL,
  `nik` int(11) NOT NULL,
  `kode_kriteria` varchar(255) NOT NULL,
  `nilai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `penilaian_karyawan`
--

INSERT INTO `penilaian_karyawan` (`id_penilaian`, `nik`, `kode_kriteria`, `nilai`) VALUES
(5, 8945, 'C1', 90),
(6, 8945, 'C2', 87),
(7, 8945, 'C3', 90),
(8, 8945, 'C4', 87);

-- --------------------------------------------------------

--
-- Struktur dari tabel `rating_kecocokan`
--

CREATE TABLE `rating_kecocokan` (
  `id_rating` varchar(255) NOT NULL,
  `nik` int(11) NOT NULL,
  `nilai_jam_kerja` float NOT NULL,
  `nilai_absensi` float NOT NULL,
  `nilai_kerapihan` float NOT NULL,
  `nilai_keterlambatan` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `rating_kecocokan`
--

INSERT INTO `rating_kecocokan` (`id_rating`, `nik`, `nilai_jam_kerja`, `nilai_absensi`, `nilai_kerapihan`, `nilai_keterlambatan`) VALUES
('PN0001', 2, 1, 1, 1, 1);

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
-- Indeks untuk tabel `penilaian_karyawan`
--
ALTER TABLE `penilaian_karyawan`
  ADD PRIMARY KEY (`id_penilaian`),
  ADD KEY `nik` (`nik`),
  ADD KEY `penilaian_kriteria_ibfk_1` (`kode_kriteria`);

--
-- Indeks untuk tabel `rating_kecocokan`
--
ALTER TABLE `rating_kecocokan`
  ADD PRIMARY KEY (`id_rating`),
  ADD KEY `nik` (`nik`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `nik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123345;

--
-- AUTO_INCREMENT untuk tabel `penilaian_karyawan`
--
ALTER TABLE `penilaian_karyawan`
  MODIFY `id_penilaian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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

--
-- Ketidakleluasaan untuk tabel `penilaian_karyawan`
--
ALTER TABLE `penilaian_karyawan`
  ADD CONSTRAINT `penilaian_karyawan_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `penilaian_kriteria_ibfk_1` FOREIGN KEY (`kode_kriteria`) REFERENCES `kriteria` (`kode_kriteria`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `rating_kecocokan`
--
ALTER TABLE `rating_kecocokan`
  ADD CONSTRAINT `rating_kecocokan_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `karyawan` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
