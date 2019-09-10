package co.com.jccp.ealgorithms;

import co.com.jccp.ealgorithms.algorithm.NSHAEA;
import co.com.jccp.ealgorithms.function.*;
import co.com.jccp.ealgorithms.gop.GeneticOperator;
import co.com.jccp.ealgorithms.gop.PolynomialMutation;
import co.com.jccp.ealgorithms.gop.SimulatedBinaryXover;
import co.com.jccp.ealgorithms.graphs.MatlabChart;
import co.com.jccp.ealgorithms.individual.MOEAIndividual;
import co.com.jccp.ealgorithms.initialization.RandomRealInitialization;
import co.com.jccp.ealgorithms.metrics.Convergence;
import co.com.jccp.ealgorithms.metrics.Diversity;
import co.com.jccp.ealgorithms.utils.RealCloneUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class NSHAEATest {

    @Test
    public void nshaeTest()
    {

//        SCH function = new SCH();
//        int dimensions = 1;
//        double[][] limits = new double[][]{{-1000, 1000}};
//        String fileName = "NSHAEA-SCH";
//        double[] xlim = new double[]{0, 4};
//        double[] ylim = new double[]{0, 4};

//        FON function = new FON();
//        int dimensions = 3;
//        double[][] limits = new double[][]{{-4, 4}, {-4, 4}, {-4, 4}};
//        String fileName = "NSHAEA-FON";
//        double[] xlim = new double[]{0, 1};
//        double[] ylim = new double[]{0, 1};

        POL function = new POL();
        int dimensions = 2;
        double[][] limits =  new double[][]{{-Math.PI, Math.PI}, {-Math.PI, Math.PI}};
        String fileName = "NSHAEA-POL";
        double[] xlim = new double[]{1, 17};
        double[] ylim = new double[]{0, 29};

//        KUR function = new KUR();
//        int dimensions = 3;
//        double[][] limits =  new double[][]{{-5, 5}, {-5, 5}, {-5, 5}};
//        String fileName = "NSHAEA-KUR";
//        double[] xlim = new double[]{-21, -14};
//        double[] ylim = new double[]{-12, 1};

//        ZDT1 function = new ZDT1();
//        int dimensions = 30;
//        double[][] limits = new double[dimensions][2];
//
//        for (int i = 0; i < dimensions; i++) {
//            limits[i][0] = 0.0;
//            limits[i][1] = 1.0;
//        }
//        String fileName = "NSHAEA-ZDT1";
//        double[] xlim = new double[]{0, 1};
//        double[] ylim = new double[]{0, 1};

//        ZDT2 function = new ZDT2();
//        int dimensions = 30;
//        double[][] limits = new double[dimensions][2];
//
//        for (int i = 0; i < dimensions; i++) {
//            limits[i][0] = 0.0;
//            limits[i][1] = 1.0;
//        }
//        String fileName = "NSHAEA-ZDT2";
//        double[] xlim = new double[]{0, 1};
//        double[] ylim = new double[]{0, 1};


        RandomRealInitialization init = new RandomRealInitialization(limits);

        boolean minimize = true;

        int popSize = 100;

        int iterations = 300;

        SimulatedBinaryXover sbx = new SimulatedBinaryXover(20.0, limits);

        PolynomialMutation pm = new PolynomialMutation(20.0, limits);

        List<GeneticOperator<double[]>> operators = new ArrayList<>();
        operators.add(sbx);
        operators.add(pm);

        RealCloneUtil rcu = new RealCloneUtil();

        NSHAEA<double[]> NSHAEA = new NSHAEA<>(init, function, minimize, dimensions, popSize, iterations, operators, rcu);

        List<MOEAIndividual<double[]>> answer = NSHAEA.apply();

        double[] x = new double[answer.size()];
        double[] y = new double[answer.size()];



        for (int i = 0; i < answer.size(); i++) {
            x[i] = answer.get(i).getObjectiveValues()[0];
            y[i] = answer.get(i).getObjectiveValues()[1];
        }

        double[][] opt = function.optimal(500);

        double[] xopt = new double[opt.length];
        double[] yopt = new double[opt.length];

        for (int i = 0; i < opt.length; i++) {
            xopt[i] = opt[i][0];
            yopt[i] = opt[i][1];
        }

        System.out.println(Convergence.calculate(new double[][]{x, y}, new double[][]{xopt, yopt}));
        System.out.println(Diversity.calculate(new double[][]{x, y}, new double[][]{xopt, yopt}));

        MatlabChart fig = new MatlabChart(); // figure('Position',[100 100 640 480]);
        fig.plot(x, y, ".r", 2.0f, "Found"); // plot(x,y1,'-r','LineWidth',2);
        fig.plot(xopt, yopt, "-b", 1.0f, "Optimal"); // plot(x,y1,'-r','LineWidth',2);
        fig.RenderPlot();                    // First render plot before modifying
        fig.title(fileName);    // title('Stock 1 vs. Stock 2');
        fig.xlim(xlim[0], xlim[1]);                   // xlim([10 100]);
        fig.ylim(ylim[0], ylim[1]);                  // ylim([200 300]);
        fig.xlabel("Objective 1");                  // xlabel('Days');
        fig.ylabel("Objective 2");                 // ylabel('Price');
        fig.grid("on","on");                 // grid on;
        fig.legend("northeast");             // legend('AAPL','BAC','Location','northeast')
        fig.font("Helvetica",15);            // .. 'FontName','Helvetica','FontSize',15
        fig.saveasSVG("NSHAEA/" + fileName + ".svg",640,640);   // saveas(gcf,'MyPlot','jpeg');



    }



}
