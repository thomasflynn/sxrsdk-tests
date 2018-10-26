/* Copyright 2015 Samsung Electronics Co., LTD
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samsungxr.viewmanager.controls.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.samsungxr.SXRAndroidResource;
import com.samsungxr.SXRCameraRig;
import com.samsungxr.SXRContext;
import com.samsungxr.SXRNode;
import com.samsungxr.SXRTexture;
import com.samsungxr.SXRTransform;
import com.samsungxr.tests.R;

public class Util {

    private static final String TAG = "vr-controls";
    private static final boolean SHOW_LOG = true;

    public static float[] calculatePointBetweenTwoObjects(SXRNode object1,
            SXRNode object2, float desiredDistance) {
        float[] point = new float[3];
        float ratio = desiredDistance / (float) distance(object1, object2);
        point[0] = (1 - ratio) * object1.getTransform().getPositionX() + (ratio)
                * object2.getTransform().getPositionX();
        point[1] = (1 - ratio) * object1.getTransform().getPositionY() + (ratio)
                * object2.getTransform().getPositionY();
        point[2] = (1 - ratio) * object1.getTransform().getPositionZ() + (ratio)
                * object2.getTransform().getPositionZ();

        return point;
    }

    public static double distance(SXRNode object1, SXRNode object2) {
        return Math.sqrt(Math.pow(object1.getTransform().getPositionX()
                - object2.getTransform().getPositionX(), 2)
                +
                Math.pow(object1.getTransform().getPositionY()
                        - object2.getTransform().getPositionY(), 2)
                +
                Math.pow(object1.getTransform().getPositionZ()
                        - object2.getTransform().getPositionZ(), 2));

    }

    public static double distance(SXRTransform object1, SXRTransform object2) {
        return Math.sqrt(Math.pow(object1.getPositionX() - object2.getPositionX(), 2) +
                Math.pow(object1.getPositionY() - object2.getPositionY(), 2) +
                Math.pow(object1.getPositionZ() - object2.getPositionZ(), 2));

    }

    public static float[] calculatePointBetweenTwoObjects(SXRTransform transform,
            SXRTransform transform2, float distance) {
        float[] point = new float[3];
        float ratio = distance / (float) distance(transform, transform2);
        point[0] = (1 - ratio) * transform.getPositionX() + (ratio)
                * transform2.getPositionX();
        point[1] = (1 - ratio) * transform.getPositionY() + (ratio)
                * transform2.getPositionY();
        point[2] = (1 - ratio) * transform.getPositionZ() + (ratio)
                * transform2.getPositionZ();

        return point;
    }

    public static void Toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void Log(String message) {
        if (SHOW_LOG == false)
            return;
        Log.i(TAG, message);
    }

    public static void Log(String showingTag, String message) {
        if (SHOW_LOG == false)
            return;
        Log.i(showingTag, message);
    }

    public static SXRTexture transparentTexture(SXRContext context) {
        return context.loadTexture(new SXRAndroidResource(context, R.raw.empty));
    }

    public static float getYRotationAngle(SXRNode rotatingObject, SXRNode targetObject) {
        return (float) Math.toDegrees(Math.atan2(targetObject.getTransform().getPositionX()
                - rotatingObject.getTransform().getPositionX(),
                targetObject.getTransform().getPositionZ()
                        - rotatingObject.getTransform().getPositionZ()));
    }

    public static float getYRotationAngle(SXRNode rotatingObject, SXRCameraRig targetObject) {
        return (float) Math.toDegrees(Math.atan2(targetObject.getTransform().getPositionX()
                - rotatingObject.getTransform().getPositionX(),
                targetObject.getTransform().getPositionZ()
                        - rotatingObject.getTransform().getPositionZ()));
    }

}
