package com.example.appghidien.api;

import com.example.appghidien.Model.CapDienAp;
import com.example.appghidien.Model.DongHo;
import com.example.appghidien.Model.DongHoDien;
import com.example.appghidien.Model.GiaDienCo;
import com.example.appghidien.Model.GiaDienTu;
import com.example.appghidien.Model.HinhThucSD;
import com.example.appghidien.Model.HoaDonBangGhiDien;
import com.example.appghidien.Model.HoaDonTienDien;
import com.example.appghidien.Model.KhachHang;
import com.example.appghidien.Model.LoaiDH;
import com.example.appghidien.Model.Nha;
import com.example.appghidien.Model.NhanVien;
import com.example.appghidien.Model.PhuongXa;
import com.example.appghidien.Model.QuanHuyen;
import com.example.appghidien.Model.Tinh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gSon = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2/").addConverterFactory(GsonConverterFactory.create(gSon))
            .build()
            .create(ApiService.class);
    @GET("api/NhanVien")
    Call<List<NhanVien>> getData();
    @POST("api/NhanVien")
    Call<NhanVien>createNhanVien(@Query("MANV")String MANV,
                                 @Query("TENNHANVIEN")String TenNV,
                                 @Query("NGAYSINH")String NgaySinh,
                                 @Query("CHUCVU")String ChucVu,
                                 @Query("MATKHAU")String MatKhau);
    @GET("api/TinhTP")
    Call<List<Tinh>>getTinhTP();
    @GET("api/QuanHuyen")
    Call<List<QuanHuyen>>getQuanHuyen(@Query("MaTinhTP")String MaTinhTP);
    @GET("api/PhuongXa")
    Call<List<PhuongXa>>getPhuongXa(@Query("MaQuan")String MaQuan);
    @GET("api/Nha")
    Call<List<Nha>>getNha(@Query("MaPhuongXa")String MaPhuongXa);
    @POST("api/Nha")
    Call<Nha>createNha(@Query("MANHA")String MaNha,
                       @Query("SONHA")String SoNha,
                       @Query("TENDUONG")String TenDuong,
                       @Query("MAPHUONGXA")String MaPhuongXa,
                       @Query("TENCHUHO")String TenChuHo);
    @GET("api/DongHoDien")
    Call<List<DongHoDien>>getDH(@Query("MaPX")String MaPhuongXa);
    @GET("api/DongHoDien/GetAllDH")
    Call<List<DongHo>>getAllDH(@Query("MaDH")String MaDH);

    @POST("api/DongHoDien")
    Call<DongHoDien>createDH(@Query("MADONGHODIEN")String MaDongHoDien,
                             @Query("MAHINHTHUC")String MaHinhThuc,
                             @Query("MACAPDIENAP")String MaCapDienAp,
                             @Query("MANHA")String MaNha,
                             @Query("MALOAIDH")String MaLoai,
                             @Query("DIACHI")String DiaChi);

    @GET("api/HoaDonBangGhiDien")
    Call<List<HoaDonBangGhiDien>>getHoaDon(@Query("MaPhuongXa")String MaPhuongXa);
    @GET("api/HoaDonBangGhiDien/HoaDonKH")
    Call<List<HoaDonBangGhiDien>>getHoaDonKH(@Query("MaNha")String MaNha);
    @GET("api/HoaDonBangGhiDien/GetMaxMaDH")
    Call<List<HoaDonBangGhiDien>>getMaxDH(@Query("MaDH")String MaDH);
    @POST("api/HoaDonBangGhiDien")
    Call<HoaDonTienDien>createHoaDon(@Query("MaBangGhi")String MaBangGhi,
                                     @Query("MaHD")String MaHD,
                                     @Query("ThangGhi")String ThangGhi,
                                     @Query("ChiSoCu")int ChiSoCu,
                                     @Query("ChiSoMoi")int ChiSoMoi,
                                     @Query("ChiSoTieuThu")int SoTieuThu,
                                     @Query("TienDien")double TienDien,
                                     @Query("TienThue")double TienThue,
                                     @Query("ThanhTien")double ThanhTien,
                                     @Query("TrangThai")String TrangThai,
                                     @Query("MaDH")String MaDH);

    @GET("api/HinhThucSD")
    Call<List<HinhThucSD>>getHinhThuc();
    @GET("api/CapDienAp")
    Call<List<CapDienAp>>getCapDienAp();
    @GET("api/LoaiDH")
    Call<List<LoaiDH>>getLoaiDH();

    @GET("api/GiaCo")
    Call<List<GiaDienCo>>getGiaCo(@Query("MaGiaCo")String MaGiaCo);

    @GET("api/GiaDienTu")
    Call<List<GiaDienTu>>getGiaDT(@Query("MaGiaDt")String MaGiaDT);
    @GET("api/KhachHang")
    Call<List<KhachHang>>getKhachHang();

    @POST("api/KhachHang")
    Call<KhachHang>createKH(@Query("MaKH") String MaKH,
                            @Query("TenKh") String TenKh,
                            @Query("Sdt")String Sdt,
                            @Query("MatKhau")String MatKhau,
                            @Query("MaNha")String MaNha);

    @POST("api/HoaDonBangGhiDien/UpdateHD")
    Call<HoaDonBangGhiDien>updateHD(@Query("MaBG") String MaBG);

}
