select  karyawan.nik, karyawan.nama, karyawan.alamat, karyawan.jenis_kelamin, "
                + "jabatan.jabatan from karyawan INNER JOIN jabatan ON karyawan.nik=jabatan.nik;

select  karyawan.nik, karyawan.nama, karyawan.alamat, karyawan.jenis_kelamin, "
                + "jabatan.jabatan from karyawan INNER JOIN jabatan ON karyawan.nik=jabatan.nik where nama like '%%' "
                 + "or alamat like '%%' or jenis_kelamin like '%%' "
                 + "or jabatan like '%%'

select penilaian_karyawan.id_penilaian, penilaian_karyawan.jam_kerja, penilaian_karyawan.absensi, penilaian_karyawan.kerapihan, "
                    + "penilaian_karyawan.keterlambatan, karyawan.nik, karyawan.nama from penilaian_karyawan "
                    + "INNER JOIN karyawan ON penilaian_karyawan.nik=karyawan.nik

select max(nilai_jam_kerja), max(nilai_absensi), max(nilai_kerapihan), min(nilai_keterlambatan) from rating_kecocokan 


select rating_kecocokan.nilai_jam_kerja, rating_kecocokan.nilai_absensi, rating_kecocokan.nilai_kerapihan, rating_kecocokan.nilai_keterlambatan, "
                    + "karyawan.nik, karyawan.nama "
                    + "from rating_kecocokan INNER JOIN karyawan ON rating_kecocokan.nik=karyawan.nik


SELECT hasil.nilai, karyawan.nik, karyawan.nama from hasil INNER JOIN karyawan ON hasil.nik=karyawan.nik ORDER BY nilai desc