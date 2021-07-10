package com.example.navigationcomponentsafeargs;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentTwo extends Fragment {

    private View rootView;
    private NavController navController;
    private ToolbarManager toolbarManager;
    private Toolbar toolbar;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_two, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
        toolbarManager = ToolbarManager.getInstance();

        toolbar = rootView.findViewById(R.id.toolbar2);
        textView = rootView.findViewById(R.id.textView);

        setupToolbar();

        String name = FragmentTwoArgs.fromBundle(getArguments()).getName();
        textView.setText(name);
        return rootView;
    }

    private void setupToolbar() {
        toolbarManager.setupToolbar(getActivity(), navController, null, toolbar,
                true);
    }
}