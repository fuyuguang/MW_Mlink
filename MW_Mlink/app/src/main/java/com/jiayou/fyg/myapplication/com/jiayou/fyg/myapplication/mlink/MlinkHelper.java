package com.jiayou.fyg.myapplication.com.jiayou.fyg.myapplication.mlink;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.jiayou.fyg.myapplication.ExplainActivity;
import com.jiayou.fyg.myapplication.MainActivity;
import com.zxinsight.MLink;
import com.zxinsight.MagicWindowSDK;
import com.zxinsight.mlink.MLinkCallback;
import com.zxinsight.mlink.MLinkIntentBuilder;

import java.util.Map;

/**
 * Created by fyg on 16-8-22.
 */
public class MlinkHelper {

    public static final String MLINK_SERVICE_KEY = "_com_jiuxian_all";
    public static final String PARAM = "param";
    public static final String PUTAOJIU_FLAG = "home/putaojiu";
    public static final String YANGJIU_FLAG = "home/yangjiu";
    public static final String BAIJIU_FLAG = "home/baijiu";
    public static final boolean TEST_DATA_SWITCH = true ;

    public static void onNewIntent(Activity context, Intent intent) {

        if (context == null){
            return;
        }

        if (intent != null){
            Uri mLink = intent.getData();
            if (mLink != null) {
                MagicWindowSDK.getMLink().router(mLink);
            } else {

                MLink.getInstance(context).checkYYB();
            }
        }
    }


    public static void gotoRouter(Activity context, Intent intent) {

        if (context == null){
            return;
        }

        if (intent != null){

            Intent temIntent =  new Intent(context, MainActivity.class);
            temIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(temIntent);

            //跳转 router 调用
            if (intent.getData() != null) {
                MLink.getInstance(context).router(intent.getData()); //跳转后结束当前 activity
                context.finish();
            } else { //如果需要应用宝跳转,则调用。否则不需要 //MLink.getInstance(this).checkYYB(); //跳转到首页

                //如果需要应用宝跳转，则调用。否则不需要
                MLink.getInstance(context).checkYYB();

                //TODO:动画等耗时操作结束后再调用 checkYYB(),一般写在 starActivity 前即可 MLink.getInstance(this).checkYYB();
                //context.startActivity(new Intent(context, MainActivity.class));
                context.finish();
            }
        }
    }




    public static void registerWCmLink(final Activity temContext) {

        MLink.getInstance(AppContext.getInstance()).registerDefault(new MLinkCallback() {
            @Override
            public void execute(Map<String, String> paramMap, Uri uri, Context context) { //HomeActivity 为你的首页


                MLinkIntentBuilder.buildIntent(paramMap, context, MainActivity.class);
            }
        });
        // mLinkKey: mLink 的 key, mLink 的唯一标识
        MLink.getInstance(AppContext.getInstance()).register(MLINK_SERVICE_KEY, new MLinkCallback() {

            public void execute(Map<String, String> paramMap, Uri uri, Context context) {


                String defaultParam = "";
                if (paramMap != null ) {
                    if (paramMap.containsKey(PARAM) && !TextUtils.isEmpty(paramMap.get(PARAM))) {
                        defaultParam = paramMap.get(PARAM);
                    }
                }

                if (!TextUtils.isEmpty(defaultParam)){
                    Uri temUrl = Uri.parse(defaultParam);
                    temUrl = temUrl.buildUpon().appendQueryParameter("url_wine_active_shoptype", String.valueOf(1)).build();;
                    defaultParam = temUrl.toString();
                }

                Intent intent = new Intent(AppContext.getInstance(), ExplainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.getExtras().putString("desc",defaultParam);
                AppContext.getInstance().startActivity(intent);
            }
        });
    }



    public static void onStart(Activity activity,Intent intent) {

        if (activity == null){
            return;
        }

        if (intent != null){
            Uri mLink = intent.getData();
            if (mLink != null) {
                MagicWindowSDK.getMLink().router(mLink);
            } else {
                MLink.getInstance(activity).checkYYB();
            }
        }
    }


}
