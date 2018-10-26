package com.samsungxr.utility;

import com.samsungxr.ActivityInstrumentationSXRf;
import com.samsungxr.SXRAndroidResource;
import com.samsungxr.SXRTestActivity;
import com.samsungxr.SXRTexture;
import com.samsungxr.R;
import com.samsungxr.animation.SXRAnimationEngine;
import com.samsungxr.animation.SXRRepeatMode;
import com.samsungxr.animation.SXRScaleAnimation;
import com.samsungxr.nodes.SXRSphereNode;
import com.samsungxr.viewmanager.TestDefaultSXRViewManager;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by j.elidelson on 10/9/2015.
 */
public class TheadsTest extends ActivityInstrumentationSXRf {

    public TheadsTest() {
        super(SXRTestActivity.class);
    }

    public void testSpawn(){

            Runnable pulse = new Runnable() {

                public void run() {
                    int i=0;
                    int j=1;
                    j=j/i;
                }
            };
            Future fut1 = Threads.spawn(pulse);
            assertNotNull(fut1);
    }

    public void testSpawnLow(){

        Runnable pulse = new Runnable() {

            public void run() {
            }
        };
        Future fut = Threads.spawnLow(pulse);
        assertNotNull(fut);
    }

    public void testSpawnHigh(){

        Runnable pulse = new Runnable() {

            public void run() {
            }
        };
        Future fut = Threads.spawnHigh(pulse);
        assertNotNull(fut);
    }


    public void testSpawnIdle(){

        Runnable pulse = new Runnable() {

            public void run() {
            }
        };
        Future fut = Threads.spawnIdle(pulse);
        assertNotNull(fut);
        Future fut2 = Threads.spawnIdle(null);
    }


    public void testSetThreadPool(){

        Threads.setThreadPool(Threads.getThreadPool());
        try {
            Threads.setThreadPool(null);
            fail("Should throws exception...");
        }catch (IllegalArgumentException e){}
    }


    public void testThreadId(){

       assertNotNull(Threads.threadId());
    }


    public void testassertUnlocked(){

        Runnable pulse = new Runnable() {

            public void run() {
                int a=0;
                a=1/a;
            }
        };
        Future fut = Threads.spawnIdle(pulse);
        Threads.assertUnlocked(fut,"test");

        Threads.LifoThreadPolicyProvider lifoThreadPolicyProvider = new Threads.LifoThreadPolicyProvider();

    }


    public void testLifoThreadPolicyProvider(){

        Runnable pulse = new Runnable() {

            public void run() {
                int a=0;
                a=1/a;
            }
        };
        Future fut = Threads.spawnIdle(pulse);
        Threads.assertUnlocked(fut, "test");

        Threads.LifoThreadPolicyProvider lifoThreadPolicyProvider = new Threads.LifoThreadPolicyProvider();
        Threads.Cancelable proc = new Threads.Cancelable() {
            @Override
            public boolean stillWanted() {
                return false;
            }

            @Override
            public void run() {

            }
        };
        lifoThreadPolicyProvider.reschedule(proc);
        lifoThreadPolicyProvider.put(proc);
        assertNotNull(lifoThreadPolicyProvider.get());
        assertEquals(true, lifoThreadPolicyProvider.isEmpty());
        lifoThreadPolicyProvider.reschedule(proc);
    }


}
