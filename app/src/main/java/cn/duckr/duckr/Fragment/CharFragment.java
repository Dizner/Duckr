package cn.duckr.duckr.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cn.duckr.duckr.R;

/**
 * Created by Dizner on 2016/11/8.
 */

public class CharFragment extends Fragment {
    private ListView char_main_listview;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.char_main_fragment,container,false);
            char_main_listview= (ListView) view.findViewById(R.id.char_main_listview);

        }
        return view;
    }
}
