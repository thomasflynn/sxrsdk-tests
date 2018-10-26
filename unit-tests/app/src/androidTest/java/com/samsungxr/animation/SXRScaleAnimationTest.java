package com.samsungxr.animation;

import android.util.Log;

import com.samsungxr.ActivityInstrumentationSXRf;
import com.samsungxr.SXRTestActivity;
import com.samsungxr.viewmanager.TestDefaultSXRViewManager;

import com.samsungxr.SXRNode;

public class SXRScaleAnimationTest extends ActivityInstrumentationSXRf {

    private final String TAG = SXRScaleAnimationTest.class.getSimpleName();

    private final float duration = 1.0f;
    private final float x = 1.0f;
    private final float y = 2.0f;
    private final float z = 3.0f;

    public SXRScaleAnimationTest() {
        super(SXRTestActivity.class);
    }

    public void testConstructorSXRScaleAnimationTransform1(){
        Log.d(TAG, "starting testConstructorSXRScaleAnimationTransform1");

        SXRNode sceneObject = new SXRNode(TestDefaultSXRViewManager.mSXRContext);
        SXRScaleAnimation scaleAnimation = new SXRScaleAnimation(sceneObject.getTransform(), duration, y);
        assertNotNull(scaleAnimation);

      }

    public void testConstructorSXRScaleAnimationNode1(){
        Log.d(TAG, "starting testConstructorSXRScaleAnimationNode1");

        SXRNode sceneObject = new SXRNode(TestDefaultSXRViewManager.mSXRContext);
        SXRScaleAnimation scaleAnimation = new SXRScaleAnimation(sceneObject, duration, y);
        assertNotNull(scaleAnimation);

    }


    public void testSetInvalidRepeatModeAnimation() {

        try {
            SXRNode sceneObject = new SXRNode(TestDefaultSXRViewManager.mSXRContext);
            new SXRRotationByAxisAnimation
                    (sceneObject, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f).setRepeatMode(2);

            SXRRotationByAxisWithPivotAnimation animation = new SXRRotationByAxisWithPivotAnimation(sceneObject,
                    1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
            animation.setRepeatMode(4);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "4 is not a valid repetition type");
        }
    }

    public void testInterpolatorAnimation() {

        SXRNode sceneObject = new SXRNode(TestDefaultSXRViewManager.mSXRContext);
        new SXRRotationByAxisAnimation
                (sceneObject, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f).setRepeatMode(2);

        SXRScaleAnimation animation = new SXRScaleAnimation(sceneObject, 1.0f, 1.0f, 1.0f, 1.0f);
        animation.setInterpolator(new SXRInterpolator() {
            @Override
            public float mapRatio(float ratio) {

                assertNotNull(ratio);

                return 0;
            }
        });
    }

    public void testSetFinishedObject() {
        SXRNode sceneObject = new SXRNode(TestDefaultSXRViewManager.mSXRContext);
        new SXRRotationByAxisAnimation
                (sceneObject, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f).setRepeatMode(2);

        SXRScaleAnimation animation = new SXRScaleAnimation(sceneObject, 1.0f, 1.0f, 1.0f, 1.0f);
        animation.setOnFinish(new SXROnFinish() {
            @Override
            public void finished(SXRAnimation animation) {
                assertNotNull(animation);
            }
        });
    }

    public void testSetRepeatCount() {
        SXRNode sceneObject = new SXRNode(TestDefaultSXRViewManager.mSXRContext);
        new SXRRotationByAxisAnimation
                (sceneObject, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f).setRepeatMode(2);

        SXRScaleAnimation animation = new SXRScaleAnimation(sceneObject, 1.0f, 1.0f, 1.0f, 1.0f);
        animation.setRepeatCount(10);
    }
}
