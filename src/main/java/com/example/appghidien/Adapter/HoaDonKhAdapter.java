package com.example.appghidien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.appghidien.HoaDonKH;
import com.example.appghidien.Model.HoaDonBangGhiDien;
import com.example.appghidien.R;
import com.example.appghidien.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDonKhAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<HoaDonBangGhiDien> hoaDonBangGhiDienList ;

    public HoaDonKhAdapter(Context context, int layout, List<HoaDonBangGhiDien> hoaDonBangGhiDienList) {
        this.context = context;
        this.layout = layout;
        this.hoaDonBangGhiDienList = hoaDonBangGhiDienList;
    }

    @Override
    public int getCount() {
        return hoaDonBangGhiDienList.size();
    }

    @Override
    public Object getItem(int position) {
        return hoaDonBangGhiDienList.get(position);
        //return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);

        // ánh xạ view
        TextView txtNgay =(TextView) convertView.findViewById(R.id.txtNgay);
        TextView txtKhachHang =(TextView) convertView.findViewById(R.id.txtKhachHang);
        TextView txtSoHoaDon =(TextView)convertView.findViewById(R.id.txtSoHoaDon);
        TextView txtSocu =(TextView) convertView.findViewById(R.id.txtSocu);
        TextView txtSomoi =(TextView) convertView.findViewById(R.id.txtSomoi);
        TextView txtTieuThu =(TextView) convertView.findViewById(R.id.txtTieuThu);
        TextView txtTienDien =(TextView) convertView.findViewById(R.id.txtTienDien);
        TextView txtTienThue =(TextView) convertView.findViewById(R.id.txtTienThue);
        TextView txtTongTien =(TextView) convertView.findViewById(R.id.txtTongtien);
        Button btnThanhToan = (Button)convertView.findViewById(R.id.btnthanhtoanKH);
        // gán giá trị
        HoaDonBangGhiDien HDBGDien = hoaDonBangGhiDienList.get(position);
        txtNgay.setText(HDBGDien.getThangGhi());
        txtKhachHang.setText(HDBGDien.getTenChuHo());
        txtSoHoaDon.setText(HDBGDien.getMaBangGhiDien());
        txtSocu.setText(HDBGDien.getChiSoCu());
        txtSomoi.setText(HDBGDien.getChiSoMoi());
        txtTieuThu.setText(HDBGDien.getChiSoTieuThu());
        txtTienDien.setText(HDBGDien.getTienDien());
        txtTienThue.setText(HDBGDien.getTienThue());
        txtTongTien.setText(HDBGDien.getThanhTien());
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MaBG = (String) txtSoHoaDon.getText();
                UpdateHD(MaBG);
            }
        });
        return convertView;
    }
    public void UpdateHD(String MaBG) {
        ApiService.apiService.updateHD(MaBG).enqueue(new Callback<HoaDonBangGhiDien>() {
            @Override
            public void onResponse(Call<HoaDonBangGhiDien> call, Response<HoaDonBangGhiDien> response) {
                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<HoaDonBangGhiDien> call, Throwable t) {
            }
        });
    }
}
