package com.example.changxiying.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class BroadcastTest {
    private final String action = "com.changxiying.myapplication.receiver";
    private PackageManager packageManager;
    @Test
    public void testRegister() throws Exception {
        Intent intent = new Intent(action);
//        hasReceiverForIntent已被废弃，新api用PackageManager.queryBroadcastReceivers
        ShadowApplication shadowApplication = ShadowApplication.getInstance();
        Assert.assertTrue(shadowApplication.hasReceiverForIntent(intent));
//        intent.setPackage(RuntimeEnvironment.application.getPackageName());
//        List<ResolveInfo> receiverInfos = packageManager.queryBroadcastReceivers(intent, );
//        assertThat(receiverInfos).isNotEmpty();
    }


}
