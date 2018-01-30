package com.sznews.news.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sznews.news.R;

/**
 * Created by qiy on 2018-1-23.
 */

public class NewsGovernmentFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, null);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("政务号");
        return view;
    }
}
