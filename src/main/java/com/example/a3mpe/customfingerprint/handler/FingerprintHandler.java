package com.example.a3mpe.customfingerprint.handler;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.a3mpe.customfingerprint.interfaces.SuccessInterface;
import com.example.a3mpe.customfingerprint.utils.FingerPrintSession;
import com.example.a3mpe.customfingerprint.utils.LocalStorageUtil;

/**
 * Created by Gaaraj on 11.05.2017.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback{
    private CancellationSignal cancellationSignal;
    private Context context;
    private FingerPrintSession fsession;
    private SuccessInterface Sci;


    public FingerprintHandler(Context mContext, SuccessInterface Sci) {
        this.context = mContext;
        this.fsession = LocalStorageUtil.getFingerPrintSession();
        this.Sci = Sci;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {

        cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(context, "Verification error\n\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(context, "Parmak izi okunamadı", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(context, "Fingerprint could not be read\n" + helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        if (fsession != null) {
            Sci.onSuccess(result);
        } else {
            Toast.makeText(context, "You must login with your e-mail address to sign in with your fingerprint.", Toast.LENGTH_LONG).show();
            Sci.onError(result);
        }

    }
}
