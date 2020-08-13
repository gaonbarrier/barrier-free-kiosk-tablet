package io.github.gaonbarrier.easykiosk.tablet.Layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.github.gaonbarrier.easykiosk.tablet.Data.Category;
import io.github.gaonbarrier.easykiosk.tablet.R;
import org.jetbrains.annotations.NotNull;

public class Board extends Fragment {
    private Category category;
    private Activity mActivity;
    private Context context;
    public Board(Activity activity, Context context, Category category){
        this.category = category;
        this.mActivity = activity;
        this.context = context;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board, container, false);
        for (int i = 0; i < category.getItems().size(); i++) {
            Button button = new Button(context, mActivity, category.getItems().get(i));
            LinearLayout con = (LinearLayout) view.findViewById(R.id.cate_fragment);
            con.addView(button);
        }
        return view;
    }
}
