package com.busefisensi.efisiensiku.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busefisensi.efisiensiku.Adapter.PassengerAdapter;
import com.busefisensi.efisiensiku.Model.PassengerModel;
import com.busefisensi.efisiensiku.R;
import com.busefisensi.efisiensiku.RegisterActivity;
import com.busefisensi.efisiensiku.util.DatabaseHandler;
import com.busefisensi.efisiensiku.util.MyDividerItemDecoration;
import com.busefisensi.efisiensiku.util.RecyclerTouchListener;
import com.busefisensi.efisiensiku.util.RegisterPenumpang;
import com.busefisensi.efisiensiku.util.User;

import java.util.ArrayList;
import java.util.List;

public class AkunFragment extends Fragment {
    private User user;
    private List<PassengerModel> passengerList = new ArrayList<>();
    private PassengerAdapter passengerAdapter;
    RecyclerView recyclerView;
    TextView tvToolbar;
    View view;
    TextView tvNama, tvEmail,tvHandphone;
    DatabaseHandler databaseHandler;
    PassengerModel passengerModel;
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
        view = inflater.inflate(R.layout.fragment_akun, container, false);
        user = new User(view.getContext());
        retrieveData();


        databaseHandler = new DatabaseHandler(getContext());
        recyclerView = view.findViewById(R.id.rv_passenger);

        passengerList.addAll(databaseHandler.getAllDataPassenger());
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fabAddPenumpang);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterPenumpang.class);
                startActivity(intent);
            }
        });



//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                showActionsDialog(position);
//            }
//        }));

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        passengerAdapter = new PassengerAdapter(getContext(), passengerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(passengerAdapter);
        passengerAdapter.notifyDataSetChanged();
    }
//    private void createPassenger(String nama, String handphone){
//        long id = databaseHandler.addRecord(nama, handphone);
//
//        PassengerModel passengerModel = databaseHandler.addRecord(id);
//
//        if(passengerModel!=null){
//            passengerList.add(0, passengerModel);
//
//            passengerAdapter.notifyDataSetChanged();
//        }
//    }
//
//    private void updatePassenger(String handphone, int position){
//        PassengerModel passengerModel = passengerList.get(position);
//
//        passengerModel.setHandhphone(handphone);
//
//        databaseHandler.updatePassenger(handphone);
//
//        passengerList.set(position, handphone);
//        passengerAdapter.notifyDataSetChanged(position);
//
//
//    }
//
//    private void deletePassenger(int position){
//        databaseHandler.deletePassenger(passengerList.get(position));
//
//        passengerList.remove(position);
//        passengerAdapter.notifyItemRemoved(position);
//    }
//
//    private void showActionsDialog(final int position){
//        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Pilih");
//        builder.setItems(colors, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which == 0){
//                    showPassengerDialog(true, passengerList.get(position), position);
//                }else {
//                    deletePassenger(position);
//                }
//            }
//        });
//        builder.show();
//    }
//
//    private void showPassengerDialog(final boolean shouldUpdate, final PassengerModel passengerModel, final int position){
//        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//        View view = layoutInflater.inflate(R.layout.passenger_dialog, null);
//
//        AlertDialog.Builder alerDialogBuilderUserInput = new AlertDialog.Builder(AkunFragment.this);
//        alerDialogBuilderUserInput.setView(view);
//    }

    private void retrieveData(){
        tvNama = view.findViewById(R.id.tvNama);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvHandphone = view.findViewById(R.id.tvPhone);
        try {
            String namaDepan = user.getNamaDepan().toString();
            String namaBelakang = user.getNamaBelakang().toString();
            String email = user.getEmail().toString();
            String handphone = user.getHandphone().toString();
            Boolean isLogin = user.getIsLogin();
            Log.v("","" + isLogin);
            tvNama.setText(namaDepan + " " + namaBelakang);
            tvEmail.setText(email);
            tvHandphone.setText(handphone);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
