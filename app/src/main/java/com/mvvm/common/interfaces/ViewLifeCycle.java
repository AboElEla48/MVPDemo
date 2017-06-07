package com.mvvm.common.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This interface for mapping the needed life cycle for activities and fragments
 */

public interface ViewLifeCycle
{
    void onCreate(@Nullable Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onDestroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);
}
