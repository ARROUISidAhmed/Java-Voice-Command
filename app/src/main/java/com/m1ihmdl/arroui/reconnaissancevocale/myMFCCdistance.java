package com.m1ihmdl.arroui.reconnaissancevocale;

import fr.enseeiht.danck.voice_analyzer.MFCC;
import fr.enseeiht.danck.voice_analyzer.MFCCHelper;

public class myMFCCdistance extends MFCCHelper {

    @Override
    public float distance(MFCC mfcc1, MFCC mfcc2) {
        // calcule la distance entre 2 MFCC
        float sum = 0;
        float temp;
        for (int i = 0; i < mfcc1.getLength(); i++) {
            temp = mfcc1.getCoef(i) - mfcc2.getCoef(i);
            temp = temp * temp;
            sum += temp;
        }
        return (float) Math.sqrt((double) sum);
    }

    @Override
    public float norm(MFCC mfcc) {
        // retourne la valeur de mesure de la MFCC (coef d'indice 0 dans la MFCC)
        // cette mesure permet de determiner s'il s'agit d'un mot ou d'un silence
        return mfcc.getCoef(0);
    }

    @Override
    public MFCC unnoise(MFCC mfcc, MFCC noise) {
        // supprime le bruit de la MFCC passee en parametre
        // soustrait chaque coef du bruit a chaque coef du la MFCC
        // passee en parametre
        float[] coefs = new float[mfcc.getLength()];
        float[] signal = mfcc.getSignal().clone();
        for (int i = 0; i < mfcc.getLength(); i++) {
            coefs[i] = mfcc.getCoef(i) - noise.getCoef(i);
        }

        MFCC result = new MFCC(coefs, signal);
        return result;
    }

}
