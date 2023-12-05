package com.example.appghidien;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appghidien.Adapter.HoaDonAdapter;
import com.example.appghidien.Adapter.HoaDonAdapter2;
import com.example.appghidien.Model.HoaDonBangGhiDien;
import com.example.appghidien.Model.PhuongXa;
import com.example.appghidien.Model.QuanHuyen;
import com.example.appghidien.Model.Tinh;
import com.example.appghidien.api.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDon2 extends AppCompatActivity {
    private Spinner spnTinhTP, spnQH, spnPX;
    List<Tinh> tplist = new ArrayList<>();
    List<QuanHuyen> qhlist = new ArrayList<>();
    List<PhuongXa> pxlist = new ArrayList<>();
    String MaTinh,MaQuan,MaPhuong;
    ListView lvHoaDon;
    ArrayList<HoaDonBangGhiDien>hoaDonlist;
    HoaDonAdapter2 HDadapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_2);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_trangchu2);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_addNV:
                        Intent intent = new Intent(HoaDon2.this, DangKy.class);
                        startActivity(intent);
                }
                return true;
            }
        });

        spnTinhTP = findViewById(R.id.spinner_TinhTP2);
        spnQH = findViewById(R.id.spinner_QH2);
        spnPX = findViewById(R.id.spinner_PX2);
        CallTP();
        spnTinhTP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Tinh selectedTinh = (Tinh) parent.getItemAtPosition(position);
                MaTinh = selectedTinh.getMaTinhTP(); // Lưu trữ mã tỉnh được chọn
                Toast.makeText(getApplicationContext(), "Mã tỉnh: " + MaTinh, Toast.LENGTH_SHORT).show();
                CallQH(MaTinh);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnQH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                QuanHuyen slectedQuan = (QuanHuyen) parent.getItemAtPosition(position);
                MaQuan = slectedQuan.getMaQuanHuyen();
                Toast.makeText(getApplicationContext(), "Mã Quận: " + MaQuan, Toast.LENGTH_SHORT).show();
                CallPX(MaQuan);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnPX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PhuongXa selectedPhuong = (PhuongXa) parent.getItemAtPosition(position);
                MaPhuong = selectedPhuong.getMaPhuongXa();
                //CallNHA(MaPhuong);
                CallHoaDon(MaPhuong);
                Toast.makeText(getApplicationContext(), "Mã Phường: " + MaPhuong, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lvHoaDon = findViewById(R.id.ListViewHoaDon2);
        hoaDonlist =new ArrayList<>();
        HDadapter = new HoaDonAdapter2(this,R.layout.layout_view_hoadon_2,hoaDonlist);
        lvHoaDon.setAdapter(HDadapter);
    }

    public void CallTP() {
        ApiService.apiService.getTinhTP().enqueue(new Callback<List<Tinh>>() {
            @Override
            public void onResponse(Call<List<Tinh>> call, Response<List<Tinh>> response) {
                if (response.isSuccessful()) {
                    List<Tinh> lists = response.body();
                    tplist.clear();
                    tplist.addAll(lists);
                    ArrayAdapter<Tinh> adapterTP = new ArrayAdapter<>(HoaDon2.this, android.R.layout.simple_spinner_dropdown_item, tplist);
                    spnTinhTP.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<Tinh>> call, Throwable t) {
                Toast.makeText(HoaDon2.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallQH(String MaTinhTP) {
        ApiService.apiService.getQuanHuyen(MaTinhTP).enqueue(new Callback<List<QuanHuyen>>() {
            @Override
            public void onResponse(Call<List<QuanHuyen>> call, Response<List<QuanHuyen>> response) {
                if (response.isSuccessful()) {
                    List<QuanHuyen> lists = response.body();
                    qhlist.clear();
                    qhlist.addAll(lists);
                    ArrayAdapter<QuanHuyen> adapterTP = new ArrayAdapter<>(HoaDon2.this, android.R.layout.simple_spinner_dropdown_item, qhlist);
                    spnQH.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<QuanHuyen>> call, Throwable t) {
                Toast.makeText(HoaDon2.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallPX(String MaQuan) {
        ApiService.apiService.getPhuongXa(MaQuan).enqueue(new Callback<List<PhuongXa>>() {
            @Override
            public void onResponse(Call<List<PhuongXa>> call, Response<List<PhuongXa>> response) {
                if (response.isSuccessful()) {
                    List<PhuongXa> lists = response.body();
                    pxlist.clear();
                    pxlist.addAll(lists);
                    ArrayAdapter<PhuongXa> adapterPX = new ArrayAdapter<>(HoaDon2.this, android.R.layout.simple_spinner_dropdown_item, pxlist);
                    spnPX.setAdapter(adapterPX);
                }
            }

            @Override
            public void onFailure(Call<List<PhuongXa>> call, Throwable t) {
                Toast.makeText(HoaDon2.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallHoaDon(String MaPhuongXa) {
        ApiService.apiService.getHoaDon(MaPhuongXa).enqueue(new Callback<List<HoaDonBangGhiDien>>() {
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
                Toast.makeText(HoaDon2.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
