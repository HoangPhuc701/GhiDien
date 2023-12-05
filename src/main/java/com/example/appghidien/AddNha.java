package com.example.appghidien;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appghidien.Model.Nha;
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

public class AddNha extends AppCompatActivity {
    private Spinner spnTinhTP, spnQH, spnPX;
    List<Tinh> tplist = new ArrayList<>();
    List<QuanHuyen> qhlist = new ArrayList<>();
    List<PhuongXa> pxlist = new ArrayList<>();
    String MaTinh,MaQuan,MaPhuong;
    TextView txtMaNha,txtSoNha,txtTenDuong,txtTenChuHo;
    Button btnnha,btnquaylai;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_nha);
        spnTinhTP = findViewById(R.id.spinner_TinhTP1);
        spnQH = findViewById(R.id.spinner_QH1);
        spnPX = findViewById(R.id.spinner_PX1);
        txtMaNha=findViewById(R.id.edtMaNha);
        txtSoNha=findViewById(R.id.edtSoNha);
        txtTenDuong=findViewById(R.id.edtTenDuong);
        txtTenChuHo=findViewById(R.id.edtTenChuHo);
        btnnha=findViewById(R.id.btnaddnha);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_nha);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_addWatch:
                        Intent intent = new Intent(AddNha.this, AddDongHo.class);
                        startActivity(intent);
                }
                return true;
            }
        });

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
                Toast.makeText(getApplicationContext(), "Mã Phường: " + MaPhuong, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnnha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MaNha =String.valueOf(txtMaNha.getText());
                String SoNha =String.valueOf(txtSoNha.getText());
                String TenDuong =String.valueOf(txtTenDuong.getText());
                String TenChuHo =String.valueOf(txtTenChuHo.getText());
                if(MaNha.isEmpty()||SoNha.isEmpty()||TenDuong.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }else
                {
                    ApiService.apiService.createNha(MaNha,SoNha,TenDuong,MaPhuong,TenChuHo).enqueue(new Callback<Nha>() {
                        @Override
                        public void onResponse(Call<Nha> call, Response<Nha> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Thêm Thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Nha> call, Throwable t) {
                            Toast.makeText(AddNha.this, "API ERROR", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    public void CallTP() {
        ApiService.apiService.getTinhTP().enqueue(new Callback<List<Tinh>>() {
            @Override
            public void onResponse(Call<List<Tinh>> call, Response<List<Tinh>> response) {
                if (response.isSuccessful()) {
                    List<Tinh> lists = response.body();
                    tplist.clear();
                    tplist.addAll(lists);
                    ArrayAdapter<Tinh> adapterTP = new ArrayAdapter<>(AddNha.this, android.R.layout.simple_spinner_dropdown_item, tplist);
                    spnTinhTP.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<Tinh>> call, Throwable t) {
                Toast.makeText(AddNha.this, "API ERROR", Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<QuanHuyen> adapterTP = new ArrayAdapter<>(AddNha.this, android.R.layout.simple_spinner_dropdown_item, qhlist);
                    spnQH.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<QuanHuyen>> call, Throwable t) {
                Toast.makeText(AddNha.this, "API ERROR", Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<PhuongXa> adapterPX = new ArrayAdapter<>(AddNha.this, android.R.layout.simple_spinner_dropdown_item, pxlist);
                    spnPX.setAdapter(adapterPX);
                }
            }

            @Override
            public void onFailure(Call<List<PhuongXa>> call, Throwable t) {
                Toast.makeText(AddNha.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
