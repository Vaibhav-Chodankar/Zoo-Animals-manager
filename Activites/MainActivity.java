package com.example.zoo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    ListView animalsList;
    Button addZooAnimals;
    EditText newAnimal;
    ArrayList<String> zooAnimals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        zooAnimals.add("Tiger");
        zooAnimals.add("Lion");
        zooAnimals.add("Aligator");
        newAnimal = findViewById(R.id.etNewAnimal);
        addZooAnimals = findViewById(R.id.btnAdd);
        animalsList = findViewById(R.id.animalsList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, zooAnimals);
        animalsList.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        animalsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                builder.setMessage("Do you want to delete " + zooAnimals.get(i) + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, zooAnimals.get(i) + " deleted!", Toast.LENGTH_SHORT).show();
                        zooAnimals.remove(zooAnimals.get(i));
                        animalsList.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        addZooAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animal = newAnimal.getText().toString();
                if(!newAnimal.equals("")){
                    zooAnimals.add(animal);
                    animalsList.setAdapter(adapter);
                    newAnimal.setText("");
                }
            }
        });
    }

}