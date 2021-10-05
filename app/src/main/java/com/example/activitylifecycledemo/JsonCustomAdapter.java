package com.example.activitylifecycledemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class JsonCustomAdapter extends BaseAdapter {
    ArrayList<PlayerModel> playerModels;
    Context context;
    TextView txtId, txtName, txtCountry, txtCity;
    ImageView img;

    public JsonCustomAdapter(Context c,ArrayList<PlayerModel> playerModel)
    {
        this.context = c;
        this.playerModels = playerModel;
    }
    @Override
    public int getCount() {
        return playerModels.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.json_list_item,viewGroup,false);

        txtId = view.findViewById(R.id.txtId);
        txtName = view.findViewById(R.id.txtName);
        txtCountry = view.findViewById(R.id.txtCountry);
        txtCity = view.findViewById(R.id.txtCity);
        img = view.findViewById(R.id.img);

        txtId.setText(playerModels.get(i).getId());
        txtName.setText(playerModels.get(i).getName());
        txtCity.setText(playerModels.get(i).getCity());
        txtCountry.setText(playerModels.get(i).getCountry());

        Picasso.with(context).load(playerModels.get(i).getImageURL()).into(img);
        return view;
    }
}
