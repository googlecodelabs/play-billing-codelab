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
package com.codelab.billing;

import android.app.Activity;
import android.content.Context;

import com.android.billingclient.api.PurchasesUpdatedListener;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.Purchase;

import java.util.List;

/**
 * TODO: Implement BillingManager that will handle all the interactions with Play Store
 * (via Billing library), maintain connection to it through BillingClient and cache
 * temporary states/data if needed.
 */
public class BillingManager implements PurchasesUpdatedListener {
    private static final String TAG = "BillingManager";

    private final BillingClient mBillingClient;
    private final Activity mActivity;

    public BillingManager(Activity activity) {
        mActivity = activity;
        mBillingClient = new BillingClient.Builder(mActivity).setListener(this).build();
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponse) {
                if (billingResponse == BillingClient.BillingResponse.OK) {
                    Log.i(TAG, "onBillingSetupFinished() response: " + billingResponse);
                } else {
                    Log.w(TAG, "onBillingSetupFinished() error code: " + billingResponse);
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                Log.w(TAG, "onBillingServiceDisconnected()");
            }
        });
    }

    @Override
    public void onPurchasesUpdated(int responseCode, List<Purchase> purchases) {
        Log.i(TAG, "onPurchasesUpdated() response: " + responseCode);
    }

    public void startPurchaseFlow(String skuId, String billingType) {
        // TODO: Implement launch billing flow here
    }
}
