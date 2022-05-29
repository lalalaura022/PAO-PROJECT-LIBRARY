package Servicii;

import CLASE.MENIU;

public class MAIN {

    public static void main(String[] args) throws Exception {
        MENIU meniu = MENIU.getInstance();
        meniu.start();
    }

}
