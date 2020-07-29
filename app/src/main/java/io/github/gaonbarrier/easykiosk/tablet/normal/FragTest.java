package io.github.gaonbarrier.easykiosk.tablet.normal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.github.gaonbarrier.easykiosk.tablet.R;

public class FragTest extends Fragment {
    private String testString;
    
    // 생성자. 테스트를 위해 문자열 입력받음
    public FragTest(String test) {
        testString = test;
    }
    
    
    // 입력받은 문자열을 텍스트뷰에 넣어 출력해주는 용도
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        TextView textView = view.findViewById(R.id.tv1);
        textView.setText(testString);
        return view;
    }
}
