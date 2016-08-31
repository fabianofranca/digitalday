package com.fabianofranca.digitaldaycalabash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ImageView;

import com.fabianofranca.digitaldaycalabash.events.ReplaceFragmentEvent;
import com.fabianofranca.digitaldaycalabash.fragments.WelcomeFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private final String TAG = "DigitalDay";

    @BindView(R.id.backgroung_imageview)
    ImageView backgroundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        WelcomeFragment fragment = new WelcomeFragment();

        replaceFragment(fragment, false, false);
    }

    private void replaceFragment(Fragment fragment, boolean animate, boolean backStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (animate) {
            transaction.setCustomAnimations(R.anim.enter_from_right,
                    R.anim.exit_to_left,
                    R.anim.enter_from_left,
                    R.anim.exit_to_right);
        }

        transaction.replace(R.id.fragment_container, fragment);

        if (backStack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }

        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressWarnings("unused")
    public void onMessageEvent(ReplaceFragmentEvent event) {
        Log.d(TAG, "Replace fragment event");
        replaceFragment(event.getFragment(), event.isAnimate(), true);
    }
}
