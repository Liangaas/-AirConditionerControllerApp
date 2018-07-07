package com.example.a24442.air_conditioner.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a24442.air_conditioner.Activity.GPSModeEdit;
import com.example.a24442.air_conditioner.R;

/**
 * Created by 24442 on 2018/5/5.
 */

public class Fragment_location extends Fragment {
    TextView homeText;
    TextView openText;
    TextView closeText;
    TextView idealText;
    TextView rangeText;

    ImageView back;
    TextView edit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_location,container,false);

        homeText = (TextView) view.findViewById(R.id.HomeText);
        openText = (TextView) view.findViewById(R.id.OpenText);
        closeText = (TextView) view.findViewById(R.id.CloseText);
        idealText = (TextView) view.findViewById(R.id.IdealText);
        rangeText = (TextView) view.findViewById(R.id.RangeText);
        edit = (TextView) view.findViewById(R.id.title_edit1);

        edit.setText("设置");

        Intent intent = getActivity().getIntent();
        String home = intent.getStringExtra("homeText");
        String open = intent.getStringExtra("openText");
        String close = intent.getStringExtra("closeText");
        String ideal = intent.getStringExtra("idealText");
        String range = intent.getStringExtra("rangeText");

        if(!isEmpty(home))
            homeText.setText(home);
        if(!isEmpty(open))
            openText.setText(open);
        if(!isEmpty(close))
            closeText.setText(close);
        if(!isEmpty(ideal))
            idealText.setText(ideal);
        if(!isEmpty(range))
            rangeText.setText(range);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(getActivity(), GPSModeEdit.class);
                startActivity(editIntent);
            }
        });
        return view;
    }

    public boolean isEmpty(String attr){
        return attr == null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

