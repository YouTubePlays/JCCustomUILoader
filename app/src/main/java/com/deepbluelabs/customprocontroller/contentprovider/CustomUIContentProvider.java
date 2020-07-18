package com.deepbluelabs.customprocontroller.contentprovider;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.deepbluelabs.customprocontroller.R;

public class CustomUIContentProvider extends ContentProvider {
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String TYPE = "TYPE";
    private static final String VERSION = "VERSION";
    private static final String ENTRY = "ENTRY";
    private static final String APP_VERSION = "APP_VERSION";

    private static final String PRO_CONTROLLER = "PRO_CONTROLLER";
    private static final String LEFT_JOYCON = "LEFT_JOYCON";
    private static final String RIGHT_JOYCON = "RIGHT_JOYCON";

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Object[] columns = new Object[projection.length];
        final MatrixCursor cursor = new MatrixCursor(projection, 1);
        for (int i = 0; i < projection.length; i++) {
            switch (projection[i]) {
                case ID:
                    columns[i] = 0;
                    break;
                case NAME:
                    columns[i] = getContext().getString(R.string.app_name);
                    break;
                case TYPE:
                    columns[i] = RIGHT_JOYCON;
                    break;
                case VERSION:
                    columns[i] = 1;
                    break;
                case APP_VERSION:
                    columns[i] = 97;
                    break;
                case ENTRY:
                    columns[i] = "index.html";
                    break;
                default:
                    break;
            }
        }
        cursor.addRow(columns);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "application/octet-stream";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public AssetFileDescriptor openAssetFile(@NonNull Uri uri, @NonNull String mode) {
        return getContext()
                .getResources()
                .openRawResourceFd(R.raw.nes);
    }
}
