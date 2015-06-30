package com.example.jie.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {


    @InjectView(R.id.go)
    Button mGo;
    @InjectView(R.id.zoom)
    Button mZoom;
    @InjectView(R.id.list)
    ListView mListView;

    private ArrayList<Integer> mList = new ArrayList<Integer>();
    Myadapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        for (int i = 0; i < 5; i++) {
            mList.add(i);
        }
        mAdapter = new Myadapter(mList);


        // listview 最多选三个
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(" list 1 ", mAdapter.list.size() + " - " + mList.size() );
//                mAdapter.list.remove(0);
//                Log.d(" list 2 ", mAdapter.list.size() + " - " + mList.size() );

                if (mAdapter.list.size() < 3) {  //不够三个的时候 直接添加
                    if (mAdapter.list.contains(position)) { //如果包含 则表示该item选中了，变为未选中状态
                        mAdapter.list.remove((Integer) position);

                    } else { // 变为选中状态
                        mAdapter.list.add(position);
                    }
                } else { // 选中的数量 >= 3
                    if (mAdapter.list.contains(position)) { // 如果包含 则表示该item选中了，变为未选中状态

                        mAdapter.list.remove((Integer) position);
                    } else { // 如果未包含 则表示该item未选中， 替换第一个item
//                        mAdapter.list.set( 0, position); //每次都替换的第0个

                        // 每添加一个新的，则把最旧的移除掉
                        mAdapter.list.trimToSize();
                        mAdapter.list.remove(0);
                        mAdapter.list.add(position);

                    }
                }

                mAdapter.notifyDataSetChanged();
            }
        });

        TextView tv = new TextView(this);
        tv.setText("bottom");
        tv.setTextSize(20);
        mListView.addFooterView(tv);

    }

    @OnClick( {R.id.zoom, R.id.uc, R.id.act })
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.zoom:

                startActivity(new Intent(this, ResideViewActivity.class));
                overridePendingTransition(  R.anim.zoom, 0 );

//                scale(mGo);
                break;
            case R.id.uc:
                startActivity(new Intent(this, UCActivity.class));
            case R.id.act:
                Intent intent =  new Intent(this, BActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);
            default:
                break;
        }

    }

    public void scale(View view) {
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scale);
        ViewHelper.setPivotX(mGo, 100f);
        ViewHelper.setPivotY(mGo, 100f);
        anim.setTarget(mGo);
        anim.start();
    }

    public void next(View v) {
        startActivity(new Intent(this, WithTextViewInFrameLayoutFragment.class));
    }


    private class Myadapter extends BaseAdapter{

        private ArrayList<Integer> list = new ArrayList<Integer>();

        public Myadapter(ArrayList<Integer> alist){
            super();
            this.list = alist;
//            this.list.addAll(alist);
        }


        @Override
        public int getCount() {

            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(MainActivity.this, R.layout.item_check, null);
            TextView txt = (TextView) view.findViewById(R.id.txt);
            CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox);

            txt.setText( list.get(position)+"");

            if( list.contains(position) ){
                checkbox.setChecked(true);
            }else{
                checkbox.setChecked(false);
            }



            return view;
        }
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
