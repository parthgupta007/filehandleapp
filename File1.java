package com.example.timeapp;


        import android.os.Bundle;

        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import java.io.BufferedReader;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;


public class File1 extends AppCompatActivity {
    EditText efilename,etext;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file1);
        efilename = (EditText)findViewById(R.id.filename);
        etext = (EditText)findViewById(R.id.ftext);
        textView=(TextView)findViewById(R.id.tv1);
    }

    public void read(View view) {
        try {
            FileInputStream fileInputStream= openFileInput(efilename.getText().toString());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines=bufferedReader.readLine())!=null) {
                stringBuffer.append(lines+"\n");
            }
            textView.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(View view) {
        String Mytextmessage  = etext.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(efilename.getText().toString(),MODE_PRIVATE);
            fileOutputStream.write(Mytextmessage.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Text Saved",Toast.LENGTH_LONG).show();
            etext.setText("");
            efilename.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}