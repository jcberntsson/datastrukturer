/**
 * Uppgift 1 för laboration 1. Kurs TDA416 - Datastrukturer
 * Grupp: 6
 * Created by: Joakim Berntsson and Tim Kerschbaumer on 2015-01-23
 * Author(s): Joakim Berntsson and Tim Kerschbaumer
 * Purpose: Recursion
 */
public class Uppg1 {

    /**
     * Returns the square root of the provided double. Wrapper function for a private recursive function.
     * @param sqr the number to return the square root of
     * @param eps the accepted error margin
     * @return the square root of sqr
     * @throws IllegalArgumentException if eps is less or equal to zero or sqr is less than 1
     */
    public static double binarySqrt(double sqr, double eps) throws IllegalArgumentException{
        if(sqr >= 1 && eps > 0){
            return help(sqr, eps, 1, (sqr+1)/2);
        }else if(sqr < 1){
            throw new IllegalArgumentException("Square can not be less than 1!");
        }else{
            throw new IllegalArgumentException("Eps must be greater than 0!");
        }
    }

    private static double help(double sqr, double eps, double low, double high){
        // The new possible square root of sqr
        double test = (high + low) / 2;
        // End condition
        if(high - low < eps){
            return test;
        }
        // Test if it's within our allowed interval
        if(test * test > sqr){
            return help(sqr, eps, low, test);
        }else if(test * test < sqr){
            return help(sqr, eps, test, high);
        }else{
            return test;
        }
    }

    private static void testSqr(double sqr, double eps){
        System.out.println("Called binarySqrt with sqr: " + sqr + " and eps: " + eps);
        try{
            double ourRoot = binarySqrt(sqr, eps);
            double realRoot = Math.sqrt(sqr);
            System.out.println("Our root of " + sqr + " is " + ourRoot);
            System.out.println("Real root of " + sqr + " is " + realRoot);
            if(ourRoot < realRoot + eps && ourRoot > realRoot - eps){
                System.out.println("Test success!");
            }else{
                System.out.println("Test failure!");
            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        // Testing for binarySqrt.
        System.out.println("Testing binarySqr\n");
        // Eps for testing purposes
        double eps = Math.pow(10, -6);
        // Test 1
        System.out.println("Test 1");
        testSqr(199238, eps);
        // Test 2
        System.out.println("\nTest 2");
        testSqr(-1, eps);
        // Test 3
        System.out.println("\nTest 3");
        testSqr(40, -1);
        // Test 4
        System.out.println("\nTest 4");
        testSqr(25, eps);
    }
}
