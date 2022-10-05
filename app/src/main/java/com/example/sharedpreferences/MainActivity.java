package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnXacnhan;
    EditText edtUser,edtPass;
    CheckBox cbRemember;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnXacnhan = (Button) findViewById(R.id.buttonLogin);
        edtUser=(EditText) findViewById(R.id.edit_maSV);
        edtPass=(EditText) findViewById(R.id.password);
        cbRemember =(CheckBox)findViewById(R.id.cb_Save);
        sharedPreferences =getSharedPreferences("data", MODE_PRIVATE);
        edtUser.setText(sharedPreferences.getString("taikhoan",""));
        edtPass.setText(sharedPreferences.getString("matkhau",""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked",false));

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uername =edtUser.getText().toString().trim();
                String pass =edtPass.getText().toString().trim();
                if (uername.equals("abc")&&pass.equals("123")){
                    Toast.makeText(MainActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    if(cbRemember.isChecked()){
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("taikhoan",uername);
                        editor.putString("matkhau",pass);
                        editor.putBoolean("checked",true);
                        editor.commit();
                    }else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Đăng Nhập Không Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}