package com.kihtrak.fragdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class top extends Fragment {

    TextView title;

    TextView info;
    TextView activityTxt;

    ReceiveString receiveString;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        receiveString = (ReceiveString)context;
    }

    Button btn;
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_top,null);
        title = fragmentView.findViewById(R.id.textView2);

        info = fragmentView.findViewById(R.id.textinfotop);

        btn = fragmentView.findViewById(R.id.btntop);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiveString.receive("CLICKED");
            }
        });
        activityTxt = getActivity().findViewById(R.id.textView);

        activityTxt.setText("TEST");


                return fragmentView;
    }

    public interface ReceiveString{
        public void receive(String str);
    }

    //ReceiveString ;
}
