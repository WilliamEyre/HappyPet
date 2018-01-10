package com.example.laptop.petshappy.city;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.laptop.petshappy.R;
import com.gjiazhe.wavesidebar.WaveSideBar;

import java.util.ArrayList;

/**
 * Created by Laptop on 2018/1/9.
 */
public class CityActivity extends AppCompatActivity{

    private ScrollView scroll = null;
    private RecyclerView rvContacts;
    private WaveSideBar sideBar;
    private RecyclerViewHeader header;

    private ArrayList<Contact> contacts = new ArrayList<>();
    private ScrollView scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shaixuantitle);

        initData();
        initView();

    }

    private void initView() {
        scrollview = (ScrollView) findViewById(R.id.scrollView);
        rvContacts = (RecyclerView) findViewById(R.id.rv_contacts);
        header= (RecyclerViewHeader) findViewById(R.id.recytitle);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setAdapter(new ContactsAdapter(contacts, R.layout.item_contacts));
        header.attachTo(rvContacts,true);
        sideBar = (WaveSideBar) findViewById(R.id.side_bar);

        sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i=0; i<contacts.size(); i++) {
                    if (contacts.get(i).getIndex().equals(index)) {
                        ((LinearLayoutManager) rvContacts.getLayoutManager()).scrollToPositionWithOffset(i, 0);
                        rvContacts.scrollToPosition(i);
                        return;
                    }
                }
            }
        });
    }

    private void initData() {
        contacts.addAll(Contact.getEnglishContacts());
    }
}
