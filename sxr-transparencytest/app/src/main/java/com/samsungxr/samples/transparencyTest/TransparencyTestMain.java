package com.samsungxr.samples.transparencyTest;

import android.view.Gravity;

import com.samsungxr.SXRContext;
import com.samsungxr.SXRMain;
import com.samsungxr.SXRRenderData;
import com.samsungxr.SXRScene;
import com.samsungxr.nodes.SXRTextViewNode;

public class TransparencyTestMain extends SXRMain {

    private SXRScene mScene;

    @Override
    public void onInit(SXRContext sxrContext) {
        mScene = sxrContext.getNextMainScene();

        SXRTextViewNode helloNode = new SXRTextViewNode(sxrContext, "H___________");
        helloNode.setGravity(Gravity.CENTER);
        helloNode.setTextSize(10);
        helloNode.getTransform().setPosition(0.0f, 0.0f, -2.11f);

        // since we didn't mark this one as transparent, it will go in the Geometry bin
        mScene.addNode(helloNode);
        
        // The rest of these will be marked transparent.
        addString("________r___", -2.07f);
        addString("_e__________", -2.01f);
        addString("___________!", -2.10f);
        addString("______W_____", -2.05f);
        addString("___l________", -2.03f);
        addString("__l_________", -2.02f);
        addString("____o_______", -2.04f);
        addString("_______o____", -2.06f);
        addString("_________l__", -2.08f);
        addString("__________d_", -2.09f);
    }
    
    private void addString(String string, float distance) {
        SXRTextViewNode sceneObject = new SXRTextViewNode(getSXRContext(), string);

        sceneObject.setGravity(Gravity.CENTER);
        sceneObject.setTextSize(10);
        sceneObject.getTransform().setPosition(0.0f, 0.0f, distance);
        sceneObject.getRenderData().setRenderingOrder(SXRRenderData.SXRRenderingOrder.TRANSPARENT);
        sceneObject.getRenderData().setAlphaBlend(true);
        
        mScene.addNode(sceneObject);
    }

    @Override
    public void onStep() {
    }
}
