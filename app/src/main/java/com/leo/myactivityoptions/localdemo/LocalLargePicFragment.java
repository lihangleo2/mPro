package com.leo.myactivityoptions.localdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leo.myactivityoptions.R;
import com.leo.myactivityoptions.SecondActivity;

import static com.leo.myactivityoptions.Comment.mipmaps;

/**
 * Created by Mr_Wrong on 15/10/6.
 */
public class LocalLargePicFragment extends Fragment {
    private LocalSecondActivity activity;
    private int index;
    private ImageView image;

    public static Fragment newFragment(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        LocalLargePicFragment fragment = new LocalLargePicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (LocalSecondActivity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt("index");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewer, container, false);
        image = view.findViewById(R.id.image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.supportFinishAfterTransition();
            }
        });


        image.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                image.getViewTreeObserver().removeOnPreDrawListener(this);
                getActivity().supportStartPostponedEnterTransition();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(activity)
                .load(mipmaps.get(index).getMipmap())
                .asBitmap()
                .dontAnimate()
                .into(image);
    }

    public View getSharedElement() {
        return image;
    }


}
