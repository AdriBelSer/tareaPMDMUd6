package com.yinya.pmdm06;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.yinya.pmdm06.databinding.FragmentStationBinding;

public class StationFragment extends DialogFragment {
    private final Station station;
    private final StationCallback callback;
    private FragmentStationBinding binding;

    public StationFragment(Station station, StationCallback callback) {
        this.station = station;
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initializeStations();
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }

    private void initializeStations() {
        binding.tvTitle.setText(station.title);
        binding.tvDescription.setText(station.description);
        binding.ivStation.setImageResource(station.image);
        binding.btnNext.setOnClickListener(v -> {
                    String userPassword = binding.tvPassword.getText().toString();
                    callback.onStationCompleted(station, userPassword);
                    this.dismiss();
                }
        );
    }
}