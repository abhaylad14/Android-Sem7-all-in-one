package com.example.activitylifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileActivity extends AppCompatActivity {
    EditText txtContent;
    Button btnRead, btnWrite, btnWriteExternal, btnReadExternal;
    private static String FileName = "MyFile.txt";
    private static String FilePath = "MyStorage";
    File MyExternalFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ControlInitialization();
        Buttonclicks();


    }

    private void ControlInitialization(){
        txtContent = findViewById(R.id.txtContent);
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        btnReadExternal = findViewById(R.id.btnReadExternal);
        btnWriteExternal = findViewById(R.id.btnWriteExternal);
        IsExternalStorageAvailable();
        IsExternalStorageReadOnly();

        if(IsExternalStorageAvailable() && !IsExternalStorageReadOnly())
        {
            MyExternalFile = new File(getExternalFilesDir(FilePath),FileName);
        }

    }

    private void Buttonclicks()
    {
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = openFileOutput(FileName,MODE_APPEND);
                    OutputStreamWriter writer = new OutputStreamWriter(fos);

                    writer.write(txtContent.getText().toString());
                    writer.flush();
                    writer.close();


                    txtContent.setText("");
                    Toast.makeText(getApplicationContext(), FileName +
                            " created successfully",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String line,FileContent = "";
                try {
                    FileInputStream fis = openFileInput(FileName);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    while((line = reader.readLine())!= null)
                    {
                        FileContent += line;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                txtContent.setText(FileContent);
                Toast.makeText(getApplicationContext(), FileName +
                        "successfully retrieved",Toast.LENGTH_LONG).show();
            }
        });

        btnWriteExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos =new FileOutputStream(MyExternalFile,true);
                    fos.write(txtContent.getText().toString().getBytes());
                    fos.flush();
                    fos.close();
                    txtContent.setText("");

                   /* OutputStreamWriter writer = new OutputStreamWriter(fos);
                    writer.write(txtContent.getText().toString());*/
                    Toast.makeText(getApplicationContext(), FileName + " created successfully",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnReadExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String line, FileContent ="";
                try {
                    FileInputStream fis = new FileInputStream(MyExternalFile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    while((line = br.readLine()) != null)
                    {
                        FileContent += line;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txtContent.setText(FileContent);
            }
        });
    }

    private boolean IsExternalStorageAvailable()
    {
        String ExtState = Environment.getExternalStorageState();
        Toast.makeText(getApplicationContext(), ExtState, Toast.LENGTH_LONG).show();
        if(Environment.MEDIA_MOUNTED.equals(ExtState))
        {
            return true;
        }
        return false;
    }

    private boolean IsExternalStorageReadOnly()
    {
        String ExtState = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(ExtState))
        {
            return true;
        }
        return false;
    }
}