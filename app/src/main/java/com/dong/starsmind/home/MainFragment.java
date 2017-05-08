package com.dong.starsmind.home;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.dong.starsmind.R;
import com.dong.starsmind.common.activity.CommonFragmentActivity;
import com.dong.starsmind.constant.AnimPropertyName;
import com.dong.starsmind.historyevent.activity.HistoryEventActivity;
import com.dong.starsmind.joke.fragment.JokeImageFragment;
import com.dong.starsmind.joke.fragment.JokeTextFragment;
import com.dong.starsmind.tuling.activity.TuLingActivity;
import com.dong.starsmind.utils.AppUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zengwendong on 16/12/18.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private ImageButton btn_play_or_stop;
    private Button btn_joke_image;
    private Button btn_joke;
    private Button btn_today_history;
    private Button btn_chat;

    private int duration = 3500;
    private boolean isPlay = true;
    private List<ObjectAnimator> animatorList = new ArrayList<>();
    private long currentPlayTime = 0;
    int btnViewWidth = AppUtils.dip2px(80);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        btn_play_or_stop = (ImageButton) view.findViewById(R.id.btn_play_or_stop);
        btn_joke = (Button) view.findViewById(R.id.btn_joke);
        btn_joke_image = (Button) view.findViewById(R.id.btn_joke_image);
        btn_chat = (Button) view.findViewById(R.id.btn_chat);
        btn_today_history = (Button) view.findViewById(R.id.btn_today_history);

        setListener();

        /*startBtnAnim1();
        startBtnAnim2();
        startBtnAnim3();
        startBtnAnim4();*/
        return view;
    }

    private void setListener() {
        btn_joke.setOnClickListener(this);
        btn_joke_image.setOnClickListener(this);
        btn_play_or_stop.setOnClickListener(this);
    }

    /*private void startBtn1Anim() {
        int tX = AppUtils.getScreenWidth() / 2 - AppUtils.dip2px(40);

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(btn_joke, AnimPropertyName.translationY, tX);
        oa1.setDuration(duration);
        oa1.setRepeatCount(ObjectAnimator.INFINITE);
        oa1.setRepeatMode(ObjectAnimator.REVERSE);
        oa1.start();
        animatorList.add(oa1);
    }

    private void startBtn2Anim() {
        int tX = AppUtils.getScreenWidth() / 2 - AppUtils.dip2px(40);
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(btn_joke_image, AnimPropertyName.translationY, tX);
        oa1.setDuration(duration);
        oa1.setRepeatCount(ObjectAnimator.INFINITE);
        oa1.setRepeatMode(ObjectAnimator.REVERSE);
        oa1.start();
        animatorList.add(oa1);
    }*/

    private void startBtnAnim1() {

        int animCenterTop = AppUtils.dip2px((250 - 100) / 2);
        int animCenterLeft = AppUtils.getScreenWidth() / 2 - AppUtils.dip2px(50) - btnViewWidth / 2;
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(btn_joke, AnimPropertyName.translationX, animCenterLeft);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(btn_joke, AnimPropertyName.translationY, animCenterTop);
        oa1.setRepeatCount(ObjectAnimator.INFINITE);
        oa1.setRepeatMode(ObjectAnimator.REVERSE);
        oa2.setRepeatCount(ObjectAnimator.INFINITE);
        oa2.setRepeatMode(ObjectAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(oa1).with(oa2);
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    private void startBtnAnim2() {
        int animCenterTop = AppUtils.dip2px((250 - 100) / 2);
        int animCenterLeft = AppUtils.getScreenWidth() / 2 - AppUtils.dip2px(50) - btnViewWidth / 2;
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(btn_joke_image, AnimPropertyName.translationX, -animCenterLeft);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(btn_joke_image, AnimPropertyName.translationY, animCenterTop);
        oa1.setRepeatCount(ObjectAnimator.INFINITE);
        oa1.setRepeatMode(ObjectAnimator.REVERSE);
        oa2.setRepeatCount(ObjectAnimator.INFINITE);
        oa2.setRepeatMode(ObjectAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(oa1).with(oa2);
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    private void startBtnAnim3() {
        int animCenterTop = AppUtils.dip2px((250 - 100) / 2);
        int animCenterLeft = AppUtils.getScreenWidth() / 2 - AppUtils.dip2px(50) - btnViewWidth / 2;
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(btn_today_history, AnimPropertyName.translationX, animCenterLeft);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(btn_today_history, AnimPropertyName.translationY, -animCenterTop);
        oa1.setRepeatCount(ObjectAnimator.INFINITE);
        oa1.setRepeatMode(ObjectAnimator.REVERSE);
        oa2.setRepeatCount(ObjectAnimator.INFINITE);
        oa2.setRepeatMode(ObjectAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(oa1).with(oa2);
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    private void startBtnAnim4() {
        int animCenterTop = AppUtils.dip2px((250 - 100) / 2);
        int animCenterLeft = AppUtils.getScreenWidth() / 2 - AppUtils.dip2px(50) - btnViewWidth / 2;
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(btn_chat, AnimPropertyName.translationX, -animCenterLeft);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(btn_chat, AnimPropertyName.translationY, -animCenterTop);
        oa1.setRepeatCount(ObjectAnimator.INFINITE);
        oa1.setRepeatMode(ObjectAnimator.REVERSE);
        oa2.setRepeatCount(ObjectAnimator.INFINITE);
        oa2.setRepeatMode(ObjectAnimator.REVERSE);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(oa1).with(oa2);
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    @Override
    public void onClick(View v) {
        if (AppUtils.isFastMultipleClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_joke:
                CommonFragmentActivity.startActivity(getActivity(), JokeTextFragment.class, getString(R.string.txt_joke));
                break;
            case R.id.btn_joke_image:
                CommonFragmentActivity.startActivity(getActivity(), JokeImageFragment.class, getString(R.string.txt_joke_image));
                break;
            case R.id.btn_today_history:
                HistoryEventActivity.startActivity(getActivity());
                break;
            case R.id.btn_chat:
                TuLingActivity.startActivity(getActivity());
                break;
            case R.id.btn_play_or_stop:
                if (isPlay) {
                    btn_play_or_stop.setImageResource(R.drawable.ic_play_white);
                    for (int i = 0; i < animatorList.size(); i++) {
                        currentPlayTime = animatorList.get(i).getCurrentPlayTime();
                        animatorList.get(i).cancel();
                    }
                } else {
                    btn_play_or_stop.setImageResource(R.drawable.ic_stop_white);
                    for (int i = 0; i < animatorList.size(); i++) {
                        animatorList.get(i).start();
                        animatorList.get(i).setCurrentPlayTime(currentPlayTime);

                    }
                }
                isPlay = !isPlay;
                break;
            default:
                break;
        }
    }
}
