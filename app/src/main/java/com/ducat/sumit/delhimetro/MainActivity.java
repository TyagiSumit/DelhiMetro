package com.ducat.sumit.delhimetro;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner Source,Destination;
    String[] Station,distance;
    boolean flag1=false,flag2=false;
    Button btn;
    TextView fare;
    int i,j,rate;
    double sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Source= findViewById(R.id.SourceS);
        Destination= findViewById(R.id.DestinationS);
        btn= findViewById(R.id.button);
        fare= findViewById(R.id.fare);
        //btn.setEnabled(false);
        Station =getResources().getStringArray(R.array.stations);
        distance= getResources().getStringArray(R.array.distances);
        ArrayAdapter<String> Sadapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Station) {
            @Override
            public boolean isEnabled(int position) {
                if (position == (Station.length - 1)) {
                    // Disable the second item from Spinner
                    return false;
                } else {
                    return true;
                }
            }
        };
        ArrayAdapter<String> Dadapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Station) {
            @Override
            public boolean isEnabled(int position) {
                if (position ==0) {
                    // Disable the second item from Spinner
                    return false;

                } else {
                    return true;
                }
            }
        };

        Source.setAdapter(Sadapter);
        Destination.setAdapter(Dadapter);
        Destination.setSelection(Station.length-1);

        Source.setSelected(false);
        Destination.setSelected(false);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Source.getSelectedItemId()==0||Destination.getSelectedItemId()==(Station.length-1)){
                    if(Source.getSelectedItemId()==0){
                        TextView errorText =(TextView) Source.getSelectedView();
                        errorText.setError("");
                        errorText.setTextColor(Color.RED);//just to highlight that this is an error
                        errorText.setText("Select source");//changes the selected item text to this
                    }

                    if(Destination.getSelectedItemId()==(Station.length-1)){
                        TextView errorText =(TextView) Destination.getSelectedView();
                        errorText.setError("");
                        errorText.setTextColor(Color.RED);//just to highlight that this is an error
                        errorText.setText("Select Destination");//changes the selected item text to this
                    }
                }
                else{
                    String source=Source.getSelectedItem().toString();
                    String destination= Destination.getSelectedItem().toString();
                    i=0;j=0;
                    for (i = 0; i < Station.length; i++) {
                        if(source.equals(Station[i])) break;
                    }
                    for (j = 0; j < Station.length; j++) {
                        if(destination.equals(Station[j])) break;
                    }
                    sum=0;
                    if(i<j){
                        for(int x=i;i<j;i++){
                          sum=sum+Double.parseDouble(distance[i]);
                        }
                    }
                    else{
                        for(int x =j;j<i;j++){
                            sum=sum+Double.parseDouble(distance[j]);
                        }
                    }
                    if(sum >0&& sum <=2) rate=10;
                    else if(sum >2&& sum <=5) rate=10;
                    else if(sum >5&& sum <=12) rate=20;
                    else if(sum >12&& sum <=21) rate=30;
                    else if(sum >21&& sum <=32) rate=40;
                    else rate=60;
                    fare.setText("Total fare is: "+rate);
                }
            }
        });



    }
}
