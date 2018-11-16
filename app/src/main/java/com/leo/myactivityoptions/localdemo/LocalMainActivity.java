package com.leo.myactivityoptions.localdemo;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.leo.myactivityoptions.Comment;
import com.leo.myactivityoptions.R;
import com.leo.myactivityoptions.localdemo.bean.CanScrollBean;
import com.leo.myactivityoptions.localdemo.bean.LocalBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LocalMainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<LocalBean> mipmaps = new ArrayList<>();
    private GridAdapter adapter;
    private Bundle bundle;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LocalMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localmain);
        EventBus.getDefault().register(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new GridAdapter(this, this);
        mipmaps.add(new LocalBean(R.mipmap.wangze1, "0"));
        mipmaps.add(new LocalBean(R.mipmap.other1, "1"));
        mipmaps.add(new LocalBean(R.mipmap.other2, "2"));
        mipmaps.add(new LocalBean(R.mipmap.wangze2, "3"));
        mipmaps.add(new LocalBean(R.mipmap.other3, "4"));
        mipmaps.add(new LocalBean(R.mipmap.other4, "5"));
        mipmaps.add(new LocalBean(R.mipmap.other5, "6"));
        mipmaps.add(new LocalBean(R.mipmap.wangze3, "7"));
        mipmaps.add(new LocalBean(R.mipmap.wangze4, "8"));
        mipmaps.add(new LocalBean(R.mipmap.wangze5, "9"));
        mipmaps.add(new LocalBean(R.mipmap.wangze6, "10"));
        mipmaps.add(new LocalBean(R.mipmap.wangze7, "11"));
        mipmaps.add(new LocalBean(R.mipmap.wangze8, "12"));
        mipmaps.add(new LocalBean(R.mipmap.wangze9, "13"));
        mipmaps.add(new LocalBean(R.mipmap.wangze10, "14"));

        Comment.mipmaps = mipmaps;
        adapter.setDataList(mipmaps);
        adapter.setHasStableIds(true);
        //初始化布局管理器
        final GridLayoutManager lm = new GridLayoutManager(this, 2);
        //设置布局管理器
        recyclerView.setLayoutManager(lm);
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set((int) getResources().getDimension(R.dimen.space_5), (int) getResources().getDimension(R.dimen.space_5), (int) getResources().getDimension(R.dimen.space_5), (int) getResources().getDimension(R.dimen.space_5));
            }
        });
        recyclerView.setAdapter(adapter);


        if (Build.VERSION.SDK_INT >= 22) {
            setExitSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    if (bundle != null) {
                        int i = bundle.getInt("index", 0);
                        sharedElements.clear();
                        names.clear();
                        View itemView = lm.findViewByPosition(i);
                        ImageView imageView = itemView.findViewById(R.id.imageView);
                        //注意这里第二个参数，如果放置的是条目的item则动画不自然。放置对应的imageView则完美
                        sharedElements.put(mipmaps.get(i).getTag(), imageView);
                        bundle = null;
                    }
                }
            });
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                int index = (int) view.getTag(R.id.imageView);
                Intent intent = new Intent(LocalMainActivity.this, LocalSecondActivity.class);
                intent.putExtra("index", index);

                if (Build.VERSION.SDK_INT >= 22) {
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(LocalMainActivity.this, view, mipmaps.get(index).getTag());// mAdapter.get(position).getUrl()
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
                break;
        }
    }


    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        bundle = new Bundle(data.getExtras());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onbackEvent(CanScrollBean canScrollBean) {//这里用于列表元素共享的时候，列表下滑
        recyclerView.scrollToPosition(canScrollBean.getPosition());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
