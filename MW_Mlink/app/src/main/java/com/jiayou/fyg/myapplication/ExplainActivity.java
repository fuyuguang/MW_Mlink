package com.jiayou.fyg.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jiayou.fyg.myapplication.com.jiayou.fyg.myapplication.mlink.AppContext;

public class ExplainActivity extends AppCompatActivity {

    private TextView mtextView;
    private TextView mtextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String desc = getIntent().getExtras().getString("desc","eeor");
        mtextView = (TextView) findViewById(R.id.text_view);
        mtextView.setError(desc);


        mtextView2 = (TextView) findViewById(R.id.text_view2);
        mtextView2.setError(desc);


        showToast(desc);

    }


    
    public static void showToast(String content){

        //test add commit
        //test add commit
        Toast.makeText(AppContext.getInstance(),content,Toast.LENGTH_SHORT).show();
    }
    
    
    public static void showToastForce(String content){
        Toast.makeText(AppContext.getInstance(),content,Toast.LENGTH_SHORT).show();
    }
    
    
    /**
    new method 
    **/
    public static void showToastForce(String content,boolean flag ){
        if(flag){
            System.out.println(" showToastForce ");
        }
        Toast.makeText(AppContext.getInstance(),content,Toast.LENGTH_SHORT).show();
    }
    
    
    /**
    testMethod1
    **/
    public static void testMethod1(){
        
            System.out.println(" testMethod1 ");
        
    }
    
    
    /**
    testMethod2
    **/
    public static void testMethod2(){
        
            System.out.println(" testMethod1 ");
        
    }
    
    
      /**
    testMethod2
    **/
    public static void testMethod3(){
        
            System.out.println(" testMethod3 ");
        
    }
    
    
      /**
    testMethod2
    **/
    public static void testMethod4(){
        
            System.out.println(" testMethod4 ");
        
    }
    
    
    
}
