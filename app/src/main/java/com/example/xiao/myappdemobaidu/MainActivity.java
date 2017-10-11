package com.example.xiao.myappdemobaidu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anonymous.greendao.gen.IdBeanDao;
import com.example.xiao.myappdemobaidu.adapter.MyAdapter;
import com.example.xiao.myappdemobaidu.entity.IdBean;
import com.example.xiao.myappdemobaidu.entity.MyBeanEntibity;
import com.example.xiao.myappdemobaidu.entity.SheBeiHaoBean;
import com.example.xiao.myappdemobaidu.sqlite.MyGreenDao;
import com.example.xiao.myappdemobaidu.utils.ShareUtils;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<MyBeanEntibity.MsgBean> list = new ArrayList<>();
    private List<MyBeanEntibity.MsgBean> list1;
    private EditText et_body;
    private Button btn_conimit;
    private List<SheBeiHaoBean> listshebi = new ArrayList<>();
    //    private SheBeiHaoBean shb = null;
    IdBean idBean = null;
    private String id;
    SheBeiHaoBean sbhb = new SheBeiHaoBean();
    // List<SheBeiHaoBean> listss = new ArrayList<>();
    //    recycleView
    private RecyclerView mRv;
    private LinearLayoutManager mManager;
    private SwipeDelMenuAdapter mAdapter;
    private List<IdBean> listG;
    private IdBean ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }



    private void initView() {
        et_body = (EditText) findViewById(R.id.et_body);
        btn_conimit = (Button) findViewById(R.id.btn_coimit);
        btn_conimit.setOnClickListener(this);

        mRv = (RecyclerView) findViewById(R.id.mRv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        /*程序第一次进来取出保存的数据 适配到listview*/
        listG =  MyGreenDao.queryUserList(getApplicationContext());

        mAdapter = new SwipeDelMenuAdapter(listG, this);
        mRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
//        questHttp();
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
                    mAdapter.notifyDataSetChanged();

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

//            /*下面设置点击Item监听事件 然后跳转到地图*/
//        lsv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
//                //这里点击 Item 跳转到 ShowBaidu 要把 item 中的字段 m_id  带过去
//                L.e("listssOnclick  "+listss);
//                String mid = listss.get(postion).getDevdeID();
//                Intent i = new Intent(MainActivity.this, ShowBaidu.class);
//                i.putExtra("mid", mid);
//                startActivity(i);
//            }
//        });
    }

    /*点击按钮添加 设备号数据 显示在 listview上*/
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_coimit:
                //获取输入框中的设备号
                String et_nubmer =  et_body.getText().toString().trim();
                if (!et_nubmer.isEmpty()) {
                    //如果输入框不为空的话就把输入框的数据添加到Greendao数据库
                    ib = new IdBean(null,et_nubmer);
                    IdBeanDao ibd = MyGreenDao.greendao(getApplicationContext());
                    ibd.insert(ib);
//                    把数据同时也保存到对象中去·
                    ib.setName(et_nubmer);
                    //然后把对象添加到集合中
                    listG.add(ib);
                    //然后刷新adapter
                    mAdapter.notifyDataSetChanged();
                    //每次添加完毕都把输入框清空
                    et_body.setText("");


                } else {
                    Toast.makeText(getApplicationContext(),"设备号不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /*item侧滑删除*/
    private class SwipeDelMenuAdapter extends MyAdapter {

        public SwipeDelMenuAdapter(List<IdBean> listss, Context context) {
            super(listss, context);
        }
        /*再次从写一下onCreateViewHolder 然后加载侧滑删除的那个布局*/

        @Override
        public SwipeDelMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(minflater.inflate(R.layout.item_city_swipe, parent, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            super.onBindViewHolder(holder, position);
            holder.itemView.findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((SwipeMenuLayout)holder.itemView).quickClose(); //删除
                    String name = listG.get(position).getName();
                    IdBean idb =   listG.get(position);
                    MyGreenDao.deleData(getApplicationContext(),idb);
//                    String dvid = listshebi.get(position).getDevdeID();
//                    sc.deleteStudent(dvid);
                    listG.remove(holder.getAdapterPosition());
//                    ShareUtils.deleShare(getApplicationContext(),"nubmer");

                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}

