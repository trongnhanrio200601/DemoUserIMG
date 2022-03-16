package com.example.user_img;

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

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;

public class MainActivity extends AppCompatActivity {
    ArrayList<NhanVien> nv_list = new ArrayList<>();
    String[] dv_List;
    String donvi;
    Button btnChonAnh;
    ImageView imgAnh;
    NhanVienAdapter adapter2;
    Bitmap anh;
    int count = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChonAnh = findViewById(R.id.Button_ChonAnh);
        imgAnh = findViewById(R.id.img_view);
        EditText et_MaSo = findViewById(R.id.editText_MaSo);
        EditText et_HoTen = findViewById(R.id.editText_Hoten);
        ListView lv_NhanVien = findViewById(R.id.listviewNhanVien);
        RadioGroup rg_GioiTinh = findViewById(R.id.radio_gr);
        RadioButton rb_Nam = findViewById(R.id.radio_Nam);
        RadioButton rb_Nu = findViewById(R.id.radio_Nu);

        Spinner sp_DonVi = findViewById(R.id.spinner_donvi);
        dv_List = getResources().getStringArray(R.array.donvi_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dv_List);
        sp_DonVi.setAdapter(adapter);

        rb_Nam.setChecked(true);
        nv_list = new ArrayList<>();
        //Dua vao listview



        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestpermissions();
                openpicker();
            }
        });

        sp_DonVi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                donvi = dv_List[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button bt_sua = findViewById(R.id.buttonSua);
        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maso = et_MaSo.getText().toString();
                String hoten = et_HoTen.getText().toString();
                String gioitinh = ((RadioButton)findViewById(rg_GioiTinh.getCheckedRadioButtonId())).getText().toString();
                //tao doi tuong nhan vien
                NhanVien nv = new NhanVien( anh ,maso, hoten, gioitinh, donvi);
                nv_list.set(count,nv);
                adapter2.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã cập nhật nhân viên" , Toast.LENGTH_SHORT).show();
            }
        });

        Button bt_Thoat = findViewById(R.id.Button_Thoat);
        bt_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        Button bt_Xoa = findViewById(R.id.buttonXoa);
        bt_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nv_list.remove(count);
                adapter2.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã xóa nhân viên" , Toast.LENGTH_SHORT).show();
            }
        });

        Button bt_them = findViewById(R.id.buttonThem);
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maso = et_MaSo.getText().toString();
                String hoten = et_HoTen.getText().toString();
                String gioitinh = ((RadioButton)findViewById(rg_GioiTinh.getCheckedRadioButtonId())).getText().toString();

                //tao doi tuong nhan vien
                NhanVien nv = new NhanVien( anh ,maso, hoten, gioitinh, donvi);

                //Them Nhan vien


                nv_list.add(nv);
                //Dua vao listview


                adapter2 = new NhanVienAdapter(MainActivity.this,
                        R.layout.dong_nhanvien, nv_list);
                lv_NhanVien.setAdapter(adapter2) ;
            }
        });


        lv_NhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NhanVien nv = nv_list.get(i);
                count = i;
                et_MaSo.setText(nv.getMaso() + "");
                et_HoTen.setText(nv.getHoten() + "");
                // xu ly gioi tinh
                imgAnh.setImageBitmap(nv.getImg());
                if (nv.getGioitinh().equals("Nam"))
                    rb_Nam.setChecked(true);
                else
                    rb_Nu.setChecked(true);
                for (int j = 0; j < dv_List.length; j++)
                    if (dv_List[j].equals(nv.getDonvi()))
                        sp_DonVi.setSelection(j);
            }
        });


    }


    private void requestpermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }

    private void openpicker() {
        TedBottomPicker.OnImageSelectedListener listener = new TedBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imgAnh.setImageBitmap(bitmap);
                    anh = bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(MainActivity.this)
                .setOnImageSelectedListener(listener)
                .create();
        tedBottomPicker.show(getSupportFragmentManager());

    }
}