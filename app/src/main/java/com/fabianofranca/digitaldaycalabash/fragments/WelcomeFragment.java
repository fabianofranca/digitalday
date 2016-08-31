package com.fabianofranca.digitaldaycalabash.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fabianofranca.digitaldaycalabash.R;
import com.fabianofranca.digitaldaycalabash.events.ReplaceFragmentEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ffranca on 30/08/16.
 */
public class WelcomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.sing_up_button)
    @SuppressWarnings("unused")
    void singUp(View view) {
        SingUpFragment fragment = new SingUpFragment();
        EventBus.getDefault().post(new ReplaceFragmentEvent(fragment, true));
    }
}
