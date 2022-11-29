package com.example.subjectstudent_207ct65664;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DBHandler dbHandler;
    TextView result;
    EditText et_id, et_ho, et_ten, et_class;
    Button add, update, remove, load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Matching();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadStudents();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudents();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeStudents();
            }
        });
    }

    private void addStudent() {
        if(!et_ho.getText().toString().isEmpty() && !et_ten.getText().toString().isEmpty() && !et_class.getText().toString().isEmpty()) {
            //int id = Integer.parseInt(et_id.getText().toString());
            String ho = et_ho.getText().toString();
            String ten = et_ten.getText().toString();
            String lop = et_class.getText().toString();

            Student student = new Student(1, ho, ten, lop);
            long insertId = dbHandler.addHandler(student);

            if (insertId == -1) {
                result.setText("Record already exists");
            } else {
                et_ho.setText("");
                et_ten.setText("");
                et_class.setText("");
                result.setText("Record added");
            }
        } else {
            result.setText("Please fill id and name");
        }
    }

    private void updateStudents() {
        if (!et_id.getText().toString().isEmpty() && !et_ho.getText().toString().isEmpty() && !et_ten.getText().toString().isEmpty() && !et_class.getText().toString().isEmpty()) {
            boolean Result = dbHandler.updateHandler(Integer.parseInt(et_id.getText().toString()), et_ho.getText().toString(), et_ten.getText().toString(), et_class.getText().toString());
            if (Result) {
                et_id.setText("");
                et_ho.setText("");
                et_ten.setText("");
                et_class.setText("");
                result.setText("Record Updated");
            }
            else {
                result.setText("No record found");
            }
        }
        else {
            result.setText("Please fill id and name");
        }
    }

    private void removeStudents() {
        if (!et_id.getText().toString().isEmpty()) {
            boolean Result = dbHandler.removeHandler(Integer.parseInt(et_id.getText().toString()));
            if (Result) {
                et_id.setText("");
                et_ho.setText("");
                et_ten.setText("");
                et_class.setText("");
                result.setText("Record Deleted");
            }
            else {
                result.setText("No record found");
            }
        }
        else {
            result.setText("Please fill correct id");
        }
    }

    private void loadStudents() {
        result.setText(dbHandler.loadHandler());
        et_id.setText("");
        et_ho.setText("");
        et_ten.setText("");
        et_class.setText("");
    }

    private void Matching() {
        result = findViewById(R.id.tvResult);
        et_id = findViewById(R.id.et_id);
        et_ho = findViewById(R.id.et_ho);
        et_ten = findViewById(R.id.et_ten);
        et_class = findViewById(R.id.et_class);
        add = findViewById(R.id.btn_add);
        load = findViewById(R.id.btn_load);
        update = findViewById(R.id.btn_update);
        remove = findViewById(R.id.btn_remove);
        dbHandler = new DBHandler(this);
    }
}