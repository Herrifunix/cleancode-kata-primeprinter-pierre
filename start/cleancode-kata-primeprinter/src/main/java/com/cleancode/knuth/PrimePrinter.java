package com.cleancode.knuth;

class PrimePrinterHelper {
    private final int M;
    private final int RR;
    private final int CC;
    private final int ORDMAX = 30;
    private final int[] P;
    private final int[] MULT;

    public PrimePrinterHelper(int M, int RR, int CC) {
        this.M = M;
        this.RR = RR;
        this.CC = CC;
        this.P = new int[M + 1];
        this.MULT = new int[ORDMAX + 1];
    }

    public void generatePrimes() {
        int J = 1;
        int K = 1;
        P[1] = 2;
        int ORD = 2;
        int SQUARE = 9;
        boolean JPRIME;

        while (K < M) {
            do {
                J += 2;
                if (J == SQUARE) {
                    ORD++;
                    SQUARE = P[ORD] * P[ORD];
                    MULT[ORD - 1] = J;
                }
                int N = 2;
                JPRIME = true;
                while (N < ORD && JPRIME) {
                    while (MULT[N] < J)
                        MULT[N] += P[N] + P[N];
                    if (MULT[N] == J)
                        JPRIME = false;
                    N++;
                }
            } while (!JPRIME);
            K++;
            P[K] = J;
        }
    }

    public void printPrimes() {
        int PAGENUMBER = 1;
        int PAGEOFFSET = 1;

        while (PAGEOFFSET <= M) {
            System.out.print("The First ");
            System.out.print(M);
            System.out.print(" Prime Numbers === Page ");
            System.out.print(PAGENUMBER);
            System.out.println("\n");

            for (int ROWOFFSET = PAGEOFFSET; ROWOFFSET <= PAGEOFFSET + RR - 1 && ROWOFFSET <= M; ROWOFFSET++) {
                for (int C = 0; C <= CC - 1; C++) {
                    if (ROWOFFSET + C * RR <= M)
                        System.out.printf("%10d", P[ROWOFFSET + C * RR]);
                }
                System.out.println();
            }
            System.out.println("\f");
            PAGENUMBER++;
            PAGEOFFSET += RR * CC;
        }
    }
}

public class PrimePrinter {
    public static void main(String[] args) {
        final int M = 1000;
        final int RR = 50;
        final int CC = 4;
        PrimePrinterHelper primePrinterHelper = new PrimePrinterHelper(M, RR, CC);
        primePrinterHelper.generatePrimes();
        primePrinterHelper.printPrimes();
    }
}
