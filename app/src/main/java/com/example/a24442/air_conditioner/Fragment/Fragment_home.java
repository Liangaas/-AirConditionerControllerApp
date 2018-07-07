package com.example.a24442.air_conditioner.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24442.air_conditioner.R;

/**
 * Created by 24442 on 2018/5/5.
 */

public class Fragment_home extends Fragment implements View.OnClickListener{


    ImageView iv_up;
    ImageView iv_down;
    ImageView iv_cold;
    ImageView iv_switch;
    ImageView iv_hot;

    TextView tv_temperature;

    boolean hot_change;
    boolean cold_change;
    boolean switch_change;

    public static Fragment_home newInstantce(String tittle){
        Fragment_home fragment_home = new Fragment_home();
        Bundle bundle = new Bundle();
        bundle.putString("tittle",tittle);
        fragment_home.setArguments(bundle);
        return  fragment_home;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        iv_up = (ImageView) view.findViewById(R.id.iv_up);
        iv_down = (ImageView) view.findViewById(R.id.iv_down);
        iv_cold = (ImageView) view.findViewById(R.id.iv_cold);
        iv_switch = (ImageView) view.findViewById(R.id.iv_switch);
        iv_hot = (ImageView) view.findViewById(R.id.iv_hot);

        hot_change = false;
        cold_change = false;
        switch_change = false;

        tv_temperature = (TextView) view.findViewById(R.id.tv_temperature);

        iv_up.setOnClickListener(this);
        iv_down.setOnClickListener(this);
        iv_cold.setOnClickListener(this);
        iv_switch.setOnClickListener(this);
        iv_hot.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_up:
                int tu = Integer.parseInt(tv_temperature.getText().toString());
                 if (tu >= 30){
                    Toast.makeText(getContext(),"温度已达最高",Toast.LENGTH_SHORT).show();
                }else {
                    tu = tu + 1;
                    tv_temperature.setText(Integer.toString(tu));
                }
                break;

            case R.id.iv_down:
                int td = Integer.parseInt(tv_temperature.getText().toString());
                if (td <= 20){
                    Toast.makeText(getContext(),"温度已达最低",Toast.LENGTH_SHORT).show();
                }else {
                    td = td - 1;
                    tv_temperature.setText(Integer.toString(td));
                }
                break;

            case R.id.iv_hot:
                if (switch_change) {
                    if (!hot_change) {
                        if (!cold_change) {
                            iv_hot.setImageResource(R.drawable.hot_pressed);
                            Toast.makeText(getContext(), "热风已开", Toast.LENGTH_SHORT).show();
                        }else {
                            iv_hot.setImageResource(R.drawable.hot_pressed);
                            iv_cold.setImageResource(R.drawable.cold);
                            cold_change = !cold_change;
                            Toast.makeText(getContext(), "热风已开", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        iv_hot.setImageResource(R.drawable.hot);
                        Toast.makeText(getContext(), "热风已关", Toast.LENGTH_SHORT).show();
                    }
                    hot_change = !hot_change;
                }else {
                    Toast.makeText(getContext(),"请先开空调!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_switch:
                if (!switch_change){
                    iv_switch.setImageResource(R.drawable.on_off_pressed );
                    Toast.makeText(getContext(),"空调已开",Toast.LENGTH_SHORT).show();
                }else {
                    iv_switch.setImageResource(R.drawable.on_off);
                    Toast.makeText(getContext(),"空调已关",Toast.LENGTH_SHORT).show();
                }
                switch_change = !switch_change;
                break;
            case R.id.iv_cold:
                if (switch_change){
                    if (!cold_change){
                        if (!hot_change){
                            iv_cold.setImageResource(R.drawable.cold_pressed );
                            Toast.makeText(getContext(),"冷风已开",Toast.LENGTH_SHORT).show();
                        }else {
                            iv_cold.setImageResource(R.drawable.cold_pressed );
                            iv_hot.setImageResource(R.drawable.hot);
                            hot_change = !hot_change;
                            Toast.makeText(getContext(),"冷风已开",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        iv_cold.setImageResource(R.drawable.cold);
                        Toast.makeText(getContext(),"冷风已关",Toast.LENGTH_SHORT).show();
                    }
                    cold_change = !cold_change;
                }else {
                    Toast.makeText(getContext(),"请先开空调!",Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                    break;
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
