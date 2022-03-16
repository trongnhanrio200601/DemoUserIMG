package com.example.user_img;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.provider.MediaStore;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class NhanVienAdapter extends BaseAdapter {
    private Context context ;
    private int layout;
    private List<NhanVien> nhanVienList;

    public NhanVienAdapter(Context context, int layout, List<NhanVien> nhanVienList) {
        this.context = context;
        this.layout = layout;
        this.nhanVienList = nhanVienList;
    }

    @Override
    public int getCount() {
        return nhanVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater1.inflate(layout, null);

        //anh xa
        TextView txtMaSo = (TextView) view.findViewById(R.id.textview_MaSo);
        TextView txtHoTen = (TextView) view.findViewById(R.id.textview_HoTen);
        TextView txtGioiTinh = (TextView) view.findViewById(R.id.textview_GioiTinh);
        TextView txtDonVi = (TextView) view.findViewById(R.id.textview_DonVi);

        ImageView imgHinh = (ImageView) view.findViewById(R.id.img_HinhList);

        //gan gia tri
        NhanVien nhanviens = nhanVienList.get(i);

        txtMaSo.setText("Mã số : " +nhanviens.getMaso());
        txtHoTen.setText("Họ tên : "+ nhanviens.getHoten());
        txtDonVi.setText("Đơn vị : "+nhanviens.getDonvi());
        txtGioiTinh.setText("Giới tính : "+nhanviens.getGioitinh());

        imgHinh.setImageBitmap(nhanviens.getImg());

        return view;
    }
}
