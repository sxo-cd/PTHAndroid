package me.passtheheadphones.ui.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import me.passtheheadphones.app.R;
import me.passtheheadphones.base.BaseActivity;
import me.passtheheadphones.ui.profile.ProfileActivity;
import me.passtheheadphones.util.DialogFactory;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginPresenter mLoginPresenter;
    @BindView(R.id.username_input) public TextView username;
    @BindView(R.id.password_input) public TextView password;

    ProgressDialog alertDialog;

    @OnClick(R.id.login_button)
    public void submit(View view) {
        mLoginPresenter.login(username.getText().toString(), password.getText().toString());
        alertDialog = new ProgressDialog(this);
        alertDialog.setMessage(getResources().getString(R.string.logging_ing));
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /**
     * Android activity lifecycle methods
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenter.attachView(this);
        //try get index, see if we have a cookie
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alertDialog != null) {
            alertDialog.cancel();
        }

        mLoginPresenter.detachView();
    }

    @Override
    public void showLoginSuccess() {
        alertDialog.dismiss();
        Intent result = new Intent();
        setResult(Activity.RESULT_OK, result);
        LoginActivity.this.finish();

        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_profile))
                .show();
    }
}
