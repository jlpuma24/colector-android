package com.co.colector.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.co.colector.R;
import com.co.colector.adapters.RegistryMenuAdapter;
import com.co.colector.helpers.DatabaseHelper;
import com.co.colector.model.Registry;
import com.co.colector.model.Tab;
import com.co.colector.utils.ColectorConstants;

import java.util.ArrayList;

/**
 * Created by User on 26/08/2015.
 */
public class FragmentInitialMenu extends Fragment {

    private ArrayList<Registry> registryArrayList;

    public FragmentInitialMenu(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.menu_ppal_layout,
                container, false);

        registryArrayList = DatabaseHelper.getRegistrysOfForms();

         if (registryArrayList.size() == 0){
             view.findViewById(R.id.listViewRegistros).setVisibility(View.GONE);
             view.findViewById(R.id.textViewNoForms).setVisibility(View.VISIBLE);
         }
         else {
             view.findViewById(R.id.listViewRegistros).setVisibility(View.VISIBLE);
             view.findViewById(R.id.textViewNoForms).setVisibility(View.GONE);

             ((ListView) view.findViewById(R.id.listViewRegistros)).
                     setAdapter(new RegistryMenuAdapter(getActivity(),registryArrayList));
         }

        return view;
    }
}