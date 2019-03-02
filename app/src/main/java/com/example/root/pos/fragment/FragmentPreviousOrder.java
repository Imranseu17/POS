package com.example.root.pos.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.root.pos.R;
import com.example.root.pos.databinding.FragmentPreviousOderBinding;
import com.example.root.pos.framentmodel.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPreviousOrder extends Fragment {


    FragmentPreviousOderBinding fragmentPreviousOderBinding;

    public FragmentPreviousOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentPreviousOderBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_previous_oder,
                container,false);
        View v = fragmentPreviousOderBinding.getRoot();
        fragmentPreviousOderBinding.setFragment(new Order("Product Name",
                "Quantity","Price"));

            ((TextView)v.findViewById(R.id.cell1)).setText("Dal");
            ((TextView)v.findViewById(R.id.cell2)).setText("5");
            ((TextView)v.findViewById(R.id.cell3)).setText("120");
            ((TextView)v.findViewById(R.id.cell4)).setText("chal");
            ((TextView)v.findViewById(R.id.cell5)).setText("5");
            ((TextView)v.findViewById(R.id.cell6)).setText("250");
            ((TextView)v.findViewById(R.id.cell7)).setText("onion");
            ((TextView)v.findViewById(R.id.cell8)).setText("5");
            ((TextView)v.findViewById(R.id.cell9)).setText("120");

        return  v;

    }

}
