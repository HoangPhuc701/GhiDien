package com.example.appghidien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appghidien.GhiDien;
import com.example.appghidien.HoaDon;
import com.example.appghidien.Model.HoaDonBangGhiDien;
import com.example.appghidien.Model.PhuongXa;
import com.example.appghidien.Model.Tinh;
import com.example.appghidien.R;
import com.example.appghidien.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDonAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<HoaDonBangGhiDien>hoaDonBangGhiDienList ;
    public HoaDonAdapter(Context context, int layout, List<HoaDonBangGhiDien> hoaDonBangGhiDienList) {
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
        TextView txtMaHD =(TextView) convertView.findViewById(R.id.edtMAHOADON);
        TextView txtChuHO =(TextView) convertView.findViewById(R.id.edtCHUHO);
        TextView txtDiaChi =(TextView)convertView.findViewById(R.id.edtDIACHI);
        TextView txtChiSoTieuThu =(TextView) convertView.findViewById(R.id.edtCHISOTIEUTHU);
        TextView txtThanhTien =(TextView) convertView.findViewById(R.id.edtTHANHTIEN);
        TextView txtTrangThai =(TextView) convertView.findViewById(R.id.edtTRANGTHAI);
        Button btnThanhToan = (Button)convertView.findViewById(R.id.btnthanhtoan);
        // gán giá trị
        HoaDonBangGhiDien HDBGDien = hoaDonBangGhiDienList.get(position);
        txtMaHD.setText(HDBGDien.getMaBangGhiDien());
        txtChuHO.setText(HDBGDien.getTenChuHo());
        txtDiaChi.setText(HDBGDien.getSoNha()+","+ HDBGDien.getTenDuong());
        txtChiSoTieuThu.setText(HDBGDien.getChiSoTieuThu()+" Kwh");
        txtThanhTien.setText(HDBGDien.getThanhTien()+" VND");
        txtTrangThai.setText(HDBGDien.getTrangThaiThanhToan());


        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MaBG = (String) txtMaHD.getText();
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
