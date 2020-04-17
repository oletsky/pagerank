package mathcomp.oletsky.pagerank;
import mathcomp.oletsky.mathhelper.VectMatr;

import java.util.Arrays;

public class PageRankTester {
    public static void main(String[] args) {
        double beta=0.85;
        int [][] navGraph={
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0}

        };
        double[][] transMatr = MarkovWalking.obtainTransMatrFromGraph(
                navGraph, beta
        );
        double[] ranks= MarkovWalking.getStationaryDistribution(transMatr);
        VectMatr.defaultOutputVector(ranks);

    }


}
