package com.dimeno.common.widget.bar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

/**
 * @author sdg
 * createTime 2020/12/18
 * desc:可用于底部bar（eg:首页，我的） 也可用于头部（eg：最新任务，历史任务）
 * */
public class CustomViewPager extends ViewPager {

    private boolean isCanScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置其是否能滑动换页
     *
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return isCanScroll && super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return isCanScroll && super.onTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * MyPagerAdapter
     */
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        //集合
        private List<Fragment> fragments;
        //title
        private String[] mTitles;

        /**
         * 构造器
         * @param fm
         * @param fragments
         * @param titles
         */
        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
            super(fm);
            this.fragments = fragments;
            this.mTitles = titles;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}