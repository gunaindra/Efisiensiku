package com.busefisensi.efisiensiku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.busefisensi.efisiensiku.Model.PassengerModel;
import com.busefisensi.efisiensiku.util.DatabaseHandler;
import com.busefisensi.efisiensiku.util.User;


public class RegisterActivity extends AppCompatActivity {

    Toolbar mtoolbar;
    Button btnSimpan;
    TextView tvToolbar;
    EditText etRegNamaDepan;
    EditText etRegNamaBelakang;
    EditText etRegEmail;
    EditText etRegHandphone;
    User user;
    DatabaseHandler handler = new DatabaseHandler(this);
    PassengerModel passengerModel;
//    user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbar = (TextView)findViewById(R.id.tvToolbar);
        tvToolbar.setText("Daftar");
        etRegNamaDepan = (EditText)findViewById(R.id.etRegNamaDepan);
        etRegNamaBelakang = (EditText)findViewById(R.id.etRegNamaBelakang);
        etRegEmail = (EditText)findViewById(R.id.etRegEmail);
        etRegHandphone = (EditText)findViewById(R.id.etRegHandphone);


        btnSimpan = (Button)findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(handler);
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void saveData(final DatabaseHandler dbHandler){
        user = new User(RegisterActivity.this);
        passengerModel = new PassengerModel();
        etRegNamaDepan = (EditText)findViewById(R.id.etRegNamaDepan);
        etRegNamaBelakang = findViewById(R.id.etRegNamaBelakang);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegHandphone = findViewById(R.id.etRegHandphone);

        try {
            String namaDepan = etRegNamaDepan.getText().toString().trim();
            String namaBelakang = etRegNamaBelakang.getText().toString().trim();
            String email = etRegEmail.getText().toString().trim();
            String handphone = etRegHandphone.getText().toString().trim();

            user.setNamaDepan(namaDepan);
            user.setNamaBelakang(namaBelakang);
            user.setEmail(email);
            user.setHandphone(handphone);
            passengerModel.setNama(namaDepan);
            passengerModel.setHandhphone(handphone);

            dbHandler.addRecord(passengerModel);
            user.setIsLogin(true);
            Toast.makeText(RegisterActivity.this, "Berhasil Didaftarkan", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

//    private void saveData(final DatabaseHandler dbHandler){
//        String name = etRegNamaDepan.getText().toString();
//        String handphone = etRegHandphone.getText().toString();
//        Log.d("name", name);
//        PassengerModel passengerModel = new PassengerModel();
//        passengerModel.setNama(name);
//        passengerModel.setHandhphone(handphone);
//
//        dbHandler.addRecord(passengerModel);
//    }

    public static class RegisterPenumpang extends AppCompatActivity {
        EditText etNama;
        EditText etHandphone;
        Button btnSimpan;
        DatabaseHandler handler = new DatabaseHandler(this);
        PassengerModel passengerModel;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_passenger);
            etNama = findViewById(R.id.etRegNama);
            etHandphone = findViewById(R.id.etRegHandphone);

            btnSimpan = findViewById(R.id.btnSimpan);
            btnSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveData(handler);
                    Intent intent = new Intent(RegisterPenumpang.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }

        public void saveData(final DatabaseHandler dbHandler){
            passengerModel = new PassengerModel();

            try {
                String namaDepan = etNama.getText().toString().trim();
                String handphone = etHandphone.getText().toString().trim();

                passengerModel.setNama(namaDepan);
                passengerModel.setHandhphone(handphone);

                dbHandler.addRecord(passengerModel);
                Toast.makeText(RegisterPenumpang.this, "Berhasil Didaftarkan", Toast.LENGTH_SHORT).show();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
