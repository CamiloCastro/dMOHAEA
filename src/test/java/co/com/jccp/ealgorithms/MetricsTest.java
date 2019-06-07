package co.com.jccp.ealgorithms;

import co.com.jccp.ealgorithms.metrics.Convergence;
import org.junit.Test;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public class MetricsTest {

    @Test
    public void convergenceTest()
    {

        double[][] x = new double[][]{{0,1,2},{0,1,2}};
        double[][] y = new double[][]{{3,2},{1,2}};

        System.out.println(Convergence.calculate(y, x));


    }
}
