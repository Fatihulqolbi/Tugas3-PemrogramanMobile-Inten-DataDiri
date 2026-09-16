package com.example.tugas3_intent_identitas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    // java utama
    EditText edtNama, edtNrp;
    Button btnPindah, btnHapusKtm;
    MaterialCardView btnPilihKtm;
    ImageView imgPreviewKtm;
    TextView tvStatusKtm;
    Uri imageUri; // Untuk menyimpan lokasi foto yang dipilih

    // Launcher untuk membuka galeri (Implicit Intent)
    private final ActivityResultLauncher<String> getContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    imageUri = uri;
                    imgPreviewKtm.setImageURI(uri);
                    imgPreviewKtm.setImageTintList(null); // Menghapus warna emas agar warna asli KTM terlihat
                    tvStatusKtm.setText("KTM Terpilih");
                    tvStatusKtm.setTextColor(getResources().getColor(R.color.primary_gold));
                    btnHapusKtm.setVisibility(android.view.View.VISIBLE); // Munculkan tombol hapus
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNama = findViewById(R.id.edtNama);
        edtNrp = findViewById(R.id.edtNrp);
        btnPindah = findViewById(R.id.btnPindah);
        btnPilihKtm = findViewById(R.id.btnPilihKtm);
        btnHapusKtm = findViewById(R.id.btnHapusKtm);
        imgPreviewKtm = findViewById(R.id.imgPreviewKtm);
        tvStatusKtm = findViewById(R.id.tvStatusKtm);

        // Listener untuk memilih foto
        btnPilihKtm.setOnClickListener(v -> getContent.launch("image/*"));

        // Listener untuk menghapus foto
        btnHapusKtm.setOnClickListener(v -> {
            imageUri = null; // Hapus data URI
            imgPreviewKtm.setImageResource(android.R.drawable.ic_menu_camera); // Balikkan ke ikon kamera
            imgPreviewKtm.setImageTintList(android.content.res.ColorStateList.valueOf(getResources().getColor(R.color.primary_gold)));
            tvStatusKtm.setText("Pilih Foto KTM (Opsional)");
            tvStatusKtm.setTextColor(getResources().getColor(R.color.text_grey));
            btnHapusKtm.setVisibility(android.view.View.GONE); // Sembunyikan tombol hapus lagi
        });

        btnPindah.setOnClickListener(v -> {
            String nama = edtNama.getText().toString();
            String nrp = edtNrp.getText().toString();

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("nama", nama);
            intent.putExtra("nrp", nrp);
            
            // Jika user memilih foto, kirim URI-nya sebagai String
            if (imageUri != null) {
                intent.putExtra("ktm_uri", imageUri.toString());
            }

            startActivity(intent);
        });
    }
}
