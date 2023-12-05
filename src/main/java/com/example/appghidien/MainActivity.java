package com.example.appghidien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appghidien.Model.KhachHang;
import com.example.appghidien.Model.NhanVien;
import com.example.appghidien.api.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public EditText edtUsername, edtpassword;
    public Button btnDangNhap, btnDangKy;
    List<NhanVien> nvlist = new ArrayList<>();
    List<KhachHang> khlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        edtUsername =findViewById(R.id.dnMaKH);
        edtpassword = findViewById(R.id.dnmatkhau);
        btnDangNhap = findViewById(R.id.dndangnhap);
        btnDangKy = findViewById(R.id.dndangky);
        CallAPI();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUserName = edtUsername.getText().toString();
                String strpassword = edtpassword.getText().toString();
                checkLogin(strUserName.toUpperCase(Locale.ROOT),strpassword.toUpperCase(Locale.ROOT));
                if(strUserName.isEmpty()||strpassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangKyKH.class);
                startActivity(intent);
            }
        });
    }

    public NhanVien checkLogin(String Ma, String Mk)
    {
        List<NhanVien> nv1=CallAPI();

        for(NhanVien a : nv1){
            if(a.getMaNV().equals(Ma)&&a.getMatKhau().equals(Mk))
            {
                if(a.getChucVu().equals("Chủ Tịch"))
                {
                    Intent intent = new Intent(MainActivity.this, HoaDon2.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }else if(a.getChucVu().equals("Nhân Viên Ghi Điện"))
                {
                    Intent intent = new Intent(MainActivity.this, GhiDien.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }else if(a.getChucVu().equals("Nhân Viên Lắp Đặt"))
                {
                    Intent intent = new Intent(MainActivity.this, AddDongHo.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }else if(a.getChucVu().equals("Nhân Viên Thu Ngân"))
                {
                    Intent intent = new Intent(MainActivity.this, HoaDon.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }
                else if(a.getChucVu().equals("Nhân Viên Quản Lý"))
                {
                    Intent intent = new Intent(MainActivity.this, HoaDon2.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }
                //return nv;
            }else{
                //Toast.makeText(MainActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                //KhachHang kh = new KhachHang();
                List<KhachHang> kh1 = CallKH();
                for(KhachHang k:kh1)
                {
                    if(k.getSdt().equals(Ma)&&k.getMatKhau().equals(Mk))
                    {
                        Intent intent = new Intent(MainActivity.this, HoaDonKH.class);
                        //String makh = k.getMaKH();
                        intent.putExtra("key_MaKH",k.getMaKH());
                        //intent.putExtra("key_DiaChi",k.)
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        //return kh;
                    }
                }
            }
        }
        return null;
    }

    public KhachHang checkKH(String Ma, String Mk)
    {
        KhachHang kh = new KhachHang();
        List<KhachHang> kh1 = CallKH();
        for(KhachHang k:kh1)
        {
            if(k.getSdt().equals(Ma)&&k.getMatKhau().equals(Mk))
            {
                Intent intent = new Intent(MainActivity.this, HoaDonKH.class);
                intent.putExtra("key_MaKH",k.getMaKH());
                //intent.putExtra("key_DiaChi",k.)
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                return kh;
            }
        }

        return null;
    }

    public List<KhachHang>CallKH()
    {
        ApiService.apiService.getKhachHang().enqueue(new Callback<List<KhachHang>>() {
            @Override
            public void onResponse(Call<List<KhachHang>> call, Response<List<KhachHang>> response) {
                if(response.isSuccessful()){
                    List<KhachHang> lists = response.body();
                    for(KhachHang kh : lists){
                        khlist.add(kh);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<KhachHang>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"API ERROR",Toast.LENGTH_SHORT).show();
            }
        });
        return khlist;
    }

    public List<NhanVien> CallAPI()
    {
            ApiService.apiService.getData().enqueue(new Callback<List<NhanVien>>() {
            @Override
            public void onResponse(Call<List<NhanVien>> call, Response<List<NhanVien>> response) {
                if(response.isSuccessful()){
                    List<NhanVien> lists = response.body();
                    for(NhanVien nv : lists){
                        nvlist.add(nv);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<NhanVien>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"API ERROR",Toast.LENGTH_SHORT).show();
            }
        });
        return nvlist;
    }

}


