package com.xeon.mitrakurirlangitf.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xeon.mitrakurirlangitf.API.APIRequestData;
import com.xeon.mitrakurirlangitf.API.RetroServer;
import com.xeon.mitrakurirlangitf.Adapter.AdapterDAta;
import com.xeon.mitrakurirlangitf.Model.DataModel;
import com.xeon.mitrakurirlangitf.Model.ResponseModel;
import com.xeon.mitrakurirlangitf.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    private FloatingActionButton fabtambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvData = findViewById(R.id.rv_data);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        fabtambah = findViewById(R.id.fab_tambah);

    }



    public void retriveData(){

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardRetriveData();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                //Toast.makeText(MainActivity.this, "Kode"+kode+"Pesan"+pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                adData = new AdapterDAta(MainActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Gagal :"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
        fabtambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });

    }
    protected void onResume(){
        super.onResume();
        retriveData();
    }

}
