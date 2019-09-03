package com.frankfaustino.notifcationexample;

import android.accounts.Account;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.clover.sdk.util.CloverAccount;
import com.clover.sdk.util.CloverAuth;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "⚡️";
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = CloverAccount.getAccount(this);

        if (account == null) {
            Log.d(TAG, "getAccount failed");
            return;
        } else {
            Log.d(TAG, "getAccount succeeded with " + account.name);

            GetAccountDetails getAccountDetails = new GetAccountDetails();
            getAccountDetails.execute();
        }
    }

    private class GetAccountDetails extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(final Void... params) {
            try {
                CloverAuth.AuthResult authResult = CloverAuth.authenticate(getApplicationContext(), account);
                final String authToken = authResult.authToken;
                String baseUrl = authResult.authData.getString(CloverAccount.KEY_BASE_URL);
                String merchantId = authResult.merchantId;

                Log.i(TAG, "authToken " + authToken);
                Log.i(TAG, "baseUrl " + baseUrl);
                Log.i(TAG, "merchantId " + merchantId);

            } catch (OperationCanceledException | AuthenticatorException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
