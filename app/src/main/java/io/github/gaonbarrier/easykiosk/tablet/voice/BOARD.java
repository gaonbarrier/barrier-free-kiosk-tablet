package io.github.gaonbarrier.easykiosk.tablet.voice;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.github.gaonbarrier.easykiosk.tablet.R;
import io.github.gaonbarrier.easykiosk.tablet.VoiceActivity;
import io.github.gaonbarrier.easykiosk.tablet.Data.Category;


public class BOARD extends Fragment {

    private Category category;

    //이 아재들은 ArrayList items에서 하나씩 꺼내옵시다.
    private Activity mVoiceAct;

    public BOARD(Category category) {
        this.category = category;
    }
    // Constructor 에서 mCount 아재의 값이 결정이 나는군.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board, container, false);
        mVoiceAct = VoiceActivity._VoiceActivity;

        for (int i = 0; i < category.getItems().size(); i++) {
            BUTTON_CONTAINER button_container = new BUTTON_CONTAINER(mVoiceAct,category.getItems().get(i));
            LinearLayout con = (LinearLayout) view.findViewById(R.id.cate_fragment);
            con.addView(button_container);
        } //버튼의 개수, 이름을 모두 받아야 가능함.
        return view;
    }


}
