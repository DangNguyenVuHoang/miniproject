package com.example.miniproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListMenuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<MenuActivity> listMenu;
    public ListMenuAdapter(Context context, int layout, List<MenuActivity>nhaPhoList){
        this.context= context;
        this.layout= layout;
        this.listMenu = nhaPhoList;

    }
    @Override
    public int getCount() {
//        return 0;
        return listMenu.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view= inflater.inflate(layout,null);

        TextView txtTen= (TextView) view.findViewById(R.id.textviewTen);
        TextView txtMota= (TextView) view.findViewById(R.id.textviewMota);
        ImageView imageHinh= (ImageView) view.findViewById(R.id.imageView);

        //gan gia tri
        MenuActivity Menu= listMenu.get(i);

        txtTen.setText(Menu.getTen());
        txtMota.setText(Menu.getMota());
//        txtGiatien.setText(Menu.getGiaTien());
        imageHinh.setImageResource(Menu.getHinh());

    return view;
    }

}
