package mathcomp.oletsky.pagerank;

/**
 * @author Oleksiy Oletsky
 */

import mathcomp.oletsky.mathhelper.SLAUSolver;
import mathcomp.oletsky.mathhelper.VectMatr;

import java.util.Arrays;

public class MarkovWalking {

    /**
     * Getting stationary distribution of Markov chain
     * @param matr - matrix of ttansitional probabilities
     * @return
     */
    public static double[] getStationaryDistribution(double[][] matr) {
        int n=matr.length;
        double[][] workMatr = new double[n+1][n];
        double[][] transposed = VectMatr.transposeSquareMatrix(matr);
        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++) {
                workMatr[i][j] = transposed[i][j];
                if (i==j) workMatr[i][j]-=1.;
            }
        for (int j=0; j<n; j++) {
            workMatr[n][j]=1;
        }
        double[] workVect = new double[n+1];
        workVect[n]=1;
        double[] res = SLAUSolver.solveSLAR(workMatr,
                workVect,
                SLAUSolver.SolverType.SVD);
        return res;
    }

    /**
     *
     * @param graph - navigation graph
     * @param beta
     * @return
     */
    public static double[][] obtainTransMatrFromGraph(int[][] graph,
                                                      double beta) {
        int m = graph.length;
        int n = graph[0].length;
        double[][] res=new double[m][n];
        for (int i=0; i<m; i++) {
            int s=0;
            double[] v=new double[n];
            for (int j=0; j<n; j++) {
                s+=graph[i][j];
            }
            if (s==0) Arrays.fill(res[i],0.);
            else {
                for (int j = 0; j < n; j++) {
                    res[i][j] = graph[i][j] / (s+0.);
                }
            }

            for (int j = 0; j <n ; j++) {
                res[i][j]=beta*res[i][j]+(1.-beta)/n;
            }
        }
        return res;

    }


}

