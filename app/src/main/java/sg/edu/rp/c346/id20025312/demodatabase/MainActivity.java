package sg.edu.rp.c346.id20025312.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTask;
    TextView tvResult;
    ListView lvResult;
    ArrayList<Task> al;
    ArrayAdapter<Task> aaResult;
    EditText etName, etDate;

    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTask = findViewById(R.id.btnGetTasks);
        tvResult = findViewById(R.id.tvResults);
        lvResult = findViewById(R.id.lvResult);
        etDate = findViewById(R.id.etDate);
        etName = findViewById(R.id.etName);

        al = new ArrayList<>();
        aaResult = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,al);
        lvResult.setAdapter(aaResult);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertTask(etName.getText().toString(), etDate.getText().toString());

            }
        });

        btnGetTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db2 = new DBHelper((MainActivity.this));
//                al = db2.getTasks(asc);

                aaResult = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, al);

                DBHelper dbh = new DBHelper(MainActivity.this);
                ArrayList<String> tasks = dbh.getTaskContent();

                String text = " ";
                for(int i=0; i<tasks.size(); i++)
                {
                    text += i + ". " + tasks.get(i) + "\n";
                }
                tvResult.setText(text);

                al.clear();
                al.addAll(dbh.getTasks(asc));
                asc = !asc;
                aaResult.notifyDataSetChanged();


            }
        });
    }
}
