package com.example.guidewindow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.guidewindow.databinding.ActivityGuideWindowBinding

class GuideWindowActivity : AppCompatActivity() {
//    lateinit var baseView:View;
lateinit var binding:ActivityGuideWindowBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_guide_window)
        binding.act = this
//        baseView = findViewById(R.id.base_view)
//        baseView.setOnClickListener {
//            Utils.toast(this,"baseView被点击了")
//        }
    }
    fun baseView(){
        Utils.toast(this,"baseView控件被点击了")
    }

    fun start(view: View) {
        Utils.toast(this,"start")
//        GuideWindowHelper.popGuide(this,view,R.layout.layout_user_guide,R.id.layout,R.id.start,object : PopupWindow.OnDismissListener{
//            override fun onDismiss() {
//                Utils.toast(this@GuideWindowActivity,"引导完成")
//            }
//        })
        GuideWindowHelper.popGuide(this,binding.baseView,R.layout.layout_guide_up,R.id.layout,R.id.start) { Utils.toast(this@GuideWindowActivity, "引导完成") }
    }
}