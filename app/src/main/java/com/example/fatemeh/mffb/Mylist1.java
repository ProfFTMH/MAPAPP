package com.example.fatemeh.mffb;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Mylist1 extends ArrayAdapter<String>{

    private final Activity context1;
    private final String[] wb;
    private final Integer[] imageId1;
    public Mylist1(Activity context1,
                  String[] wb, Integer[] imageId1) {
        super(context1, R.layout.activity_mylist1, wb);
        this.context1 = context1;
        this.wb = wb;
        this.imageId1 = imageId1;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context1.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.activity_mylist1, null, true);
        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.txtt);
        ImageView imageView1 = (ImageView) rowView.findViewById(R.id.imgg);
        txtTitle1.setText(wb[position]);
        imageView1.setImageResource(imageId1[position]);
        return rowView;
    }
}
