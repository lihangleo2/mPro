package com.leo.myactivityoptions.likewx;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.leo.myactivityoptions.R;

import net.moyokoo.diooto.Diooto;
import net.moyokoo.diooto.DragDiootoView;
import net.moyokoo.diooto.config.DiootoConfig;
import net.moyokoo.diooto.interfaces.DefaultCircleProgress;

import org.salient.artplayer.MediaPlayerManager;
import org.salient.artplayer.ScaleType;
import org.salient.artplayer.VideoView;

import java.util.ArrayList;

import me.panpf.sketch.SketchImageView;

/**
 * Created by leo
 * on 2019/5/14.
 */
public class LikeWxActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ReAdapter adapter;
    //沉浸式状态栏
    protected ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likewx);
        mImmersionBar = ImmersionBar.with(this)
                .statusBarColor(R.color.status_color)
//                .keyboardEnable(true)
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
                .fitsSystemWindows(true);
        mImmersionBar.init();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ReAdapter(this, this);
        arrayList.add("https://ww1.sinaimg.cn");
        arrayList.add("https://ww1.sinaimg.cn/large/0073sXn7ly1g1p973zyz4g30de0mskjp?imageView2/2/w/460");
//        arrayList.add("https://github.com/moyokoo/Media/blob/master/diooto3.jpg?raw=true");
        arrayList.add("https://ww4.sinaimg.cn/bmiddle/61e7945bly1fwnpjo7er0j215o6u77o1.jpg");
//        arrayList.add("https://github.com/moyokoo/Media/blob/master/diooto4.jpg?raw=true");
        arrayList.add("http://img5.mtime.cn/mg/2018/07/06/093947.51483272.jpg");

        arrayList.add("https://github.com/moyokoo/Media/blob/master/diooto5.jpg?raw=true");
        arrayList.add("https://github.com/moyokoo/Media/blob/master/diooto6.jpg?raw=true");
        arrayList.add("https://github.com/moyokoo/Media/blob/master/diooto7.png?raw=true");
        arrayList.add("https://github.com/moyokoo/Media/blob/master/diooto8.png?raw=true");
        arrayList.add("https://github.com/moyokoo/Media/blob/master/diooto9.jpg?raw=true");

        final GridLayoutManager lm = new GridLayoutManager(this, 3);
        //设置布局管理器
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set((int) getResources().getDimension(R.dimen.divide), (int) getResources().getDimension(R.dimen.divide), (int) getResources().getDimension(R.dimen.divide), (int) getResources().getDimension(R.dimen.divide));
            }
        });
        adapter.setRemindList(arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.srcImageView:
                final int position = (int) v.getTag();
                if (position != 3) {
                    //加载列表
                    String[] array = (String[]) arrayList.toArray(new String[arrayList.size()]);
                    Diooto diooto = new Diooto(LikeWxActivity.this)
                            .indicatorVisibility(View.VISIBLE)
                            .urls(array)
                            .type(DiootoConfig.PHOTO)
                            .immersive(true)
                            .position(position, 0)
                            .views(recyclerView, R.id.srcImageView)
                            .loadPhotoBeforeShowBigImage(new Diooto.OnLoadPhotoBeforeShowBigImageListener() {
                                @Override
                                public void loadView(SketchImageView sketchImageView, int position) {
                                    sketchImageView.displayImage(arrayList.get(position));
                                    sketchImageView.setOnLongClickListener(new View.OnLongClickListener() {
                                        @Override
                                        public boolean onLongClick(View v) {
                                            Toast.makeText(LikeWxActivity.this, "Long click", Toast.LENGTH_SHORT).show();
                                            return false;
                                        }
                                    });
                                }
                            }).start();
                } else {
                    final String[] array = (String[]) arrayList.toArray(new String[arrayList.size()]);
                    //加载视频
                    Diooto diooto = new Diooto(LikeWxActivity.this)
                            .urls(array[position])
                            .position(position)
                            .views((SketchImageView) v)
                            .type(DiootoConfig.VIDEO)
                            .immersive(true)
                            .setProgress(new DefaultCircleProgress())
                            //提供视频View
                            .onProvideVideoView(new Diooto.OnProvideViewListener() {
                                @Override
                                public View provideView() {
                                    return new VideoView(LikeWxActivity.this);
                                }
                            })
                            //显示视频加载之前的缩略图
                            .loadPhotoBeforeShowBigImage(new Diooto.OnLoadPhotoBeforeShowBigImageListener() {
                                @Override
                                public void loadView(SketchImageView sketchImageView, int positionL) {
                                    sketchImageView.displayImage(array[position]);
                                }
                            })
                            //动画到最大化时的接口
                            .onVideoLoadEnd(new Diooto.OnShowToMaxFinishListener() {
                                @Override
                                public void onShowToMax(final DragDiootoView dragDiootoView, final SketchImageView sketchImageView, final View progressView) {
                                    VideoView videoView = (VideoView) dragDiootoView.getContentView();
                                    SimpleControlPanel simpleControlPanel = new SimpleControlPanel(LikeWxActivity.this);
                                    simpleControlPanel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dragDiootoView.backToMin();
                                        }
                                    });
                                    simpleControlPanel.setOnVideoPreparedListener(new SimpleControlPanel.OnVideoPreparedListener() {
                                        @Override
                                        public void prepared() {
                                            sketchImageView.setVisibility(View.GONE);
                                            progressView.setVisibility(View.GONE);
                                        }
                                    });
                                    videoView.setControlPanel(simpleControlPanel);

                                    videoView.setUp("http://vfx.mtime.cn/Video/2018/07/06/mp4/180706094003288023.mp4");
                                    videoView.start();
                                    dragDiootoView.notifySize(1920, 1080);
                                    MediaPlayerManager.instance().setScreenScale(ScaleType.SCALE_CENTER_CROP);
                                }
                            })
                            //到最小状态的接口
                            .onFinish(new Diooto.OnFinishListener() {
                                @Override
                                public void finish(DragDiootoView dragDiootoView) {
                                    MediaPlayerManager.instance().releasePlayerAndView(LikeWxActivity.this);
                                }
                            }).start();

                }


                break;
        }
    }
}
