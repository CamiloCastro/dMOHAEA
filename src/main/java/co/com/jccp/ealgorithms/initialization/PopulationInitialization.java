package co.com.jccp.ealgorithms.initialization;

import co.com.jccp.ealgorithms.individual.MOEAIndividual;

import java.util.List;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public abstract class PopulationInitialization<T> {



    public abstract List<MOEAIndividual<T>> init(int popSize, int dimensions);


}
