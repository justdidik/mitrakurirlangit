package com.xeon.mitrakurirlangitf.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xeon.mitrakurirlangitf.API.APIRequestData;
import com.xeon.mitrakurirlangitf.API.RetroServer;
import com.xeon.mitrakurirlangitf.Model.ResponseModel;
import com.xeon.mitrakurirlangitf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etAlamat, etTelepon;
    private Button btnSimpan;
    private String nama,alamat,telepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAlamat = findViewById(R.id.et_alamat);
        etTelepon = findViewById(R.id.et_Telpon);
        btnSimpan = findViewById(R.id.btn_simpan);


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                alamat = etAlamat.getText().toString();
                telepon = etTelepon.getText().toString();

                if(nama.trim().equals("")){
                    etNama.setError("Nama Harus Diisi");

                }
                else if (alamat.trim().equals("")){
                    etAlamat.setError("Alamat Harus Diisi");
                }
                else if (telepon.trim().equals("")){
                    etTelepon.setError("Telepon Harus Diisi");
                }
                else {
                    createData();
                }

            }
        });


    }


    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(nama,alamat,telepon);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : "+kode+ "Pesan"+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menyimpan"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
