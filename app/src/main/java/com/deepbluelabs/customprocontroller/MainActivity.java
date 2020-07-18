package com.deepbluelabs.customprocontroller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ACTION_NAME = "com.rdapps.gamepad.registerui";
    public static final String EXTRA_UI_AUTHORITY = "JC_UI_AUTHORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(ACTION_NAME);
        intent.putExtra(EXTRA_UI_AUTHORITY, BuildConfig.CONTENT_PROVIDER_AUTHORITY);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            showInstallJoyConDroidDialog();
        }
    }

    private void showInstallJoyConDroidDialog() {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.install, (dialog, which) -> {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.rdapps.gamepad")));
                    } catch (ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.rdapps.gamepad")));
                    }
                })
                .setTitle(R.string.missing_app)
                .setMessage(R.string.install_message)
                .create()
                .show();
    }
}