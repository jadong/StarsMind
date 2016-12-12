package test;

import com.dong.starsmind.app.SMLog;
import com.dong.starsmind.news.presenter.HealthNewsPresenter;

/**
 * Created by zengwendong on 16/11/8.
 */
public class TestMain {

    public static void main(String[] args){
        SMLog.isSystemOut = true;
        HealthNewsPresenter healthNewsPresenter = new HealthNewsPresenter(null);
        healthNewsPresenter.getMeiNvImages(1);

    }

}
