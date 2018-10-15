package com.hs3.commons;

public class Constants {


    public static Boolean OPEN_BONUS_RISK = true;
    public static int RISK_BONUS_CHANGE_COUNT = 5;
    public static boolean REV = false;
    public static boolean MATRIX = false;


    public Constants(boolean openBonusRisk, int riskBonusChangeCount, boolean rev, boolean matrix) {
        OPEN_BONUS_RISK = openBonusRisk;
        RISK_BONUS_CHANGE_COUNT = riskBonusChangeCount;
        REV = rev;
        MATRIX = matrix;
    }

    public Constants(Boolean rev, Boolean matrix) {
        REV = rev;
        MATRIX = matrix;
    }

    public Constants(Boolean rev) {
        REV = rev;
    }

    public static void setOpenBonusRisk(Boolean status) {
        OPEN_BONUS_RISK = status;
    }

    public static void setRiskBonusChangeCount(int count) {
        RISK_BONUS_CHANGE_COUNT = count;
    }

    public static void setREV(Boolean status) {
        REV = status;
    }

    public static void setMATRIX(boolean status) {
        MATRIX = status;
    }
}
