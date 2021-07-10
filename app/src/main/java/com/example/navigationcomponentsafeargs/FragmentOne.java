package com.example.navigationcomponentsafeargs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOne extends Fragment {

    private View rootView;
    private NavController navController;
    private Button btnGotoSecondFragment;
    private ToolbarManager toolbarManager;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_one, container, false);
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);
        toolbarManager = ToolbarManager.getInstance();

        toolbar = rootView.findViewById(R.id.toolbar);
        btnGotoSecondFragment = rootView.findViewById(R.id.btnGotoSecondFragment);

        setupToolbar();

        btnGotoSecondFragment.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                FragmentOneDirections.ActionFragmentOneToFragmentTwo action =
                        FragmentOneDirections.actionFragmentOneToFragmentTwo();
                action.setName("IT wala...");
                navController.navigate(action);
            }
        });

        return rootView;
    }

    private void setupToolbar() {
        toolbarManager.setupToolbar(getActivity(), navController, null, toolbar,
                false);
    }
}