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

import com.example.appghidien.Model.CapDienAp;
import com.example.appghidien.Model.DongHoDien;
import com.example.appghidien.Model.HinhThucSD;
import com.example.appghidien.Model.LoaiDH;
import com.example.appghidien.Model.Nha;
import com.example.appghidien.Model.Tinh;
import com.example.appghidien.api.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDongHo extends AppCompatActivity {
    private Spinner spnHinhThuc,spnCapDienAp,spnLoai;
    private TextView txtMaNha,txtDiaChi,txtMaDH;
    private Button btnaddDH,btnaddNha;
    List<HinhThucSD> hinhthucsdlist = new ArrayList<>();
    List<CapDienAp> capDienApList = new ArrayList<>();
    List<LoaiDH> loaiDHList = new ArrayList<>();
    String MaHinhThuc,MaCap,MaLoai;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dong_ho);
        txtMaNha=findViewById(R.id.edMaNha);
        txtDiaChi=findViewById(R.id.edDiaChiDH);
        txtMaDH=findViewById(R.id.edMaDH);
        spnHinhThuc = findViewById(R.id.spinner_HinhThuc);
        spnCapDienAp = findViewById(R.id.spinner_capdienap);
        spnLoai = findViewById(R.id.spinner_loai);
        btnaddDH=findViewById(R.id.btnadddongho);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_dongho);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_addHome:
                        Intent intent = new Intent(AddDongHo.this, AddNha.class);
                        startActivity(intent);
                }
                return true;
            }
        });

        CallHinhThuc();
        spnHinhThuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HinhThucSD selectedHinhThuc = (HinhThucSD) parent.getItemAtPosition(position);
                MaHinhThuc = selectedHinhThuc.getMaHinhThuc(); // Lưu trữ mã hình thức được chọn
                Toast.makeText(getApplicationContext(), "Mã Hình Thức: " + MaHinhThuc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        CallCapDienAp();
        spnCapDienAp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CapDienAp selectedCapDienAp = (CapDienAp) parent.getItemAtPosition(position);
                MaCap = selectedCapDienAp.getMaCapDienAp();
                Toast.makeText(getApplicationContext(), "Mã cấp: " + MaCap, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        CallLoaiDH();
        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoaiDH selectedLoaiDH = (LoaiDH) parent.getItemAtPosition(position);
                MaLoai = selectedLoaiDH.getMaLoaiDH();
                Toast.makeText(getApplicationContext(), "Mã loại: " + MaLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnaddDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MaNha =String.valueOf(txtMaNha.getText());
                String DiaChi =String.valueOf(txtDiaChi.getText());
                String MaDH=String.valueOf(txtMaDH.getText());
                if(MaNha.isEmpty()||DiaChi.isEmpty()||MaHinhThuc.isEmpty()||MaCap.isEmpty()||MaLoai.isEmpty()||MaDH.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đủ thông tin ", Toast.LENGTH_SHORT).show();
                }else
                {
                    AddDH(MaDH,MaHinhThuc,MaCap,MaNha,MaLoai,DiaChi);
                }
            }
        });
    }

    public void AddDH(String MaDH,String MaHThuc,String MaCap, String MaNha, String MaLoai, String DiaChi)
    {
        ApiService.apiService.createDH(MaDH,MaHThuc,MaCap,MaNha,MaLoai,DiaChi).enqueue(new Callback<DongHoDien>() {
            @Override
            public void onResponse(Call<DongHoDien> call, Response<DongHoDien> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Thêm Thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DongHoDien> call, Throwable t) {
                Toast.makeText(AddDongHo.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallHinhThuc() {
        ApiService.apiService.getHinhThuc().enqueue(new Callback<List<HinhThucSD>>() {
            @Override
            public void onResponse(Call<List<HinhThucSD>> call, Response<List<HinhThucSD>> response) {
                if (response.isSuccessful()) {
                    List<HinhThucSD> lists = response.body();
                    hinhthucsdlist.clear();
                    hinhthucsdlist.addAll(lists);
                    ArrayAdapter<HinhThucSD> adapterTP = new ArrayAdapter<>(AddDongHo.this, android.R.layout.simple_spinner_dropdown_item, hinhthucsdlist);
                    spnHinhThuc.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<HinhThucSD>> call, Throwable t) {
                Toast.makeText(AddDongHo.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallCapDienAp() {
        ApiService.apiService.getCapDienAp().enqueue(new Callback<List<CapDienAp>>() {
            @Override
            public void onResponse(Call<List<CapDienAp>> call, Response<List<CapDienAp>> response) {
                if (response.isSuccessful()) {
                    List<CapDienAp> lists = response.body();
                    capDienApList.clear();
                    capDienApList.addAll(lists);
                    ArrayAdapter<CapDienAp> adapterTP = new ArrayAdapter<>(AddDongHo.this, android.R.layout.simple_spinner_dropdown_item, capDienApList);
                    spnCapDienAp.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<CapDienAp>> call, Throwable t) {
                Toast.makeText(AddDongHo.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallLoaiDH() {
        ApiService.apiService.getLoaiDH().enqueue(new Callback<List<LoaiDH>>() {
            @Override
            public void onResponse(Call<List<LoaiDH>> call, Response<List<LoaiDH>> response) {
                if (response.isSuccessful()) {
                    List<LoaiDH> lists = response.body();
                    loaiDHList.clear();
                    loaiDHList.addAll(lists);
                    ArrayAdapter<LoaiDH> adapterTP = new ArrayAdapter<>(AddDongHo.this, android.R.layout.simple_spinner_dropdown_item, loaiDHList);
                    spnLoai.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<LoaiDH>> call, Throwable t) {
                Toast.makeText(AddDongHo.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
