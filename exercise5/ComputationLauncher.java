package exercise5;

/**
 * This class launched two heavy computations
 * sequentially first, then in parallel. 
 * Assuming there is more than one processor in 
 * the machine, parallel computations finish
 * earlier.
 * 
 * Refactored for other multi core configurations
 */
public class ComputationLauncher {   
    /**
     * How many numbers to process? If too low, there is no noticeable
     * difference.
     */
     public static final int COUNT = 40000000;
     private static final int NUM_PROCESSORS = Runtime.getRuntime().availableProcessors();

    /*
     * The computations to be performed. Stored as fields so 
     * both methods (sequential and parallel) act on exactly 
     * the same data
     */
    private Computation[] comp = new Computation[NUM_PROCESSORS];
    /**
     * The main method that launches the computations
     *
     * @param args command-line arguments, ignored
     */
    public static void main(String args[]) {
	  ComputationLauncher c = new ComputationLauncher();
	  c.launch();
    }
   
    private double[] createArray(int size) {
	  double[] result = new double[size];
	  for (int i = 0; i < result.length; i++) {
		    result[i] = Math.random();
		}
	  return result;
    }
   
    private void launch() {
	  // Uncomment the following line to know how many processors your machine has
	  // System.out.println("#CPU: " + Runtime.getRuntime().availableProcessors());
	  long start, stop;
	  for (int i = 0; i < NUM_PROCESSORS; i++){
		  comp[i] = new Computation(createArray(COUNT/NUM_PROCESSORS));
	  }
	
	  start = System.currentTimeMillis();
	  sequentialComputations();
	  stop = System.currentTimeMillis();
	  System.out.println("Time without threads: " + (stop - start) + "ms");
	  start = System.currentTimeMillis();
	  parallelComputations();
	  stop = System.currentTimeMillis();
	  System.out.println("Time with threads: " + (stop - start) + "ms");
    }
   
    private void sequentialComputations() {
      for (int i = 0; i < NUM_PROCESSORS; i++){
  		  comp[i].run();
  	  }
      
	  double result = 0.0; 
	  for (int i = 0; i < NUM_PROCESSORS; i++){
		  result += comp[i].getResult();
	  }
	  
	  System.out.println("Result: " + result);
    }
   
    private void parallelComputations() {
      for (int i = 0; i < NUM_PROCESSORS; i++){
    	  Thread t = new Thread(comp[i]);
    	  t.start();
      }
    	
      double result = 0.0; 
	  for (int i = 0; i < NUM_PROCESSORS; i++){
		  result += comp[i].getResult();
	  }

	  System.out.println("Result: " + result);
    }
}