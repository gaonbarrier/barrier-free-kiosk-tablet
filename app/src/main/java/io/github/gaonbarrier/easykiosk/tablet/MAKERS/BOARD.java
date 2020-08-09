package io.github.gaonbarrier.easykiosk.tablet.MAKERS;

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
import io.github.gaonbarrier.easykiosk.tablet.normal.NormalActivity;


public class BOARD extends Fragment {
    private String[] menu = {"아메리카노", "녹차", "라떼", "는", "말이야"}; //for Test. Do Texts go well?
    //이 아재들은 ArrayList items에서 하나씩 꺼내옵시다.
    int mCount; //count가 불러와진 걸로 봐선 String(이름)같은 것도 Contents안에서 불러올 수 있을 거임. 이거랑 비슷한 방법 사용 ㄱㄱ
    // 이 아재의 목적이 뭔진 모르겠다만 일단 짜증나게 생겼으니 안쓰고싶음
    private Activity mNormalAct;

    public BOARD(int count) {
        this.mCount = count;
    }
    // Constructor 에서 mCount 아재의 값이 결정이 나는군.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board, container, false);
        mNormalAct = NormalActivity._NormalActivity;

        for (int i = 0; i < mCount; i++) {
            BUTTON_CONTAINER button_container = new BUTTON_CONTAINER(mNormalAct, menu[i], i);

            LinearLayout con = (LinearLayout) view.findViewById(R.id.cate_fragment);
            con.addView(button_container);
        } //버튼의 개수, 이름을 모두 받아야 가능함.


        //짱구를 살짝 굴려볼까
        /*
        * ㅋㅋㅋ 모르겠다 ㅅㅂ
        * */



        return view;
    }
}
