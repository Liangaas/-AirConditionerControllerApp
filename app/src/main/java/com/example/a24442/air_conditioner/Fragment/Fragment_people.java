package com.example.a24442.air_conditioner.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a24442.air_conditioner.R;
import com.example.a24442.air_conditioner.Activity.SituationEdit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 24442 on 2018/5/6.
 */

public class Fragment_people extends Fragment {

    private ListView listView;
    private TextView add;
    private ImageView back;
    private ArrayList<HashMap<String, String>> data;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_people,container,false);

        initViewAndData();

        return view;
    }

    private void initViewAndData(){
        listView = (ListView) view.findViewById(R.id.situationList);
        add = (TextView) view.findViewById(R.id.title_edit1);
        add.setText("");
        add.setBackgroundResource(R.drawable.add);

        setAddStep();//    添加模块的步骤


        data = new ArrayList<>();

        Intent intent = getActivity().getIntent();
        String title = intent.getStringExtra("title");
        String item1 = intent.getStringExtra("first");
        String item2 = intent.getStringExtra("second");
        int type = intent.getIntExtra("type", 0);
        //获取修改的信息
        if(title != null) {
            HashMap<String, String> map = new HashMap<>();
            map.put("item1", item1);
            map.put("item2", item2);
            map.put("title", title);
            map.put("type", type + "");
            data.add(map);
        }


        Fragment_people.SituationAdapter situationAdapter = new Fragment_people.SituationAdapter(getActivity(), data);
        listView.setAdapter(situationAdapter);
    }

    //    添加模块的步骤
    public void setAddStep(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SituationEdit.class);
                startActivityForResult(intent,1);
            }
        });
    }


    static class SituationAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<HashMap<String, String>> list;

        public SituationAdapter(Context context, ArrayList<HashMap<String, String>> list) {
            this.context = context;
            this.list = list;
        }

        public Context getContext() {
            return context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Fragment_people.SituationAdapter.ViewHolder holder;
            if (convertView == null) {
                //绑定item模块
                convertView = LayoutInflater.from(context).inflate(R.layout.situation_item, parent, false);
                holder = new Fragment_people.SituationAdapter.ViewHolder();
                //设置模块中的数
                holder.titleText = (TextView) convertView.findViewById(R.id.theText1);
                holder.text1 = (TextView) convertView.findViewById(R.id.theText2);
                holder.text2 = (TextView) convertView.findViewById(R.id.theText3);
                holder.switchCompat = convertView.findViewById(R.id.switch1);

                convertView.setTag(holder);
            } else {
                holder = (Fragment_people.SituationAdapter.ViewHolder) convertView.getTag();
            }

            HashMap<String, String> modeData = list.get(position);
            holder.titleText.setText(modeData.get("title"));
            holder.text1.setText(modeData.get("item1"));
            if(modeData.get("item2") == null)
                holder.text2.setVisibility(View.GONE);
            else{
                holder.text2.setText(modeData.get("item2"));
            }


            holder.switchCompat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            return convertView;
        }

        private static class ViewHolder {
            private TextView titleText;
            private TextView text1;
            private TextView text2;
            private SwitchCompat switchCompat;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
