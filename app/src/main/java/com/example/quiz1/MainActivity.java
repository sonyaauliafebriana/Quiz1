package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etInputItem, etKodebrg, etbrg;
    private RadioGroup radioGroup;
    private Button btnProses;
    private TextView tvDiskon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputItem = findViewById(R.id.etinputitem);
        etKodebrg = findViewById(R.id.etKodebrg);
        etbrg = findViewById(R.id.etbrg);
        radioGroup = findViewById(R.id.radioGroup);
        btnProses = findViewById(R.id.btnProses);
        tvDiskon = findViewById(R.id.tvDiskon);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesTransaksi();
            }
        });
    }

    private void prosesTransaksi() {
        String namaPembeli = etInputItem.getText().toString().trim();
        String kodeBarang = etKodebrg.getText().toString().trim();
        String jumlahBarang = etbrg.getText().toString().trim();
        String jenisMember = "";

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            jenisMember = selectedRadioButton.getText().toString();
        }

        // Menghitung total harga
        int hargaBarang = 0;
        switch (kodeBarang) {
            case "SGS":
                hargaBarang = 12999999;
                break;
            case "IPX":
                hargaBarang = 5725300;
                break;
            case "OAS":
                hargaBarang = 1989123;
                break;
            default:
                Toast.makeText(this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
                return;
        }

        int totalHarga = Integer.parseInt(jumlahBarang) * hargaBarang;

        // Diskon
        int diskon = 0;
        if (totalHarga > 10000000) {
            diskon += 100000;
        }
        switch (jenisMember) {
            case "Gold":
                diskon += totalHarga * 0.1;
                break;
            case "Silver":
                diskon += totalHarga * 0.05;
                break;
            case "Biasa":
                diskon += totalHarga * 0.02;
                break;
        }

        // Mengurangi diskon dari total harga
        int totalBayar = totalHarga - diskon;

        // Menyiapkan Intent untuk menampilkan detail Barang
        Intent intent = new Intent(this, DetailBarangActivity.class);
        intent.putExtra("NAMA_PEMBELI", namaPembeli);
        intent.putExtra("KODE_BARANG", kodeBarang);
        intent.putExtra("JUMLAH_BARANG", jumlahBarang);
        intent.putExtra("JENIS_MEMBER", jenisMember);
        intent.putExtra("TOTAL_HARGA", totalHarga);
        intent.putExtra("DISKON", diskon);
        intent.putExtra("TOTAL_BAYAR", totalBayar);

        // Memulai aktivitas DetailTransaksiActivity
        startActivity(intent);
    }
}
