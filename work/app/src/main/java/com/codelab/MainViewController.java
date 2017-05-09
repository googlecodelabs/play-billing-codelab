/*
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelab;

import android.content.SharedPreferences;
import android.support.annotation.DrawableRes;
import android.util.Log;

import com.codelab.sample.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Handles control logic of the GamePlayActivity
 */
public class MainViewController {
    private static final String TAG = "MainViewController";

    // Graphics for the gas gauge
    private static int[] TANK_RES_IDS = { R.drawable.gas0, R.drawable.gas1,
            R.drawable.gas2, R.drawable.gas3, R.drawable.gas4 };

    // How many units (1/4 tank is our unit) fill in the tank.
    private static final int TANK_MAX = 4;

    private GamePlayActivity mActivity;

    // Current amount of gas in tank, in units
    private int mTank;

    public MainViewController(GamePlayActivity activity) {
        mActivity = activity;
        loadData();
    }

    public void useGas() {
        mTank--;
        saveData();
        Log.d(TAG, "Tank is now: " + mTank);
    }

    public boolean isTankEmpty() {
        return mTank <= 0;
    }

    public boolean isTankFull() {
        return mTank >= TANK_MAX;
    }

    public @DrawableRes int getTankResId() {
        int index = (mTank >= TANK_RES_IDS.length) ? (TANK_RES_IDS.length - 1) : mTank;
        return TANK_RES_IDS[index];
    }

    /**
     * Save current tank level to disc
     *
     * Note: In a real application, we recommend you save data in a secure way to
     * prevent tampering.
     * For simplicity in this sample, we simply store the data using a
     * SharedPreferences.
     */
    private void saveData() {
        SharedPreferences.Editor spe = mActivity.getPreferences(MODE_PRIVATE).edit();
        spe.putInt("tank", mTank);
        spe.apply();
        Log.d(TAG, "Saved data: tank = " + String.valueOf(mTank));
    }

    private void loadData() {
        SharedPreferences sp = mActivity.getPreferences(MODE_PRIVATE);
        mTank = sp.getInt("tank", 2);
        Log.d(TAG, "Loaded data: tank = " + String.valueOf(mTank));
    }
}