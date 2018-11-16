package com.leo.myactivityoptions.localdemo.bean;

import java.io.Serializable;

public class LocalBean implements Serializable {
    private Integer mipmap;
    private String tag;

    public LocalBean(Integer mipmap, String tag) {
        this.mipmap = mipmap;
        this.tag = tag;
    }

    public Integer getMipmap() {
        return mipmap;
    }

    public void setMipmap(Integer mipmap) {
        this.mipmap = mipmap;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
