package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailBarangActivity extends AppCompatActivity {

    private TextView tvNamaPembeli, tvNamaBarang, tvKodeBarang, tvJumlahBarang, tvTotalHarga, tvDiskon, tvTotalBayar, tvWelcome, tvMembershipType;
    private String[] kodeBarangToNama = {"Samsung Galaxy S", "Iphone X", "Oppo A5s"};
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        tvNamaPembeli = findViewById(R.id.tvNamaPembeli);
        tvNamaBarang = findViewById(R.id.tvNamaBarang);
        tvKodeBarang = findViewById(R.id.tvKodeBarang);
        tvJumlahBarang = findViewById(R.id.tvJumlahBarang);
        tvTotalHarga = findViewById(R.id.tvTotalHarga);
        tvDiskon = findViewById(R.id.tvDiskon);
        tvTotalBayar = findViewById(R.id.tvTotalBayar);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvMembershipType = findViewById(R.id.tvMembershipType);
        btnShare = findViewById(R.id.btnShare);

        // Data dari Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String namaPembeli = extras.getString("NAMA_PEMBELI");
            String kodeBarang = extras.getString("KODE_BARANG");
//            String namaBarang = extras.getString("NAMA_BARANG");
            String jumlahBarang = extras.getString("JUMLAH_BARANG");
            String jenisMember = extras.getString("JENIS_MEMBER");
            int totalHarga = extras.getInt("TOTAL_HARGA");
            int diskon = extras.getInt("DISKON");
            int totalBayar = extras.getInt("TOTAL_BAYAR");

            // Nama barang berdasarkan kode barang
            String namaBarang = "";
            switch (kodeBarang) {
                case "SGS":
                    namaBarang = kodeBarangToNama[0];
                    break;
                case "IPX":
                    namaBarang = kodeBarangToNama[1];
                    break;
                case "OAS":
                    namaBarang = kodeBarangToNama[2];
                    break;
            }

            tvWelcome.setText("Selamat Datang " + namaPembeli); // Menampilkan ucapan selamat datang
            tvMembershipType.setText("Tipe Membership : " + jenisMember); // Menampilkan jenis keanggotaan
            tvNamaPembeli.setText("Nama Pembeli : " + namaPembeli);
            tvNamaBarang.setText("Nama Barang : " + namaBarang); // Menampilkan nama barang
            tvKodeBarang.setText("Kode Barang : " + kodeBarang);
            tvJumlahBarang.setText("Jumlah Barang : " + jumlahBarang);
            tvTotalHarga.setText("Total Harga : Rp." + totalHarga);
            tvDiskon.setText("Diskon : Rp." + diskon);
            tvTotalBayar.setText("Total Bayar : Rp." + totalBayar);

           //Tombol Share
            String finalNamaBarang = namaBarang;
            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareData(namaPembeli, finalNamaBarang, kodeBarang, jumlahBarang, jenisMember, totalHarga, diskon, totalBayar);
                }
            });
        }
    }

    private void shareData(String namaPembeli, String namaBarang, String kodeBarang, String jumlahBarang, String jenisMember, int totalHarga, int diskon, int totalBayar) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Detail Barang");
        String message = "Nama Pembeli: " + namaPembeli + "\n" +
                "Nama Barang: " + namaBarang + "\n" +
                "Kode Barang: " + kodeBarang + "\n" +
                "Jumlah Barang: " + jumlahBarang + "\n" +
                "Jenis Member: " + jenisMember + "\n" +
                "Total Harga: Rp. " + totalHarga + "\n" +
                "Diskon: Rp. " + diskon + "\n" +
                "Total Bayar: Rp. " + totalBayar;
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
