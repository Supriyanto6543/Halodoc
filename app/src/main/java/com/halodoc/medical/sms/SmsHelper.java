package com.halodoc.medical.sms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Supriyanto on 7/1/2020.
 */
public class SmsHelper extends ContextWrapper {

    private static final String TAG = "SmsHelper";
    private static final String HASH = "SHA-256";
    private static final int HASH_BYTE = 9;
    private static final int HASH_BASE_CHAR = 11;

    public SmsHelper(Context base) {
        super(base);
    }

    private static String hash(String packageName, String name){
        String appInfo = packageName + name;

        try{
            MessageDigest digest = MessageDigest.getInstance(HASH);
            digest.update(appInfo.getBytes(StandardCharsets.UTF_8));
            byte[] hashSignatur = digest.digest();
            hashSignatur = Arrays.copyOfRange(hashSignatur, 0, HASH_BYTE);
            String base64 = Base64.encodeToString(hashSignatur, Base64.NO_PADDING | Base64.NO_WRAP);
            base64 = base64.substring(0, HASH_BASE_CHAR);
            Log.d(TAG, String.format("Package: %s -- hash: %s", packageName, base64));
            return base64;
        }catch (NoSuchAlgorithmException e){
            Log.e(TAG, "hash:NoSuchAlgorithm", e);
        }
        return null;
    }

    public ArrayList getAppHashCode() {
        ArrayList appCodes = new ArrayList<>();
        try {
            // Get all package signatures for the current package
            String packageName = getPackageName();
            PackageManager packageManager = getPackageManager();
            Signature[] signatures = packageManager.getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES).signatures;
            // For each signature create a compatible hash
            for (Signature signature : signatures) {
                String hash = hash(packageName, signature.toCharsString());
                if (hash != null) {
                    appCodes.add(String.format("%s", hash));
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Unable to find package to obtain hash.", e);
        }
        return appCodes;
    }

}
