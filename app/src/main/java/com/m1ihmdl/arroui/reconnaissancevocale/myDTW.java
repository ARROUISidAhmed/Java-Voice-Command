package com.m1ihmdl.arroui.reconnaissancevocale;

import fr.enseeiht.danck.voice_analyzer.DTWHelper;
import fr.enseeiht.danck.voice_analyzer.Field;

public class myDTW extends DTWHelper {

    @Override
    public float DTWDistance(Field unknown, Field known) {
        float[][] distanceMatrix = new float[unknown.getLength()][known.getLength()];
        myMFCCdistance myMfccDistance = new myMFCCdistance();
        for (int i = 0; i < unknown.getLength(); i++) {
            for (int j = 0; j < known.getLength(); j++) {
                distanceMatrix[i][j] = myMfccDistance.distance(unknown.getMFCC(i), known.getMFCC(j));
            }
        }

        float w0 = 1;
        float w1 = 2;
        float w2 = 1;
        float[][] g = new float[unknown.getLength() + 1][known.getLength() + 1];

        g[0][0] = 0;
        for (int j = 1; j < known.getLength(); j++) {
            g[0][j] = Float.POSITIVE_INFINITY;
        }

        for (int i = 1; i <= unknown.getLength(); i++) {
            g[i][0] = Float.POSITIVE_INFINITY;
            for (int j = 1; j <= known.getLength(); j++) {
                float dij = distanceMatrix[i - 1][j - 1];
                g[i][j] = Float.min(g[i - 1][j] + w0 * dij,
                        Float.min(g[i - 1][j - 1] + w1 * dij, g[i][j - 1] + w2 * dij));
            }
        }
        return g[unknown.getLength()][known.getLength()] / (unknown.getLength() + known.getLength());
    }

}
