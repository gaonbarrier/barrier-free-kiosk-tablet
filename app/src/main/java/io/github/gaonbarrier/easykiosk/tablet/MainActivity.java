package io.github.gaonbarrier.easykiosk.tablet;

import io.github.gaonbarrier.easykiosk.tablet.network.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.github.gaonbarrier.easykiosk.tablet.db.*;

public class MainActivity extends AppCompatActivity {
    private Receiver Receiver;
    private Sender Sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Receiver = new Receiver();
        Sender = new Sender();
        itemDBOpenHelper itemDBOpenHelper = new itemDBOpenHelper(this);
        optionDBOpenHelper optionDBOpenHelper = new optionDBOpenHelper(this);
        Receiver.setItemDBOpenHelper(itemDBOpenHelper);
        Receiver.setOptionDBOpenHelper(optionDBOpenHelper);

    }
}
