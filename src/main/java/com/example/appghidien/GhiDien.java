package com.example.appghidien;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appghidien.Model.CapDienAp;
import com.example.appghidien.Model.DongHo;
import com.example.appghidien.Model.DongHoDien;
import com.example.appghidien.Model.GiaDienCo;
import com.example.appghidien.Model.GiaDienTu;
import com.example.appghidien.Model.HinhThucSD;
import com.example.appghidien.Model.HoaDonBangGhiDien;
import com.example.appghidien.Model.HoaDonTienDien;
import com.example.appghidien.Model.LoaiDH;
import com.example.appghidien.Model.NhanVien;
import com.example.appghidien.Model.PhuongXa;
import com.example.appghidien.Model.QuanHuyen;
import com.example.appghidien.Model.Tinh;
import com.example.appghidien.api.ApiService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GhiDien extends AppCompatActivity {
    private Spinner spnTinhTP, spnQH, spnPX,spnMaDH;
    private TextView txtsocu,txtsomoi,txttieuthu,txtthanhtien,txtCaoDiem,txtThapDiem,txtBinhThuong;
    private EditText edtsocu,edtsomoi,edttieuthu,edtthanhtien,edtCaoDiem,edtThapDiem,edtBinhThuong;
    private Switch SCoDT;
    private Button btnAddHD;
    List<Tinh> tplist = new ArrayList<>();
    List<QuanHuyen> qhlist = new ArrayList<>();
    List<PhuongXa> pxlist = new ArrayList<>();
    List<DongHoDien> dhlist = new ArrayList<>();
    List<DongHo> madhlist = new ArrayList<>();
    List<HoaDonBangGhiDien> hoadonlist = new ArrayList<>();
    List<LoaiDH> loaiDHList = new ArrayList<>();
    List<HinhThucSD> hinhthucsdlist = new ArrayList<>();
    List<CapDienAp> capDienApList = new ArrayList<>();
    List<GiaDienCo> giaCoList = new ArrayList<>();
    List<GiaDienTu>giaDTList = new ArrayList<>();
    String MaTinh,MaQuan,MaPhuong,MaDH;
    int soMoi,soCu,tieuthu,tempt,binhthuong,caodiem,thapdiem;
    double thanhtien=0;
    double tiendien=0;
    double tienthue=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ghi_dien);
        spnTinhTP = findViewById(R.id.spinner_TinhTPP);
        spnQH = findViewById(R.id.spinner_QHH);
        spnPX = findViewById(R.id.spinner_PXX);
        spnMaDH=findViewById(R.id.spinner_MaDH);
        edtsocu=findViewById(R.id.edtChiSoCu);
        edtsomoi=findViewById(R.id.edtChiSoMoi);
        edttieuthu=findViewById(R.id.edtChiSoTieuThu);
        edtthanhtien=findViewById(R.id.edtThanhTien);
        btnAddHD=findViewById(R.id.btnAddHoaDon);
        edtCaoDiem=findViewById(R.id.edtCaoDiem);
        edtThapDiem=findViewById(R.id.edtThapDiem);
        edtBinhThuong=findViewById(R.id.edtBinhThuong);
        SCoDT = findViewById(R.id.nutCoDT);
        txtsocu = findViewById(R.id.tv_socu);
        txtsomoi = findViewById(R.id.tv_somoi);
        txttieuthu = findViewById(R.id.tv_sotieuthu);
        txtthanhtien = findViewById(R.id.tv_tien);
        txtCaoDiem = findViewById(R.id.tv_caodiem);
        txtThapDiem = findViewById(R.id.tv_thapdiem);
        txtBinhThuong = findViewById(R.id.tv_binhthuong);


        CallHinhThuc();
        CallCapDienAp();
        CallLoaiDH();
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
                CallDH(MaPhuong);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spnMaDH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DongHoDien selectedDH = (DongHoDien) parent.getItemAtPosition(position);
                MaDH = selectedDH.getMaDongHoDien();
                Toast.makeText(getApplicationContext(), "Mã DH: " + MaDH, Toast.LENGTH_SHORT).show();
                CallSoCu(MaDH);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtBinhThuong.setVisibility(View.GONE);
        txtBinhThuong.setVisibility(View.GONE);
        edtThapDiem.setVisibility(View.GONE);
        txtThapDiem.setVisibility(View.GONE);
        edtCaoDiem.setVisibility(View.GONE);
        txtCaoDiem.setVisibility(View.GONE);

        edtBinhThuong.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtThapDiem.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtCaoDiem.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtsomoi.setInputType(InputType.TYPE_CLASS_NUMBER);

        SCoDT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Nếu Switch được bật (isChecked = true), cho phép nhập dữ liệu vào EditText
                // và bật tính năng android:focusable
                if (isChecked) {
                    edtBinhThuong.setVisibility(View.VISIBLE);
                    txtBinhThuong.setVisibility(View.VISIBLE);
                    edtThapDiem.setVisibility(View.VISIBLE);
                    txtThapDiem.setVisibility(View.VISIBLE);
                    edtCaoDiem.setVisibility(View.VISIBLE);
                    txtCaoDiem.setVisibility(View.VISIBLE);

                    //edtThapDiem.setFocusable(true);
                    edtThapDiem.setEnabled(true); // Bật tính năng nhập dữ liệu
                    edtThapDiem.setTextColor(Color.BLACK); // Thiết lập màu chữ cho EditText khi cho phép chỉnh sửa
                    //edtBinhThuong.setFocusable(true);
                    edtBinhThuong.setEnabled(true);
                    edtBinhThuong.setTextColor(Color.BLACK);
                    //edtCaoDiem.setFocusable(true);
                    edtCaoDiem.setEnabled(true);
                    edtCaoDiem.setTextColor(Color.BLACK);
                } else {
                    edtBinhThuong.setVisibility(View.GONE);
                    txtBinhThuong.setVisibility(View.GONE);
                    edtThapDiem.setVisibility(View.GONE);
                    txtThapDiem.setVisibility(View.GONE);
                    edtCaoDiem.setVisibility(View.GONE);
                    txtCaoDiem.setVisibility(View.GONE);

                    edtBinhThuong.setText("");
                    edtThapDiem.setText("");
                    edtCaoDiem.setText("");

                    //edtThapDiem.setFocusable(false);
                    edtThapDiem.setEnabled(false); // Tắt tính năng nhập dữ liệu
                    edtThapDiem.setTextColor(Color.GRAY); // Thiết lập màu chữ cho EditText khi không cho phép chỉnh sửa
                    //edtBinhThuong.setFocusable(false);
                    edtBinhThuong.setEnabled(false);
                    edtBinhThuong.setTextColor(Color.GRAY);
                    //edtCaoDiem.setFocusable(false);
                    edtCaoDiem.setEnabled(false);
                    edtCaoDiem.setTextColor(Color.GRAY);
                }
            }
        });

        edtsomoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    soMoi = Integer.parseInt(edtsomoi.getText().toString());
                    soCu = Integer.parseInt(edtsocu.getText().toString());
                    List<DongHo> dh1 = CallAllDH(MaDH);
                    for (DongHo a : dh1) {
                        String MaGia = a.getMaHinhThuc().concat("-").concat(a.getMaCapDienAp());
                        if (a.getMaLoaiDH().equals("CO")) {
                            CallGiaCo(MaGia);
                            tieuthu = soMoi - soCu;
                            // Cập nhật edt của tiêu thụ
                            edttieuthu.setText(String.valueOf(tieuthu));
                            if (tieuthu <= 50) {//tiêu thụ nhỏ hoặc bằng 50
                                tiendien = tieuthu * giaCoList.get(0).getBac1();
                                tienthue = tiendien * 0.1;
                                thanhtien= tiendien + tienthue;
                                edtthanhtien.setText(String.valueOf(thanhtien));
                            } else if (tieuthu > 50 && tieuthu <= 100) {
                                tempt = tieuthu - 50;
                                tiendien = 50 * giaCoList.get(0).getBac1() + tempt * giaCoList.get(0).getBac2();
                                tienthue = tiendien *0.1;
                                thanhtien= tiendien + tienthue;
                                edtthanhtien.setText(String.valueOf(thanhtien));
                            } else if (tieuthu > 100 && tieuthu <= 200) {
                                tempt = tieuthu - 100;
                                tiendien = 50 * giaCoList.get(0).getBac1() +
                                        50 * giaCoList.get(0).getBac2() +
                                        tempt * giaCoList.get(0).getBac3();
                                tienthue = tiendien *0.1;
                                thanhtien= tiendien + tienthue;
                                edtthanhtien.setText(String.valueOf(thanhtien));
                            } else if (tieuthu > 200 && tieuthu <= 300) {
                                tempt = tieuthu - 200;
                                tiendien = 50 * giaCoList.get(0).getBac1() +
                                        50 * giaCoList.get(0).getBac2() +
                                        100 * giaCoList.get(0).getBac3() +
                                        tempt * giaCoList.get(0).getBac4();
                                tienthue = tiendien *0.1;
                                thanhtien= tiendien + tienthue;
                                edtthanhtien.setText(String.valueOf(thanhtien));
                            } else if(tieuthu > 300 && tieuthu <= 400)
                            {
                                tempt = tieuthu - 300;
                                tiendien = 50 * giaCoList.get(0).getBac1() +
                                        50 * giaCoList.get(0).getBac2() +
                                        100 * giaCoList.get(0).getBac3()+
                                        100 * giaCoList.get(0).getBac4()+
                                        tempt * giaCoList.get(0).getBac5();
                                tienthue = tiendien *0.1;
                                thanhtien= tiendien + tienthue;
                                edtthanhtien.setText(String.valueOf(thanhtien));
                            }else if(tieuthu > 400)
                            {
                                tempt = tieuthu - 400;
                                tiendien = 50 * giaCoList.get(0).getBac1() +
                                        50 * giaCoList.get(0).getBac2() +
                                        100 * giaCoList.get(0).getBac3()+
                                        100 * giaCoList.get(0).getBac4()+
                                        100 * giaCoList.get(0).getBac5()+
                                        tempt * giaCoList.get(0).getBac6();
                                tienthue = tiendien *0.1;
                                thanhtien= tiendien + tienthue;
                                edtthanhtien.setText(String.valueOf(thanhtien));
                            }
                        }
                        else if(a.getMaLoaiDH().equals("DT"))
                        {
                            binhthuong = Integer.parseInt(edtBinhThuong.getText().toString());
                            caodiem = Integer.parseInt(edtBinhThuong.getText().toString());
                            thapdiem = Integer.parseInt(edtThapDiem.getText().toString());

                            CallGiaDT(MaGia);
                            tieuthu = binhthuong + caodiem + thapdiem;
                            edttieuthu.setText(String.valueOf(tieuthu));
                            tiendien = binhthuong * giaDTList.get(0).getBinhThuong() +
                                        caodiem * giaDTList.get(0).getCaoDiem() +
                                        thapdiem * giaDTList.get(0).getThapDiem();
                            tienthue = tiendien * 0.1;//thuế 10%
                            thanhtien= tiendien + tienthue;
                            edtthanhtien.setText(String.valueOf(thanhtien));
                            }

                    }
                }catch(Exception e) {}
            }
        });

        btnAddHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                Date currentDate = new Date();
                String MaBG = "G".concat(String.valueOf(random.nextInt(1000)));
                String MaHD = "HD".concat(String.valueOf(random.nextInt(1000)));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String ThangGhi = formatter.format(currentDate);
                String TrangThai = "Chưa Thanh Toán";
                ApiService.apiService.createHoaDon(MaBG,MaHD,ThangGhi,soCu,soMoi,tieuthu,tiendien,tienthue,thanhtien,TrangThai,MaDH).enqueue(new Callback<HoaDonTienDien>() {
                    @Override
                    public void onResponse(Call<HoaDonTienDien> call, Response<HoaDonTienDien> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(GhiDien.this, "Đã lưu hóa đơn", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<HoaDonTienDien> call, Throwable t) {
                        Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
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
                    ArrayAdapter<Tinh> adapterTP = new ArrayAdapter<>(GhiDien.this, android.R.layout.simple_spinner_dropdown_item, tplist);
                    spnTinhTP.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<Tinh>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<QuanHuyen> adapterTP = new ArrayAdapter<>(GhiDien.this, android.R.layout.simple_spinner_dropdown_item, qhlist);
                    spnQH.setAdapter(adapterTP);
                }
            }

            @Override
            public void onFailure(Call<List<QuanHuyen>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<PhuongXa> adapterPX = new ArrayAdapter<>(GhiDien.this, android.R.layout.simple_spinner_dropdown_item, pxlist);
                    spnPX.setAdapter(adapterPX);
                }
            }

            @Override
            public void onFailure(Call<List<PhuongXa>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallDH(String MaPX) {
        ApiService.apiService.getDH(MaPX).enqueue(new Callback<List<DongHoDien>>() {
            @Override
            public void onResponse(Call<List<DongHoDien>> call, Response<List<DongHoDien>> response) {
                if (response.isSuccessful()) {
                    List<DongHoDien> lists = response.body();
                    dhlist.clear();
                    dhlist.addAll(lists);
                    ArrayAdapter<DongHoDien> adapterPX = new ArrayAdapter<>(GhiDien.this, android.R.layout.simple_spinner_dropdown_item, dhlist);
                    spnMaDH.setAdapter(adapterPX);
                }
            }

            @Override
            public void onFailure(Call<List<DongHoDien>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallSoCu(String MaDH) {
        ApiService.apiService.getMaxDH(MaDH).enqueue(new Callback<List<HoaDonBangGhiDien>>() {
            @Override
            public void onResponse(Call<List<HoaDonBangGhiDien>> call, Response<List<HoaDonBangGhiDien>> response) {
                if (response.isSuccessful()) {
                    List<HoaDonBangGhiDien> lists = response.body();
                    hoadonlist.clear();
                    hoadonlist.addAll(lists);
                    edtsocu.setText(hoadonlist.get(0).getChiSoMoi());
                }
            }

            @Override
            public void onFailure(Call<List<HoaDonBangGhiDien>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<DongHo> CallAllDH(String MaDH) {
        ApiService.apiService.getAllDH(MaDH).enqueue(new Callback<List<DongHo>>() {
            @Override
            public void onResponse(Call<List<DongHo>> call, Response<List<DongHo>> response) {
                if (response.isSuccessful()) {
                    List<DongHo> lists = response.body();
                    madhlist.clear();
                    madhlist.addAll(lists);
                }
            }

            @Override
            public void onFailure(Call<List<DongHo>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        return madhlist;
    }

    public void CallHinhThuc() {
        ApiService.apiService.getHinhThuc().enqueue(new Callback<List<HinhThucSD>>() {
            @Override
            public void onResponse(Call<List<HinhThucSD>> call, Response<List<HinhThucSD>> response) {
                if (response.isSuccessful()) {
                    List<HinhThucSD> lists = response.body();
                    hinhthucsdlist.clear();
                    hinhthucsdlist.addAll(lists);
                }
            }

            @Override
            public void onFailure(Call<List<HinhThucSD>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
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
                }
            }

            @Override
            public void onFailure(Call<List<CapDienAp>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
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
                }
            }

            @Override
            public void onFailure(Call<List<LoaiDH>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallGiaCo(String MaGiaCo) {
        ApiService.apiService.getGiaCo(MaGiaCo).enqueue(new Callback<List<GiaDienCo>>() {
            @Override
            public void onResponse(Call<List<GiaDienCo>> call, Response<List<GiaDienCo>> response) {
                if (response.isSuccessful()) {
                    List<GiaDienCo> lists = response.body();
                    giaCoList.clear();
                    giaCoList.addAll(lists);
                }
            }

            @Override
            public void onFailure(Call<List<GiaDienCo>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CallGiaDT(String MaGiaDT) {
        ApiService.apiService.getGiaDT(MaGiaDT).enqueue(new Callback<List<GiaDienTu>>() {
            @Override
            public void onResponse(Call<List<GiaDienTu>> call, Response<List<GiaDienTu>> response) {
                if (response.isSuccessful()) {
                    List<GiaDienTu> lists = response.body();
                    giaDTList.clear();
                    giaDTList.addAll(lists);
                }
            }

            @Override
            public void onFailure(Call<List<GiaDienTu>> call, Throwable t) {
                Toast.makeText(GhiDien.this, "API ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
