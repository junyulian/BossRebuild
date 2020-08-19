package com.example.bossrebuild.activity;

import com.example.bossrebuild.R;
import com.example.bossrebuild.databinding.ActivityFlowBinding;
import com.example.bossrebuild.util.LogUtils;
import com.example.bossrebuild.widge.flow.FlowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/5
 */
public class FlowActivity extends BaseActivity<ActivityFlowBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flow;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        List<String> list = new ArrayList<>();
        for(int i=0; i<20; i++){
            list.add("测试"+i);
        }

        final FlowManager flowManager = new FlowManager(list,0);
        flowManager.setMaxSize(3);
        flowManager.setView(this,binding.llContent);

        binding.tvBtn.setOnClickListener(v->{
            List<String> checkList = flowManager.getCheckedContent();
            for(String str: checkList){
                LogUtils.e("--str:"+str);
            }
        });


    }
}
