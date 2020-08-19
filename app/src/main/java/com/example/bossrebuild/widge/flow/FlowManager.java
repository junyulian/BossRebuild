package com.example.bossrebuild.widge.flow;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用
 * @Author: wujun
 * @Date: 2020/8/3
 */
public class FlowManager {

    private List<String> list = new ArrayList<>();
    private List<FlowTextView> flowList = new ArrayList<>();

    private int maxSize = 3;
    private int defaultIndex = -1;

    public FlowManager(List<String> data){
        list.addAll(data);
    }

    public FlowManager(List<String> data,int index){
        list.addAll(data);
        defaultIndex = index;
    }

    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }


    public void setView(Context context, ViewGroup group){
        FlowLayout flowLayout = new FlowLayout(context);
        for(int i=0; i<list.size(); i++){
            final FlowTextView view = new FlowTextView(context);
            view.setText(list.get(i));
            flowLayout.addView(view);

            if(defaultIndex != -1 && defaultIndex == i){
                view.setChecked(true);
                flowList.add(view);
            }

            view.setOnCheckedListener(new FlowTextView.OnCheckedListener() {
                @Override
                public void onChecked(boolean isChecked,String content) {
                    if(isChecked){
                        if(!flowList.contains(view)){
                            flowList.add(view);
                        }

                        if(flowList.size() > maxSize){
                            flowList.get(0).setChecked(false);
                        }
                    }else {
                        if(flowList.contains(view)){
                            flowList.remove(view);
                        }
                    }
                    if(listener != null){
                        listener.onCheckedChg(getCheckedContent());
                    }
                }
            });
        }
        group.addView(flowLayout);
    }


    public List<String> getCheckedContent(){
        List<String> checkedStr = new ArrayList<>();
        for(FlowTextView flowTextView: flowList){
            checkedStr.add(flowTextView.getText().toString().trim());
        }
        return checkedStr;
    }

    public OnCheckedChgListener listener;
    public interface OnCheckedChgListener{
        void onCheckedChg(List<String> list);
    }

    public void setOnCheckedChgListener(OnCheckedChgListener listener){
        this.listener = listener;
    }

}
