package com.fabianofranca.digitaldaycalabash.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabianofranca.digitaldaycalabash.R;

import butterknife.ButterKnife;

/**
 * Created by ffranca on 30/08/16.
 */
public class FinishFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finish_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
