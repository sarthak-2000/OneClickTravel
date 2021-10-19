package com.example.oneclicktravel.Activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.SparseIntArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public abstract class RuntimePermissionsActivity extends AppCompatActivity {

    private SparseIntArray mErrorString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mErrorString=new SparseIntArray();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            onPermissionsGranted(requestCode);
        } else {
            Snackbar.make(findViewById(android.R.id.content), mErrorString.get(requestCode),
                    Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                        }

                     //   private void startActivity(Intent intent) { }
                    }).show();
        }
    }
    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar.make(findViewById(android.R.id.content), stringId,
                        Snackbar.LENGTH_INDEFINITE).setAction("GRANT",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(RuntimePermissionsActivity.this, requestedPermissions, requestCode);
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        } else {
            onPermissionsGranted(requestCode);
        }
    }
    public abstract void onPermissionsGranted(int requestCode);

//    @Override
//    public AssetManager getAssets() {
//        return null;
//    }
//
//    @Override
//    public Resources getResources() {
//        return null;
//    }
//
//    @Override
//    public PackageManager getPackageManager() {
//        return null;
//    }
//
//    @Override
//    public ContentResolver getContentResolver() {
//        return null;
//    }
//
//    @Override
//    public Looper getMainLooper() {
//        return null;
//    }
//
//    @Override
//    public Context getApplicationContext() {
//        return null;
//    }
//
//    @Override
//    public void setTheme(int i) {
//
//    }
//
//    @Override
//    public Resources.Theme getTheme() {
//        return null;
//    }
//
//    @Override
//    public ClassLoader getClassLoader() {
//        return null;
//    }
//
//    @Override
//    public String getPackageName() {
//        return null;
//    }
//
//    @Override
//    public ApplicationInfo getApplicationInfo() {
//        return null;
//    }
//
//    @Override
//    public String getPackageResourcePath() {
//        return null;
//    }
//
//    @Override
//    public String getPackageCodePath() {
//        return null;
//    }
//
//    @Override
//    public SharedPreferences getSharedPreferences(String s, int i) {
//        return null;
//    }
//
//    @Override
//    public boolean moveSharedPreferencesFrom(Context context, String s) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteSharedPreferences(String s) {
//        return false;
//    }
//
//    @Override
//    public FileInputStream openFileInput(String s) throws FileNotFoundException {
//        return null;
//    }
//
//    @Override
//    public FileOutputStream openFileOutput(String s, int i) throws FileNotFoundException {
//        return null;
//    }
//
//    @Override
//    public boolean deleteFile(String s) {
//        return false;
//    }
//
//    @Override
//    public File getFileStreamPath(String s) {
//        return null;
//    }
//
//    @Override
//    public File getDataDir() {
//        return null;
//    }
//
//    @Override
//    public File getFilesDir() {
//        return null;
//    }
//
//    @Override
//    public File getNoBackupFilesDir() {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public File getExternalFilesDir(@Nullable String s) {
//        return null;
//    }
//
//    @Override
//    public File[] getExternalFilesDirs(String s) {
//        return new File[0];
//    }
//
//    @Override
//    public File getObbDir() {
//        return null;
//    }
//
//    @Override
//    public File[] getObbDirs() {
//        return new File[0];
//    }
//
//    @Override
//    public File getCacheDir() {
//        return null;
//    }
//
//    @Override
//    public File getCodeCacheDir() {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public File getExternalCacheDir() {
//        return null;
//    }
//
//    @Override
//    public File[] getExternalCacheDirs() {
//        return new File[0];
//    }
//
//    @Override
//    public File[] getExternalMediaDirs() {
//        return new File[0];
//    }
//
//    @Override
//    public String[] fileList() {
//        return new String[0];
//    }
//
//    @Override
//    public File getDir(String s, int i) {
//        return null;
//    }
//
//    @Override
//    public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory) {
//        return null;
//    }
//
//    @Override
//    public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory, @Nullable DatabaseErrorHandler databaseErrorHandler) {
//        return null;
//    }
//
//    @Override
//    public boolean moveDatabaseFrom(Context context, String s) {
//        return false;
//    }
//
//    @Override
//    public boolean deleteDatabase(String s) {
//        return false;
//    }
//
//    @Override
//    public File getDatabasePath(String s) {
//        return null;
//    }
//
//    @Override
//    public String[] databaseList() {
//        return new String[0];
//    }
//
//    @Override
//    public Drawable getWallpaper() {
//        return null;
//    }
//
//    @Override
//    public Drawable peekWallpaper() {
//        return null;
//    }
//
//    @Override
//    public int getWallpaperDesiredMinimumWidth() {
//        return 0;
//    }
//
//    @Override
//    public int getWallpaperDesiredMinimumHeight() {
//        return 0;
//    }
//
//    @Override
//    public void setWallpaper(Bitmap bitmap) throws IOException {
//
//    }
//
//    @Override
//    public void setWallpaper(InputStream inputStream) throws IOException {
//
//    }
//
//    @Override
//    public void clearWallpaper() throws IOException {
//
//    }
//
//    @Override
//    public void startActivity(Intent intent) {
//
//    }
//
//    @Override
//    public void startActivity(Intent intent, @Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void startActivities(Intent[] intents) {
//
//    }
//
//    @Override
//    public void startActivities(Intent[] intents, Bundle bundle) {
//
//    }
//
//    @Override
//    public void startIntentSender(IntentSender intentSender, @Nullable Intent intent, int i, int i1, int i2) throws IntentSender.SendIntentException {
//
//    }
//
//    @Override
//    public void startIntentSender(IntentSender intentSender, @Nullable Intent intent, int i, int i1, int i2, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
//
//    }
//
//    @Override
//    public void sendBroadcast(Intent intent) {
//
//    }
//
//    @Override
//    public void sendBroadcast(Intent intent, @Nullable String s) {
//
//    }
//
//    @Override
//    public void sendOrderedBroadcast(Intent intent, @Nullable String s) {
//
//    }
//
//    @Override
//    public void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String s, @Nullable BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s1, @Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void sendBroadcastAsUser(Intent intent, UserHandle userHandle) {
//
//    }
//
//    @Override
//    public void sendBroadcastAsUser(Intent intent, UserHandle userHandle, @Nullable String s) {
//
//    }
//
//    @Override
//    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, @Nullable String s, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s1, @Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void sendStickyBroadcast(Intent intent) {
//
//    }
//
//    @Override
//    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s, @Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void removeStickyBroadcast(Intent intent) {
//
//    }
//
//    @Override
//    public void sendStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {
//
//    }
//
//    @Override
//    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s, @Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void removeStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {
//
//    }
//
//    @Nullable
//    @Override
//    public Intent registerReceiver(@Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Intent registerReceiver(@Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable String s, @Nullable Handler handler) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable String s, @Nullable Handler handler, int i) {
//        return null;
//    }
//
//    @Override
//    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
//
//    }
//
//    @Nullable
//    @Override
//    public ComponentName startService(Intent intent) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public ComponentName startForegroundService(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public boolean stopService(Intent intent) {
//        return false;
//    }
//
//    @Override
//    public boolean bindService(Intent intent, @NonNull ServiceConnection serviceConnection, int i) {
//        return false;
//    }
//
//    @Override
//    public void unbindService(@NonNull ServiceConnection serviceConnection) {
//
//    }
//
//    @Override
//    public boolean startInstrumentation(@NonNull ComponentName componentName, @Nullable String s, @Nullable Bundle bundle) {
//        return false;
//    }
//
//    @Override
//    public Object getSystemService(@NonNull String s) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public String getSystemServiceName(@NonNull Class<?> aClass) {
//        return null;
//    }
//
//    @SuppressLint("WrongConstant")
//    @Override
//    public int checkPermission(@NonNull String s, int i, int i1) {
//        return 0;
//    }
//
//    @Override
//    public int checkCallingPermission(@NonNull String s) {
//        return 0;
//    }
//
//    @Override
//    public int checkCallingOrSelfPermission(@NonNull String s) {
//        return 0;
//    }
//
//    @Override
//    public int checkSelfPermission(@NonNull String s) {
//        return 0;
//    }
//
//    @Override
//    public void enforcePermission(@NonNull String s, int i, int i1, @Nullable String s1) {
//
//    }
//
//    @Override
//    public void enforceCallingPermission(@NonNull String s, @Nullable String s1) {
//
//    }
//
//    @Override
//    public void enforceCallingOrSelfPermission(@NonNull String s, @Nullable String s1) {
//
//    }
//
//    @Override
//    public void grantUriPermission(String s, Uri uri, int i) {
//
//    }
//
//    @Override
//    public void revokeUriPermission(Uri uri, int i) {
//
//    }
//
//    @Override
//    public void revokeUriPermission(String s, Uri uri, int i) {
//
//    }
//
//    @Override
//    public int checkUriPermission(Uri uri, int i, int i1, int i2) {
//        return 0;
//    }
//
//    @Override
//    public int checkCallingUriPermission(Uri uri, int i) {
//        return 0;
//    }
//
//    @Override
//    public int checkCallingOrSelfUriPermission(Uri uri, int i) {
//        return 0;
//    }
//
//    @Override
//    public int checkUriPermission(@Nullable Uri uri, @Nullable String s, @Nullable String s1, int i, int i1, int i2) {
//        return 0;
//    }
//
//    @Override
//    public void enforceUriPermission(Uri uri, int i, int i1, int i2, String s) {
//
//    }
//
//    @Override
//    public void enforceCallingUriPermission(Uri uri, int i, String s) {
//
//    }
//
//    @Override
//    public void enforceCallingOrSelfUriPermission(Uri uri, int i, String s) {
//
//    }
//
//    @Override
//    public void enforceUriPermission(@Nullable Uri uri, @Nullable String s, @Nullable String s1, int i, int i1, int i2, @Nullable String s2) {
//
//    }
//
//    @Override
//    public Context createPackageContext(String s, int i) throws PackageManager.NameNotFoundException {
//        return null;
//    }
//
//    @Override
//    public Context createContextForSplit(String s) throws PackageManager.NameNotFoundException {
//        return null;
//    }
//
//    @Override
//    public Context createConfigurationContext(@NonNull Configuration configuration) {
//        return null;
//    }
//
//    @Override
//    public Context createDisplayContext(@NonNull Display display) {
//        return null;
//    }
//
//    @Override
//    public Context createDeviceProtectedStorageContext() {
//        return null;
//    }
//
//    @Override
//    public boolean isDeviceProtectedStorage() {
//        return false;
//    }
}
