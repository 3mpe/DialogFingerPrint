package com.example.a3mpe.customfingerprint.interfaces;

import android.hardware.fingerprint.FingerprintManager;

/**
 * Created by Gaaraj on 11.05.2017.
 */


public interface SuccessInterface {
    void onSuccess(FingerprintManager.AuthenticationResult AuthenticationResult);

    void onError(FingerprintManager.AuthenticationResult AuthenticationResult);
}

