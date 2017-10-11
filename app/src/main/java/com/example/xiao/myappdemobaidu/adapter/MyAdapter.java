package com.example.xiao.myappdemobaidu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiao.myappdemobaidu.R;
import com.example.xiao.myappdemobaidu.entity.IdBean;
import com.example.xiao.myappdemobaidu.entity.MyBeanEntibity;
import com.example.xiao.myappdemobaidu.entity.SheBeiHaoBean;
import com.example.xiao.myappdemobaidu.ui.ShowBaidu;
import com.example.xiao.myappdemobaidu.utils.L;

import java.util.List;

/**
 * 项目名：MyAppDemoBaidu
 * 包名：com.example.xiao.myappdemobaidu.adapter
 * 文件名：MyAdapter
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/4/26 0026 下午 5:35
 * 描述：TODO
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<IdBean> listss;
    public LayoutInflater minflater;

    public MyAdapter(List<IdBean> listss, Context context) {
        this.listss = listss;
        this.context = context;
        minflater = LayoutInflater.from(context);
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(minflater.inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int sizes = listss.size();
        L.e("size "+sizes);
       final IdBean sbhb =  listss.get(position);
        String ids = sbhb.getName();
        holder.tv_item.setText(sbhb.getName());
//        这里给item 设置点击事件
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里点击获取当前position 下标对应的 对象里面的数据
                String dvID = sbhb.getName();
                Intent i = new Intent(context, ShowBaidu.class);
                i.putExtra("mid", dvID);
                context.startActivity(i);
                L.e("dvID"+dvID);
//                Toast.makeText(context, "下标为：" + position, Toast.LENGTH_SHORT).show();
            }
        });
        /*这里是设置图片的*/
        //holder.avatar.setImageResource(R.drawable.friend);
    }

    @Override
    public int getItemCount() {
        return listss.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item;
        View content;
        ImageView avatar;
        LinearLayout ll_layout; //点击事件的
        public ViewHolder(View itemView) {
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
            content = itemView.findViewById(R.id.content);
            ll_layout = (LinearLayout) itemView.findViewById(R.id.ll_layout);
            // avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
