package com.sznews.news.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sznews.news.R;

/**
 * Created by qiy on 2018-2-9.
 */

public class CategoryFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public static CategoryFragment newInstance(String param1){
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        TextView content = (TextView) view.findViewById(R.id.fg_tv);
        content.setText(mParam1);
        return view;
    }
}
