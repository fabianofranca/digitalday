package com.fabianofranca.digitaldaycalabash.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.fabianofranca.digitaldaycalabash.R;
import com.fabianofranca.digitaldaycalabash.events.ReplaceFragmentEvent;
import com.fabianofranca.digitaldaycalabash.tasks.SendTask;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ffranca on 30/08/16.
 */
public class SingUpFragment extends Fragment implements SendTask.SendTaskListener {

    @BindView(R.id.input_fullname)
    EditText fullNameEditText;

    @BindView(R.id.input_email)
    EditText emailEditText;

    @BindView(R.id.input_layout_fullname)
    TextInputLayout fullNameInputLayout;

    @BindView(R.id.input_layout_email)
    TextInputLayout emailInputLayout;

    private ProgressDialog progressDialog;

    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.sending));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sing_up_fragment, container, false);
        ButterKnife.bind(this, view);

        setupProgressDialog();

        return view;
    }

    @OnClick(R.id.send_button)
    @SuppressWarnings("unused")
    void send(View view) {

        if (!validateFullName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        progressDialog.show();

        SendTask task = new SendTask();

        task.setListener(this);

        task.execute();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateFullName() {
        if (fullNameEditText.getText().toString().trim().isEmpty()) {
            fullNameInputLayout.setError(getString(R.string.err_msg_fullname));
            requestFocus(fullNameEditText);
            return false;
        } else {
            fullNameInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            emailInputLayout.setError(getString(R.string.err_msg_email));
            requestFocus(emailEditText);
            return false;
        } else {
            emailInputLayout.setErrorEnabled(false);
        }

        return true;
    }

    @Override
    public void onSuccess() {
        progressDialog.dismiss();
        FinishFragment fragment = new FinishFragment();
        EventBus.getDefault().post(new ReplaceFragmentEvent(fragment, true));
    }
}
