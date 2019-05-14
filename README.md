## 共享元素小图到大图浏览，即recycleView到viewPager,pager左右滑动，共享动画item改变:

 - viewPager滑动到当前gridView还没有滑到的item.主动让gridView下滑
 - 共享元素滑动改变共享item
 - 注意：在网络图片加载中。增加了图片加载进度。解决动画的弊端


（为了效果展示压缩了 展示的gif,有点模糊，请下载观看最佳效果）


#### 项目演示
|滑动改变item|滑动到列表不可见，列表下滑|
|:---:|:---:|
|![](https://github.com/lihangleo2/mPro/blob/master/smallupdateGif1.gif)|![](https://github.com/lihangleo2/mPro/blob/master/smallupdateGif2.gif)|



[我的博客](https://blog.csdn.net/leol_2/article/details/80198306)  
#
#
#
#
## 第三方实现高仿微信图片拖拽退出
（如果用共享元素也能实现。大致实现原理是，调转一个透明的activity，getLocation记住点击控件的坐标，然后设置给透明activity里控件位置）
 - 支持图片缩放，支持gif动图，支持长图，对未加载图片有妥善的处理
 - 手指拖拽退出，单击退出
 - 支持自定义布局（本文暂且未提供，如需要，作者持续更新）
 
 ### 在此感谢以下：
 [sketch](https://github.com/panpf/sketch)  
 
 [ArtPlayer](https://github.com/maiwenchang/ArtPlayer)  
   
 [特别感谢Diooto，支持原创。笔者只是在其基础上优化了一些，加上自己的理解](https://github.com/moyokoo/Diooto)
 
 
 #### 第三方功能展示
|正常功能展示|视频|长图|
|:---:|:---:|:---:|
|![](https://github.com/lihangleo2/mPro/blob/master/%E6%AD%A3%E5%B8%B8%E5%BD%95%E5%88%B6.gif)|![](https://github.com/lihangleo2/mPro/blob/master/%E8%A7%86%E9%A2%91.gif)|![](https://github.com/lihangleo2/mPro/blob/master/%E9%95%BF%E5%9B%BE.gif)
|gif动图|未加载图|
|![](https://github.com/lihangleo2/mPro/blob/master/gif%E5%8A%A8%E5%9B%BE.gif)|![](https://github.com/lihangleo2/mPro/blob/master/%E6%9C%AA%E5%8A%A0%E8%BD%BD%E5%9B%BE.gif)|
