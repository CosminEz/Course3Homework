package com.example.cosmin.course3homework;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cosmin.course3homework.Adapter.StudentAdapter;
import com.example.cosmin.course3homework.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    private EditText nume;
    private EditText prenume;
    private EditText email;
    private EditText telefon;
    private RadioGroup grupUniversitati;
    private RadioButton universitate;
    private Button salveazaButton;
    private ListView listViewStudent;
    private List<Student> listaStudenti;
    private ImageView listViewCloseButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nume=(EditText)findViewById(R.id.Nume);
        prenume=(EditText)findViewById(R.id.Prenume);
        email=(EditText)findViewById(R.id.Email);
        telefon=(EditText)findViewById(R.id.Telefon);
        grupUniversitati=(RadioGroup)findViewById(R.id.grupUniversitati);
        salveazaButton=(Button)findViewById(R.id.salveaza);
        listViewStudent=(ListView)findViewById(R.id.lv_student);
        listViewCloseButton=(ImageView)findViewById(R.id.im_close_button);

        grupUniversitati.setOnCheckedChangeListener(this);

        listaStudenti=new ArrayList<>();
        listaStudenti.add(new Student("Filote","Cosmin", "Universitatea din Bucuresti","0741048694"));
        listaStudenti.add(new Student("Filote","Marcel", "Poli","0741048694"));
        listaStudenti.add(new Student("Filote","Elena", "Universitatea din Pitesti","0741048694"));
        listaStudenti.add(new Student("Budai","Denisa","Unibuc","0741048694"));
        listaStudenti.add(new Student("Filote","Ciprian", "Galati","0741048694"));



        salveazaButton.setOnClickListener(this);
        listViewCloseButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        if(v == salveazaButton){
            StudentAdapter studentAdapter = new StudentAdapter(listaStudenti, MainActivity.this);
            listViewStudent.setAdapter(studentAdapter);
            listViewStudent.setVisibility(View.VISIBLE);
            listViewCloseButton.setVisibility(View.VISIBLE);
            Toast.makeText(this,"ai apasat pe salveaza",Toast.LENGTH_LONG).show();
        }
        if(v == listViewCloseButton)
        {
            listViewStudent.setVisibility(View.GONE);
            listViewCloseButton.setVisibility(View.GONE);
        }

    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        universitate=(RadioButton)findViewById(checkedId);

    }
}
