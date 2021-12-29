package com.example.guidewindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.guidewindow.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setAct(this);
    }

    /**
     * 没有定制引导语的引导弹窗
     */
    public void guideWindow(){
        Utils.toast(this,"guideWindow");
        startActivity(new Intent(this,GuideWindowActivity.class));
    }

    public void guideMultipleView(){
        Utils.toast(this,"guideWindowMultipleView");
        startActivity(new Intent(this,GuideMultipleViewActivity.class));
    }

    public void dialog(){
        startActivity(new Intent(this,DialogActivity.class));
    }

}