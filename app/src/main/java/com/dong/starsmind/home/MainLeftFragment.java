package com.dong.starsmind.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.bookshelf.activity.BookshelfActivity;
import com.dong.starsmind.bookshelf.activity.ReadActivity;

/**
 * Created by zengwendong on 16/11/3.
 */
public class MainLeftFragment extends Fragment implements View.OnClickListener{

    private CardView card_view_bookshelf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_left,null);
        card_view_bookshelf = (CardView) view.findViewById(R.id.card_view_bookshelf);
        card_view_bookshelf.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view_bookshelf:
                BookshelfActivity.startActivity(getActivity());
                break;
            default:
                break;
        }
    }
}
