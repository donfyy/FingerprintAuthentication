package com.donfyy.fingerprintauthentication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShowAuthenticationDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Sign In")
                .setMessage("Confirm fingerprint to continue")
                .setPositiveButton("Ok", (dialog, which) -> {

                })
                .setNegativeButton("Cancel", (dialog, which) -> {

                })
                .create();
        alertDialog
                .show();

        FingerprintManagerCompat fingerprintManagerCompat = FingerprintManagerCompat.from(this);
        if (fingerprintManagerCompat.isHardwareDetected()) {
            fingerprintManagerCompat.authenticate(null, 0, null, new FingerprintManagerCompat.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    super.onAuthenticationError(errMsgId, errString);
                    Log.e(TAG, "errMsgId:" + errMsgId + " errString:" + errString);
                }

                @Override
                public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                    super.onAuthenticationHelp(helpMsgId, helpString);
                    Log.e(TAG, "helpMsgId:" + helpMsgId + " helpString:" + helpString);
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Log.e(TAG, "onAuthenticationSucceeded:" + result);
                    alertDialog.dismiss();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Log.e(TAG, "onAuthenticationFailed");
                }
            }, null);
        } else {
            Toast.makeText(this, "no fingerprint hardware detected!", Toast.LENGTH_LONG).show();
        }
    }
}
