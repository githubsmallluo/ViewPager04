package com.luo.viewpager04.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class WallpaperPaperAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    //传入一个list<Fragment>类型的数组
    public WallpaperPaperAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    //返回数组的长度
    @Override
    public int getCount() {
        return mList.size();
    }

    //得到当前的position
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }
}