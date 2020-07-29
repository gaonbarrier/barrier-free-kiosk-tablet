package io.github.gaonbarrier.easykiosk.tablet;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.github.gaonbarrier.easykiosk.tablet.normal.NormalActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view)
    {
        Intent intent = new Intent(this, NormalActivity.class);
        startActivity(intent);
    }
    public void onClick1(View view)
    {
        Intent intent = new Intent(this, SoundActivity.class);
        startActivity(intent);
    }
    public void onClick2(View view)
    {
        Intent intent = new Intent(this, PracticeActivity.class);
        startActivity(intent);
    }
}
