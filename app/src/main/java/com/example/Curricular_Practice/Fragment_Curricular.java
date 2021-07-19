package com.example.Curricular_Practice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_Curricular extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private ArrayList<Item> arrayList;
    private  FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ImageButton btn_back_to_home;
    private Button btn_add;
    static final int home = 0;
    public Fragment_Curricular(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curricular,container,false);
        ////////////////뒤로가기버튼////////////////////
        btn_back_to_home = view.findViewById(R.id.btn_back_curri_to_home);
        btn_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setFrag(home);
            }
        });
        /////////////////////데이터 추가버튼////////////////////
        btn_add = view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Frag2","addData");
                databaseReference.push().setValue(new Item());
                adapter.notifyDataSetChanged();
            }
        });
/////////////////////////리싸이클러뷰출력//////////////////////
        recyclerView = view.findViewById(R.id.recyclerview_curricular);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Item");//키
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //기존데이터베이스값 수신
                Log.e("dpdpdp","되냐?");
                arrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Item item = snapshot.getValue(Item.class);
                    arrayList.add(item);
                }
                //여기에 플러스버튼 추가하고싶음
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Item item = snapshot.getValue(Item.class);
                arrayList.add(item);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Item item = snapshot.getValue(Item.class);
                    arrayList.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ////////////////////////////////////////////////////////
        adapter = new CustomAdapter(arrayList,getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

}
