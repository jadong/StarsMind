package com.dong.starsmind.helper;

/**
 * Created by zengwendong on 16/11/2.
 */
public interface OnMoveAndSwipedListener {
    boolean onItemMove(int fromPosition , int toPosition);
    void onItemDismiss(int position);
}
