package com.example.mdpostres;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.example.mdpostres.databinding.FragmentConfirmationBinding;
import com.google.android.material.transition.MaterialFadeThrough;
import com.google.android.material.transition.MaterialSharedAxis;


public class ConfirmationFragment extends Fragment {



    FragmentConfirmationBinding binding ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.X, true));
        setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));

         setExitTransition(new MaterialFadeThrough());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false);
    }


    public void onViewCreated(@NonNull View view , @Nullable Bundle savedInnstanceState){

        binding.btnDone.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_confirmation_to_products);
        });

        setHasOptionsMenu(true);

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {

        menu.findItem(R.id.action_confirmation).setVisible(false);
        menu.findItem(R.id.confirmationFragment).setVisible(false);

        super.onPrepareOptionsMenu(menu);
    }
}