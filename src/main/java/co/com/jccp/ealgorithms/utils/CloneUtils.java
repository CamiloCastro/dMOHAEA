package co.com.jccp.ealgorithms.utils;

import co.com.jccp.ealgorithms.individual.MOEAIndividual;

/**
 * Created by: Juan Camilo Castro Pinto
 **/
public interface CloneUtils<T> {

    MOEAIndividual<T> clone(MOEAIndividual<T> original);

}
