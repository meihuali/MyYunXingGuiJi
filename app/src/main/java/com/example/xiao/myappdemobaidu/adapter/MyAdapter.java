package com.example.xiao.myappdemobaidu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiao.myappdemobaidu.R;
import com.example.xiao.myappdemobaidu.entity.MyBeanEntibity;

import java.util.List;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.adapter
 * 文件名：MyAdapter
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/4/26 0026 下午 5:35
 * 描述：TODO
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<MyBeanEntibity.MsgBean> list;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<MyBeanEntibity.MsgBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoderl vh = null;
//        第一次加载
        if (convertView == null) {
            vh = new ViewHoderl();
            convertView = inflater.inflate(R.layout.item_view, null);
            vh.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
//            设置缓存
            convertView.setTag(vh);
        } else {
            vh = (ViewHoderl) convertView.getTag();
        }
        //设置数据
        MyBeanEntibity.MsgBean mem = list.get(position);
        vh.tv_item.setText(mem.getM_id());

        return convertView;
    }

    class ViewHoderl{
        private TextView tv_item;
    }
}
