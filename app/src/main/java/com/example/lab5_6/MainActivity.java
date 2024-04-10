package com.example.lab5_6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab5_6.Model.Response_Model;
import com.example.lab5_6.Model.Students;
import com.example.lab5_6.adapter.Recycle_Item_Students;
import com.example.lab5_6.handle.Item_Student_Handle;
import com.example.lab5_6.services.HttpRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Item_Student_Handle {
    private HttpRequest httpRequest;
    private Recycle_Item_Students adapterStudents;
    private RecyclerView rcvStudents;
    private ArrayList<Students> originList;
    private ArrayList<Students> displayList;
    FloatingActionButton btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvStudents = findViewById(R.id.rcv_students);
        btn_add = findViewById(R.id.btn_add);
        httpRequest = new HttpRequest();
        httpRequest.callApi().getListStudents().enqueue(getStudentsAPI);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
    }

    private void getData(ArrayList<Students> list) {
        adapterStudents = new Recycle_Item_Students(this, list, this);
        rcvStudents.setLayoutManager(new LinearLayoutManager(this));
        rcvStudents.setAdapter(adapterStudents);
    }

    private void openDialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View diaglogAdd = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(diaglogAdd);
        Dialog dialog = builder.create();
        dialog.show();

        Button btnAdd = diaglogAdd.findViewById(R.id.btn_add_dialog);
        EditText edt_Masv = diaglogAdd.findViewById(R.id.edt_Masv);
        EditText edt_Hoten = diaglogAdd.findViewById(R.id.edt_Hoten);
        EditText edt_Diem = diaglogAdd.findViewById(R.id.edt_Diem);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masv = edt_Masv.getText().toString();
                String hoten = edt_Hoten.getText().toString();
                String diem = edt_Diem.getText().toString();
                Students students = new Students();

                if (masv.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập mã sv", Toast.LENGTH_SHORT).show();
                    return;
                } else if (hoten.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Họ tên", Toast.LENGTH_SHORT).show();
                    return;
                } else if (diem.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập điểm", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    students.setMasv(masv);
                    students.setHoten(hoten);
                    students.setDiem(diem);
                    httpRequest.callApi().addStudnets(students).enqueue(responseStudentsAPI);
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }

    Callback<Response_Model<ArrayList<Students>>> getStudentsAPI = new Callback<Response_Model<ArrayList<Students>>>() {
        @Override
        public void onResponse(Call<Response_Model<ArrayList<Students>>> call, Response<Response_Model<ArrayList<Students>>> response) {
            if (response.isSuccessful()) {

                if (response.body().getStatus() == 200) {
                    ArrayList<Students> list = response.body().getData();
                    Log.d("List", "onResponse: " + list);
                    getData(list);

                }
            }
        }

        @Override
        public void onFailure(Call<Response_Model<ArrayList<Students>>> call, Throwable t) {
            Log.d(">>>GETLIST", "onFailure: " + t.getMessage());
        }
    };
    Callback<Response_Model<Students>> responseStudentsAPI = new Callback<Response_Model<Students>>() {
        @Override
        public void onResponse(Call<Response_Model<Students>> call, Response<Response_Model<Students>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    httpRequest.callApi().getListStudents().enqueue(getStudentsAPI);
                }
            }
        }

        @Override
        public void onFailure(Call<Response_Model<Students>> call, Throwable t) {
            Log.d(">>>GESTLIST", "onFailure: " + t.getMessage());
        }
    };

    @Override
    public void Delete(String id) {
        httpRequest.callApi().deleteStudentsById(id).enqueue(responseStudentsAPI);
    }

    @Override
    public void Update(String id, Students students) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edt_up_Masv = view.findViewById(R.id.edt_up_Masv);
        EditText edt_up_Hoten = view.findViewById(R.id.edt_up_Hoten);
        EditText edt_up_Diem = view.findViewById(R.id.edt_up_Diem);
        Button btnUpdate = view.findViewById(R.id.btn_update_dialog);

        edt_up_Masv.setText(students.getMasv());
        edt_up_Hoten.setText(students.getHoten());
        edt_up_Diem.setText(students.getDiem());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = students.getId();
                String masv = edt_up_Masv.getText().toString();
                String hoten = edt_up_Hoten.getText().toString();
                String diem = edt_up_Diem.getText().toString();

                if (masv.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập mã sv", Toast.LENGTH_SHORT).show();
                    return;
                } else if (hoten.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Họ tên", Toast.LENGTH_SHORT).show();
                    return;
                } else if (diem.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập điểm", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    students.setMasv(masv);
                    students.setHoten(hoten);
                    students.setDiem(diem);
                    httpRequest.callApi().updateStudentsById(id, students).enqueue(responseStudentsAPI);
                    Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }
}