package com.a3rdsemproject.asmanjas.smarthome;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Automation extends Fragment {


    public Automation() {
        // Required empty public constructor
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      /* AlertDialog.Builder builderx = new AlertDialog.Builder(getContext());
       builderx.setMessage("Don't change sensor status unless special circumstances. It might affect working of the application and give unexpected results.");
       builderx.setTitle("Warning");
       builderx.setCancelable(true);
       builderx.setPositiveButton(
               "OK",
               new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                   }
               });


       AlertDialog alertx = builderx.create();
       alertx.show();*/
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_automation, container, false);



   }

}
