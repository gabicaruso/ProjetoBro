package br.edu.insper.al.mayraprl.bro;

        import android.support.v7.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Permission extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        Button btnShowOverlay = findViewById(R.id.show_overlay);
        btnShowOverlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchOverlayService();
                Intent main = new Intent(Permission.this, MainActivity.class);
                startActivity(main);

            }
        });
    }

    private void launchOverlayService() {
        Intent intent = new Intent(Permission.this, OverlayService.class);
        startService(intent);
    }
}
