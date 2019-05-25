package br.edu.insper.al.mayraprl.bro;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowOverlay = findViewById(R.id.show_overlay);
        btnShowOverlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                launchOverlayService();
                
            }
        });
    }

    private void launchOverlayService() {
        Intent intent = new Intent(MainActivity.this, OverlayService.class);
        startService(intent);
    }
}
