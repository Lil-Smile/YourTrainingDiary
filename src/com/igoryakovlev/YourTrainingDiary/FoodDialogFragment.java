package com.igoryakovlev.YourTrainingDiary;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import org.w3c.dom.Text;

/**
 * Created by Smile on 21.08.15.
 */
public class FoodDialogFragment extends DialogFragment implements View.OnClickListener {
    EditText etFoodName, etProt,etFat,etCarbo,etCalor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(getResources().getString(R.string.adding));
        View v = inflater.inflate(R.layout.add_food_dialog,null);
        v.findViewById(R.id.buttonSaveTheFood).setOnClickListener(this);


        etFoodName = (EditText)v.findViewById(R.id.etFoodName);
        etProt = (EditText)v.findViewById(R.id.etProtein);
        etFat = (EditText)v.findViewById(R.id.etFat);
        etCarbo = (EditText)v.findViewById(R.id.etCarbohydrates);
        etCalor = (EditText)v.findViewById(R.id.etCalorifical);



        return v;
    }

    @Override
    public void onClick(View v) {
        DBHelper dbHelper = new DBHelper(v.getContext());

        String nameFood;
        float prot,fat,carbo,calor;

        try
        {
            nameFood=etFoodName.getText().toString();
            prot=Float.valueOf(etProt.getText().toString()).floatValue();
            fat=Float.valueOf(etFat.getText().toString()).floatValue();
            carbo=Float.valueOf(etCarbo.getText().toString()).floatValue();
            calor=Float.valueOf(etCalor.getText().toString()).floatValue();
        } catch(Exception e)
        {
            e.printStackTrace();
            Log.d("adding","smth is wrong");
            dismiss();
            return;
        }

        dbHelper.addTheFoodToDatabase(nameFood,prot,fat,carbo,calor);
        dismiss();
        return;
    }
}