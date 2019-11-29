package com.luo.viewpager04;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.luo.viewpager04.adapter.WallpaperPaperAdapter;
import com.luo.viewpager04.fragment.Fragment_Classification;
import com.luo.viewpager04.fragment.Fragment_Local;
import com.luo.viewpager04.fragment.Fragment_Recommend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private RadioButton mRecommend;
    private RadioButton mClassification;
    private RadioButton mLocal;
    private RadioGroup mWallpaperRg;
    private ViewPager mWallpaperVp;
    private List<Fragment> mList;

    private Fragment_Recommend fragment_recommend;
    private Fragment_Classification fragment_classification;
    private Fragment_Local fragment_local;

    private WallpaperPaperAdapter wallpaperPaperAdapter;
    private TextPaint textPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        mRecommend = (RadioButton) findViewById(R.id.recommend);
        mClassification = (RadioButton) findViewById(R.id.classification);
        mLocal = (RadioButton) findViewById(R.id.local);
        mWallpaperRg = (RadioGroup) findViewById(R.id.wallpaper_rg);
        mWallpaperVp = (ViewPager) findViewById(R.id.wallpaper_vp);

        //radiogroup的点击事件
        mWallpaperRg.setOnCheckedChangeListener(this);
        //添加适配器
        mWallpaperVp.setAdapter(wallpaperPaperAdapter);
        //这里没有使用setOnPageChangeListener来设置是因为，setOnPageChangeListener已经过时，显示使用addOnPageChangeListener也是一样的效果，用法不变
        mWallpaperVp.addOnPageChangeListener(this);
        //缓存页面，预加载，这里预加载所有的页面，后面的值来控制预加载的页面数
        mWallpaperVp.setOffscreenPageLimit(mList.size() - 1);
    }

    private void initData() {
        mList = new ArrayList<Fragment>();
        //实例化List数组
        fragment_recommend = new Fragment_Recommend();
        fragment_classification = new Fragment_Classification();
        fragment_local = new Fragment_Local();

        mList.add(fragment_recommend);
        mList.add(fragment_classification);
        mList.add(fragment_local);

        //这里与文章getChildFragmentManager写的不同是因为，现在这个demo是一个fragmentActivity，而不再是一个fragment
        wallpaperPaperAdapter = new WallpaperPaperAdapter(getSupportFragmentManager(), mList);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        clearState();
        switch (checkedId) {
            case R.id.recommend:
                Log.d("luo", "mWallpaperVp 0 ->");
                mWallpaperVp.setCurrentItem(0);
                Log.d("luo", "recommend ->");
                mRecommend.setTextColor(Color.BLACK);
                mRecommend.setTextSize(20);
                textPaint = mRecommend.getPaint();
                textPaint.setFakeBoldText(true);
                break;
            case R.id.classification:
                Log.d("luo", "mWallpaperVp 1 ->");
                mWallpaperVp.setCurrentItem(1);
                Log.d("luo", "classification ->");
                mClassification.setTextColor(Color.BLACK);
                mClassification.setTextSize(20);
                textPaint = mClassification.getPaint();
                textPaint.setFakeBoldText(true);
                break;
            case R.id.local:
                Log.d("luo", "mWallpaperVp 2 ->");
                mWallpaperVp.setCurrentItem(2);
                Log.d("luo", "local ->");
                mLocal.setTextColor(Color.BLACK);
                mLocal.setTextSize(20);
                textPaint = mLocal.getPaint();
                textPaint.setFakeBoldText(true);
                break;
            default:
                break;

        }
    }

    //滑动过程中的动作
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //选择某个页面松手后会被调用
    @Override
    public void onPageSelected(int position) {
        clearState();
        switch (position) {
            case 0:
                //这里必须设置一下setChecked，不然当viewpager滑动到最右边，无法点击最左边的那个radiogroup里面的radiobutton
                mRecommend.setChecked(true);
                mRecommend.setTextColor(Color.BLACK);
                mRecommend.setTextSize(20);
                textPaint = mRecommend.getPaint();
                textPaint.setFakeBoldText(true);
                break;
            case 1:
                mClassification.setChecked(true);
                mClassification.setTextColor(Color.BLACK);
                mClassification.setTextSize(20);
                textPaint = mClassification.getPaint();
                textPaint.setFakeBoldText(true);
                break;
            case 2:
                mLocal.setChecked(true);
                mLocal.setTextColor(Color.BLACK);
                mLocal.setTextSize(20);
                textPaint = mLocal.getPaint();
                textPaint.setFakeBoldText(true);
                break;
            default:
                break;

        }
    }

    //手指放上去，松开，拖动都会被调用
    //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //初始化底部导航栏
    private void clearState() {
        mRecommend.setTextColor(getResources().getColor(R.color.wallpaper_title));
        mClassification.setTextColor(getResources().getColor(R.color.wallpaper_title));
        mLocal.setTextColor(getResources().getColor(R.color.wallpaper_title));

        mRecommend.setTextSize(16);
        mClassification.setTextSize(16);
        mLocal.setTextSize(16);

        textPaint = mRecommend.getPaint();
        textPaint.setFakeBoldText(false);

        textPaint = mClassification.getPaint();
        textPaint.setFakeBoldText(false);

        textPaint = mLocal.getPaint();
        textPaint.setFakeBoldText(false);
    }
}
