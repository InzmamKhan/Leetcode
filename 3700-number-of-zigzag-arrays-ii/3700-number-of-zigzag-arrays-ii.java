class Solution {
    private static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int K = r - l + 1;
        if (n == 1) return K;

        int size = 2 * K;
        
        long[] baseVec = new long[size];
        for (int v = 0; v < K; v++) {
            baseVec[2 * v] = v;              
            baseVec[2 * v + 1] = K - 1 - v;  
        }

        long[][] T = new long[size][size];
        for (int v = 0; v < K; v++) {
            for (int vp = 0; vp < v; vp++) {
                T[2 * vp + 1][2 * v] = 1;
            }
            for (int vp = v + 1; vp < K; vp++) {
                T[2 * vp][2 * v + 1] = 1;
            }
        }

        long[][] T_pow = matrixPower(T, n - 2, size);

        long[] finalVec = multiplyMatrixVector(T_pow, baseVec, size);

        long total = 0;
        for (long val : finalVec) {
            total = (total + val) % MOD;
        }

        return (int) total;
    }

    private long[][] matrixPower(long[][] matrix, int p, int size) {
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) res[i][i] = 1; 

        long[][] base = matrix;
        while (p > 0) {
            if ((p & 1) == 1) {
                res = multiplyMatrices(res, base, size);
            }
            base = multiplyMatrices(base, base, size);
            p >>= 1;
        }
        return res;
    }

    private long[][] multiplyMatrices(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private long[] multiplyMatrixVector(long[][] M, long[] V, int size) {
        long[] res = new long[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i] = (res[i] + M[i][j] * V[j]) % MOD;
            }
        }
        return res;
    }
}