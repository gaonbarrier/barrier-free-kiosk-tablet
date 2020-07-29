package io.github.gaonbarrier.easykiosk.tablet;

import android.content.Intent;
import android.view.View;
import io.github.gaonbarrier.easykiosk.tablet.network.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.github.gaonbarrier.easykiosk.tablet.normal.NormalActivity;
import io.github.gaonbarrier.easykiosk.tablet.network.*;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // NetworkSection nw = new NetworkSection();
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
