package com.busefisensi.efisiensiku.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiku.R;

public class AkunFragment extends Fragment {
    TextView tvToolbar;
    public static AkunFragment newInstance() {
        AkunFragment fragment = new AkunFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        return view;
    }
}
