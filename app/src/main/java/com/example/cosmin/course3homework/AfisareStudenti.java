package com.example.cosmin.course3homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.cosmin.course3homework.Adapter.StudentAdapter;
import com.example.cosmin.course3homework.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class AfisareStudenti extends AppCompatActivity {

    private ListView listViewStudent;
    private static List<Student> listaStudenti = new ArrayList<>();
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisare_studenti);
        db=DBHelper.getInstance(this);

        Intent intent = getIntent();
        Student student = (Student)intent.getSerializableExtra("student");

        db.insertStudent(student);
        listaStudenti =db.getAllStudents();
        listViewStudent=(ListView)findViewById(R.id.lv_student);
        StudentAdapter studentAdapter = new StudentAdapter(listaStudenti, AfisareStudenti.this);
        listViewStudent.setAdapter(studentAdapter);
    }
}
