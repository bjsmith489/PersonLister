package com.example.personlisterfinal;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.io.IOException;

public class CameraPermissionDialogFragment extends DialogFragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public static CameraPermissionDialogFragment newInstance(int title) {
        CameraPermissionDialogFragment frag = new CameraPermissionDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.confirm_camera)
                .setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Context context = getActivity();
                        PackageManager packageManager = context.getPackageManager();
                        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false) {
                            Toast.makeText(getActivity(), "This device does not have a camera.", Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
