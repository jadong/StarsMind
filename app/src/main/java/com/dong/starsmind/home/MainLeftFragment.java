package com.dong.starsmind.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.starsmind.R;
import com.dong.starsmind.historyevent.activity.HistoryEventActivity;
import com.dong.starsmind.tuling.activity.TuLingActivity;
import com.dong.starsmind.news.activity.HealthNewsActivity;
import com.dong.starsmind.todo.activity.TodoListActivity;

/**
 * Created by zengwendong on 16/11/3.
 */
public class MainLeftFragment extends Fragment implements View.OnClickListener{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_left,null);
        view.findViewById(R.id.card_view_history_event).setOnClickListener(this);
        view.findViewById(R.id.card_view_event).setOnClickListener(this);
        view.findViewById(R.id.card_view_health).setOnClickListener(this);
        view.findViewById(R.id.card_view_send).setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view_history_event:
                HistoryEventActivity.startActivity(getActivity());
                break;
            case R.id.card_view_event:
                TodoListActivity.startActivity(getActivity());
                break;
            case R.id.card_view_health:
                HealthNewsActivity.startActivity(getActivity());
                break;
            case R.id.card_view_send:
                TuLingActivity.startActivity(getActivity());
                break;
            default:
                break;
        }
    }
}
