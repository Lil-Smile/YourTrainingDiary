package com.igoryakovlev.YourTrainingDiary;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Smile on 23.08.15.
 */
public class AddRationFragment extends DialogFragment implements Constants {


    int weight;
    int hours, minutes;
    String eating=EATING_DATA[0];
    HashMap<String, Object> data;


    Spinner spinner;
    Button buttonTimePicker;
    Context context;
    AddFoodRationResponce addFoodRationResponce;
    EditText etWeight;

    void addContext(Context context)
    {
        this.context=context;
    }

    void addFinishedListener(AddFoodRationResponce addFoodRationResponce)
    {
        this.addFoodRationResponce = addFoodRationResponce;
    }

    void addMap(HashMap<String,Object> map)
    {
        this.data = map;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.add_ration_dialog, container, false);
        etWeight=(EditText)v.findViewById(R.id.etWeight);
        spinner = (Spinner)v.findViewById(R.id.spinnerEating);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,EATING_DATA);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Прием пищи");
        spinner.setSelection(0);

        //int width = context.getResources().getDisplayMetrics().widthPixels;
        //width = width/2;
        //int height = context.getResources().getDisplayMetrics().heightPixels;
        //height=height/2;
        //getDialog().getWindow().setLayout(width,height);

        //Log.d("Spinner", EATING_DATA[2]);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eating = EATING_DATA[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        v.findViewById(R.id.buttonAddFoodToRationInDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!etWeight.getText().toString().equals(""))
                {
                    weight =Integer.valueOf(etWeight.getText().toString()).intValue();
                    hours = Integer.valueOf(buttonTimePicker.getText().toString().substring(0, 2)).intValue();
                    minutes = Integer.valueOf(buttonTimePicker.getText().toString().substring(3,5));
                    Log.d("got","weight="+weight+" hours="+hours+" minutes="+minutes+" eating="+eating);
                    addFoodRationResponce.onAddingFoodFinished(weight, hours,minutes,eating, data);
                }
                dismiss();

            }
        });

        buttonTimePicker = (Button)v.findViewById(R.id.buttonTimePicker);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int h=calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        String str;
        if (h<10)
        {
            str="0"+h+":";
        } else
        {
            str = h+":";
        }
        if (m<10)
        {
            str=str+"0"+m;
        } else
        {
            str=str+m;
        }
        buttonTimePicker.setText(str);
        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int h=hourOfDay;
                        int m = minute;
                        String str;
                        if (h<10)
                        {
                            str="0"+h+":";
                        } else
                        {
                            str = h+":";
                        }
                        if (m<10)
                        {
                            str=str+"0"+m;
                        } else
                        {
                            str=str+m;
                        }
                        buttonTimePicker.setText(str);
                        //dismiss();
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                timePickerDialog.show();
            }
        });



        return v;
    }
}