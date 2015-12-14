package com.suhang.test5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {
    private RecyclerView rv;
    private int dx;
    private int windowW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        windowW = getWindowManager().getDefaultDisplay().getWidth();
        rv = (RecyclerView) findViewById(R.id.rv);
        Button b = (Button) findViewById(R.id.b);
        final LinearLayoutManager manager = new LinearLayoutManager(MainActivity1.this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter();
        rv.setAdapter(adapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                MainActivity1.this.dx+=dx;
            }
        });

        rv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    dx = 0;
                }

                if(event.getAction()==MotionEvent.ACTION_UP||event.getAction()==MotionEvent.ACTION_CANCEL){
                    if(dx>=windowW/2){
                        rv.smoothScrollToPosition(1);
                    }else{
                        rv.smoothScrollToPosition(0);
                    }
                    Log.i("aaa","aaaaaaaaaaaaaaaaa");

                }
                return false;
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = getLayoutInflater().from(MainActivity1.this).inflate(R.layout.recycler_item, null);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (position % 2 == 0) {
                holder.iv.setImageResource(R.drawable.error);
            }
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv;

            public MyViewHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.iv);
            }
        }
    }
}
