package com.helloworld.helloworld;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment_login, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        databaseHelper.addEmp("1", "A", "10");
        databaseHelper.addEmp("2", "B", "20");
        databaseHelper.addEmp("3", "C", "30");
        databaseHelper.addEmp("4", "D", "40");
        databaseHelper.addEmp("5", "D", "50");
        databaseHelper.addEmp("6", "E", "60");
        databaseHelper.addEmp("7", "E", "70");
        databaseHelper.addEmp("8", "F", "80");
        databaseHelper.addEmp("9", "G", "90");
        databaseHelper.addEmp("10", "H", "100");

        RecyclerView rcProductView = view.findViewById(R.id.rcProduct);
        rcProductView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Product> datSource = databaseHelper.getProducts();

        ProductAdapter productAdapter = new ProductAdapter(datSource);
        rcProductView.setAdapter(productAdapter);
    }
}
