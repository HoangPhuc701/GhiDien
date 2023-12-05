package com.example.appghidien;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appghidien.Model.KhachHang;
import com.example.appghidien.Model.Nha;
import com.example.appghidien.api.ApiService;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyKH extends AppCompatActivity {
    TextView txtTen,txtSDT,txtMatKhau,txtMaNha;
    Button btndk;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky_kh);
        txtTen=findViewById(R.id.dkTenKH);
        txtSDT=findViewById(R.id.dksdtKH);
        txtMatKhau=findViewById(R.id.dkmatkhauKH);
        txtMaNha=findViewById(R.id.dkmanha);
        btndk=findViewById(R.id.dkdangkyKH);

        String ten = String.valueOf(txtTen.getText());
        String sdt = String.valueOf(txtSDT.getText());
        String matkhau = String.valueOf(txtMatKhau.getText());
        String manha = String.valueOf(txtMaNha.getText());

        if(ten.isEmpty()||sdt.isEmpty()||matkhau.isEmpty()||manha.isEmpty())
        {
            Toast.makeText(this, "vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
        }else
        {
            Random random = new Random();
            String MaKH = "KH-".concat(String.valueOf(random.nextInt(1000)));
            ApiService.apiService.createKH(MaKH,ten,sdt,matkhau,manha).enqueue(new Callback<KhachHang>() {
                @Override
                public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Thêm Thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<KhachHang> call, Throwable t) {
                    Toast.makeText(DangKyKH.this, "API ERROR", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
