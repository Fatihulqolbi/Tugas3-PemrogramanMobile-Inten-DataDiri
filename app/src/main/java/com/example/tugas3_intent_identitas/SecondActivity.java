package com.example.tugas3_intent_identitas;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    // java second
    TextView txtNama, txtNrp;
    ImageView imgKtm;
    Button btnTutup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtNama = findViewById(R.id.txtNama);
        txtNrp = findViewById(R.id.txtNrp);
        imgKtm = findViewById(R.id.imgKtm);
        btnTutup = findViewById(R.id.btnTutup);

        String nama = getIntent().getStringExtra("nama");
        String nrp = getIntent().getStringExtra("nrp");
        String ktmUriString = getIntent().getStringExtra("ktm_uri");

        txtNama.setText(nama);
        txtNrp.setText("NRP: " + nrp);

        // Menampilkan foto KTM dari user input (URI)
        if (ktmUriString != null) {
            Uri ktmUri = Uri.parse(ktmUriString);
            imgKtm.setImageURI(ktmUri);
        } else {
            // Gambar default jika user tidak pilih foto
            imgKtm.setImageResource(android.R.drawable.ic_menu_gallery);
            imgKtm.setAlpha(0.3f);
        }

        btnTutup.setOnClickListener(v -> {
            Toast.makeText(this, "Menutup Halaman...", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Menambahkan log untuk membuktikan onDestroy dipanggil sesuai kriteria Activity Lifecycle
        Log.d("Lifecycle", "onDestroy: SecondActivity ditutup");
    }
}
