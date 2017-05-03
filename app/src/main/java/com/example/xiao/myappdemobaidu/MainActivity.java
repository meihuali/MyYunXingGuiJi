package com.example.xiao.myappdemobaidu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xiao.myappdemobaidu.adapter.MyAdapter;
import com.example.xiao.myappdemobaidu.entity.IdBean;
import com.example.xiao.myappdemobaidu.entity.MyBeanEntibity;
import com.example.xiao.myappdemobaidu.ui.ShowBaidu;
import com.example.xiao.myappdemobaidu.utils.L;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lsv_list;
    private MyAdapter adapter;
    private ArrayList<MyBeanEntibity.MsgBean> list = new ArrayList<>();
    private List<MyBeanEntibity.MsgBean> list1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }



    private void initView() {
        lsv_list = (ListView) findViewById(R.id.lsv_list);
        adapter = new MyAdapter(this,list);
        lsv_list.setAdapter(adapter);
        questHttp();
    }

    private void questHttp() {
        // String url = "http://api.sunsyi.com:8081/Trajectory/Hardwarelist/list/1/limit/2,5";
        String url = "http://api.sunsyi.com:8081/Trajectory/Hardwarelist/list/1";
        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                Gson gson = new Gson();
                MyBeanEntibity me = gson.fromJson(t, MyBeanEntibity.class);
                int status = me.getStatus();
                if (status == 1) {
//                    这里解析成功返回list后·再把这个集合添加到 最上面 声明的那个集合里面
                    list1 = me.getMsg();
                    list.addAll(list1);
                    adapter.notifyDataSetChanged();

                }

            }
        })
                .url(url) //接口地址
                //请求类型，如果不加，默认为 GET 可选项：
                //POST/PUT/DELETE/HEAD/OPTIONS/TRACE/PATCH
                .httpMethod(RxVolley.Method.GET)
                //设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
                .cacheTime(0)
                //是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
                .shouldCache(false)
                .encoding("UTF-8") //编码格式，默认为utf-8
                .doTask();  //执行请求操作
    }

    /*点击事件*/
    private void initData() {
            /*下面设置点击监听事件*/
        lsv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                //这里点击 Item 跳转到 ShowBaidu 要把 item 中的字段 m_id  带过去
                String mid = list.get(postion).getM_id();
                Intent i = new Intent(MainActivity.this, ShowBaidu.class);
                i.putExtra("mid", mid);
                startActivity(i);
            }
        });
    }


}
