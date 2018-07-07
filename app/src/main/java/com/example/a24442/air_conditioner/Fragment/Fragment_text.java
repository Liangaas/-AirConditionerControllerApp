package com.example.a24442.air_conditioner.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a24442.air_conditioner.Activity.DatePickerDialog;
import com.example.a24442.air_conditioner.Activity.ElectricDetail;
import com.example.a24442.air_conditioner.Activity.MainActivity;
import com.example.a24442.air_conditioner.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by 24442 on 2018/5/6.
 */

public class Fragment_text extends Fragment{
    ListView listView;
    TextView yearChoose;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_text,container,false);
        listView = (ListView) view.findViewById(R.id.electricity_list);
        yearChoose = (TextView) view.findViewById(R.id.yearChoose);

        chooseYear();
        setListView();

        return view;
    }

    public void chooseYear(){
        yearChoose.setOnClickListener(new View.OnClickListener() {
            Calendar c = Calendar.getInstance();

            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
                                          int startDayOfMonth) {
                        String textString = String.format("%d 年", startYear);
                        yearChoose.setText(textString);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE)).show();
            }
        });
    }

    public void setListView(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        for(int i = 1; i <= 12; i++){
            map.put("monthMoney","100");
            map.put("month",i+"月");
            map.put("electric",i+"00");
            list.add(map);
            map = new HashMap<>();
        }

        EletricAdapter eletricAdapter = new EletricAdapter(getActivity(), list);

        listView.setAdapter(eletricAdapter);
    }

    static class EletricAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<HashMap<String, String>> list;

        public EletricAdapter(Context context, ArrayList<HashMap<String, String>> list) {
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
            final ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.month_item, parent, false);
                holder = new ViewHolder();
                holder.monthText = (TextView) convertView.findViewById(R.id.monthText);
                holder.electricity = (TextView) convertView.findViewById(R.id.electricity);
                holder.monthMoney = (TextView) convertView.findViewById(R.id.monthMoney);
                holder.month = convertView.findViewById(R.id.month);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            HashMap<String, String> monthElectric = list.get(position);
            holder.monthMoney.setText(monthElectric.get("monthMoney"));
            holder.monthText.setText(monthElectric.get("month"));
            holder.electricity.setText(monthElectric.get("electric"));

            holder.month.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap<String, String> map = list.get(position);

                    Intent intent = new Intent(getContext(), ElectricDetail.class);

                    intent.putExtra("electric", map.get("electric"));
                    intent.putExtra("cost",map.get("monthMoney"));
                    intent.putExtra("month",map.get("month"));

                    getContext().startActivity(intent);
                }
            });

            return convertView;
        }

        private static class ViewHolder {
            private TextView monthText;
            private TextView electricity;
            private TextView monthMoney;
            private RelativeLayout month;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1&& resultCode == 2){
            MainActivity mainActivity = (MainActivity) getActivity();
            FragmentManager fragmentManager = mainActivity.getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container,new Fragment_text());
        }
    }
}

