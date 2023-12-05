package com.example.appghidien;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appghidien.Model.DongHoDien;
import com.example.appghidien.Model.Nha;
import com.example.appghidien.Model.NhanVien;
import com.example.appghidien.Model.Tinh;
import com.example.appghidien.api.ApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {
    private Spinner spnChucVu;
    private EditText edtTen,edtNgaySinh,edtMatKhau;
    private Button btndk;
    List<String> chucVuList = new ArrayList<String>();
    String manv,ten,matkhau,ngaysinh,chucvu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        spnChucVu = findViewById(R.id.spinner_ChucVu);
        edtTen=findViewById(R.id.dkTen);
        edtNgaySinh=findViewById(R.id.dkngaysinh);
        edtMatKhau=findViewById(R.id.dkmatkhau);
        btndk=findViewById(R.id.dkdangky);
        chucVuList.add("Nhân Viên Ghi Điện");
        chucVuList.add("Nhân Viên Lắp Đặt");
        chucVuList.add("Nhân Viên Thu Ngân");
        chucVuList.add("Nhân Viên Quản Lý");
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_trangchu2);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_ListHD:
                        Intent intent = new Intent(DangKy.this, HoaDon2.class);
                        startActivity(intent);
                }
                return true;
            }
        });

        ArrayAdapter<String> adapterCV = new ArrayAdapter<>(DangKy.this, android.R.layout.simple_spinner_dropdown_item, chucVuList);
        spnChucVu.setAdapter(adapterCV);

        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
        spnChucVu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedChucvu = (String) parent.getItemAtPosition(position);
                chucvu = selectedChucvu;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten = edtTen.getText().toString();
                ngaysinh = edtNgaySinh.getText().toString();
                matkhau = edtMatKhau.getText().toString();
                if (ten.isEmpty()||ngaysinh.isEmpty()||matkhau.isEmpty())
                {
                    Toast.makeText(DangKy.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    Random random = new Random();
                    manv = "NV".concat(String.valueOf(random.nextInt(1000)));
                    Toast.makeText(DangKy.this, "Mã NV: "+ manv, Toast.LENGTH_SHORT).show();
                    CallAddNV(manv,ten,ngaysinh,matkhau,chucvu);
                }
            }
        });
    }
    public void ChonNgay()
    {
        Calendar calendar = Calendar.getInstance();
        int NgayHienTai = calendar.get(calendar.DATE);
        int ThangHienTai = calendar.get(calendar.MONTH);
        int NamHienTai = calendar.get(calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        SimpleDateFormat simpleDateFormat = new
                                SimpleDateFormat("yyyy/MM/dd");
                        edtNgaySinh.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },NamHienTai, ThangHienTai, NgayHienTai );
        datePickerDialog.show();
    }

    public void CallAddNV(String Manv,String TenNV,String NgaySinh,String ChucVu,String MatKhau)
    {
        ApiService.apiService.createNhanVien(Manv,TenNV,NgaySinh,ChucVu,MatKhau).enqueue(new Callback<NhanVien>() {
            @Override
            public void onResponse(Call<NhanVien> call, Response<NhanVien> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Thêm Thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NhanVien> call, Throwable t) {
                Toast.makeText(DangKy.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
