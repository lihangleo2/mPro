package com.leo.myactivityoptions;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.R.attr.fragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView gridView;
    private ArrayList<String> urls = new ArrayList<>();
    private MyAdapter adapter;
    private String imgaeMyPath;


    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgaeMyPath = getFilesDir().getAbsolutePath().toString() + "/aliyycoy.jpg";
        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new MyAdapter(this, this);

        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525432713869&di=27e60d46d72f858c21c8fd38e94d0946&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F22%2F06%2F55%2F57b2d98e109c6_1024.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1525422655&di=87cd3df69db3cb362dc20469ccdefab7&src=http://img.taopic.com/uploads/allimg/121027/240425-12102H1412511.jpg");
        urls.add("https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/0824ab18972bd40736cc3df078899e510fb309b7.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1525422668&di=01d996856c4c7959bef410ccf69fdb40&src=http://img01.taopic.com/160929/318761-16092Z9225648.jpg");
        urls.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2294814182,4087227957&fm=27&gp=0.jpg");
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526027532&di=5cdce38e9d3e815babf5d066f6e8ae70&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F140721%2F240449-140H10HA098.jpg");
        urls.add("http://imgsrc.baidu.com/imgad/pic/item/d000baa1cd11728bdf999dd4c2fcc3cec2fd2c8b.jpg");
        urls.add("http://img5.duitang.com/uploads/item/201312/05/20131205171922_dVBte.jpeg");
        urls.add("http://img.pconline.com.cn/images/upload/upc/tx/gamephotolib/1410/27/c0/40170771_1414341013392.jpg");
        urls.add("http://images.17173.com/2014/9yin/2014/03/11/20140311092844886.png");
        Comment.urls = urls;

        adapter.setUrls(urls);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        if (Build.VERSION.SDK_INT >= 22) {
            setExitSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    if (bundle != null) {
                        int i = bundle.getInt("index", 0);
                        sharedElements.clear();
                        names.clear();
//                        fragment.mAdapter.get(i).getUrl()
//                        fragment.mLayoutManager.findViewByPosition(i)
                        View itemView = gridView.getChildAt(i);
                        ImageView imageView = itemView.findViewById(R.id.imageView);
                        //注意这里第二个参数，如果防止是的条目的item则动画不自然。放置对应的imageView则完美
                        sharedElements.put(urls.get(i), imageView);
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
                ImageView imvv = (ImageView) view;
                Bitmap bm = ((BitmapDrawable) imvv.getDrawable()).getBitmap();
                Comment.bitmap = bm;
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("index", index);
//                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        MainActivity.this, new Pair<View, String>(view, "shareView"));
//                ActivityCompat.startActivity(MainActivity.this, intent, activityOptions.toBundle());


                if (Build.VERSION.SDK_INT >= 22) {
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(MainActivity.this, view, urls.get(index));// mAdapter.get(position).getUrl()
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
//        if (fragment != null && fragment.isAdded()) {//这里用于列表元素共享的时候，列表下滑
//            supportPostponeEnterTransition();
//            fragment.scrollToPosition(bundle.getInt("index", 0));
//        }
    }

}
