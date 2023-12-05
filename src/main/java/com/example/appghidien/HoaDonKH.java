package com.example.appghidien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appghidien.Adapter.HoaDonAdapter;
import com.example.appghidien.Adapter.HoaDonKhAdapter;
import com.example.appghidien.Model.HoaDonBangGhiDien;
import com.example.appghidien.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDonKH extends AppCompatActivity {
    TextView txtMAKH,txtDiaChi;
    ListView lvHoaDonKH;
    ArrayList<HoaDonBangGhiDien>hoaDonlist;
    HoaDonKhAdapter HDadapter;
    String manha="Nha1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_hoadon_kh);
        txtMAKH=findViewById(R.id.txtMAKH);
        txtDiaChi=findViewById(R.id.txtDIACHIkh);
        Intent intent = getIntent();
        String makh = intent.getStringExtra("key_MaKH");
        txtMAKH.setText(makh);


        CallHoaDon(manha);

        lvHoaDonKH = findViewById(R.id.ListViewHoaDonKH);
        hoaDonlist =new ArrayList<>();
        HDadapter = new HoaDonKhAdapter(this,R.layout.hoadon_kh,hoaDonlist);
        lvHoaDonKH.setAdapter(HDadapter);
    }

    public void CallHoaDon(String MaNha) {
        ApiService.apiService.getHoaDonKH(MaNha).enqueue(new Callback<List<HoaDonBangGhiDien>>() {
            @Override
            public void onResponse(Call<List<HoaDonBangGhiDien>> call, Response<List<HoaDonBangGhiDien>> response) {
                if (response.isSuccessful()) {
                    List<HoaDonBangGhiDien> lists = response.body();
                    hoaDonlist.clear();
                    for(int i=0;i<lists.size();i++)
                    {
                        HoaDonBangGhiDien hd =lists.get(i);
                        hoaDonlist.add(hd);
                    }
                    HDadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<HoaDonBangGhiDien>> call, Throwable t) {
                Toast.makeText(HoaDonKH.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
