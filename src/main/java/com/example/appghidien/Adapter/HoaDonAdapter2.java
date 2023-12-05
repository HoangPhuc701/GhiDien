package com.example.appghidien.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.appghidien.Model.HoaDonBangGhiDien;
import com.example.appghidien.R;
import com.example.appghidien.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoaDonAdapter2 extends BaseAdapter {

    private Context context;
    private int layout;
    private List<HoaDonBangGhiDien>hoaDonBangGhiDienList ;
    public HoaDonAdapter2(Context context, int layout, List<HoaDonBangGhiDien> hoaDonBangGhiDienList) {
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
        return null;
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
        TextView txtMaHD =(TextView) convertView.findViewById(R.id.edtMAHOADON2);
        TextView txtChuHO =(TextView) convertView.findViewById(R.id.edtCHUHO2);
        TextView txtDiaChi =(TextView)convertView.findViewById(R.id.edtDIACHI2);
        TextView txtChiSoTieuThu =(TextView) convertView.findViewById(R.id.edtCHISOTIEUTHU2);
        TextView txtThanhTien =(TextView) convertView.findViewById(R.id.edtTHANHTIEN2);
        TextView txtTrangThai =(TextView) convertView.findViewById(R.id.edtTRANGTHAI2);
        // gán giá trị
        HoaDonBangGhiDien HDBGDien = hoaDonBangGhiDienList.get(position);
        txtMaHD.setText(HDBGDien.getMaBangGhiDien());
        txtChuHO.setText(HDBGDien.getTenChuHo());
        txtDiaChi.setText(HDBGDien.getSoNha()+","+ HDBGDien.getTenDuong());
        txtChiSoTieuThu.setText(HDBGDien.getChiSoTieuThu()+" Kwh");
        txtThanhTien.setText(HDBGDien.getThanhTien()+" VND");
        txtTrangThai.setText(HDBGDien.getTrangThaiThanhToan());


        return convertView;
    }
}
