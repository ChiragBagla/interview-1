package com.interview.algorithms.dp;

/**
 * Created by chenting on 2014/6/26.
 *
 * Given a string of digits, find the minimum number of additions required for the string to equal some target number.
 * Each addition is the equivalent of inserting a plus sign somewhere into the string of digits. After all plus signs are inserted,
 * evaluate the sum as usual. For example, consider the string "12" (quotes for clarity). With zero additions, we can achieve the number 12.
 * If we insert one plus sign into the string, we get "1+2", which evaluates to 3. So, in that case, given "12", a minimum of 1 addition is required to
 * get the number 3. As another example, consider "303" and a target sum of 6. The best strategy is not "3+0+3", but "3+03".
 * You can do this because leading zeros do not change the result.
 * Write a class QuickSums that contains the method minSums, which takes a String numbers and an int sum. The method should calculate and return the
 * minimum number of additions required to create an expression from numbers that evaluates to sum. If this is impossible, return -1.
 */
public class C12_14_QuickSums {
    public boolean useDP = false;

    public C12_14_QuickSums(boolean useDP) {
        this.useDP = useDP;
    }

    public int minSums(String numbers, int sum){
        int[] intNumbers = new int[numbers.length()];
        for(int i = 0; i < numbers.length(); i++ ){
            intNumbers[i] = numbers.charAt(i) - '0';
        }
        if(useDP) return minSumsByDP(intNumbers, sum);
        else return minSumsByBF(intNumbers, sum);
    }

    /**
     * use Binary number increase to simulate the Brute Force search process.
     * @param numbers
     * @param sum
     * @return
     */
    private int minSumsByBF(int[] numbers, int sum){
        int min = Integer.MAX_VALUE;
        int N = numbers.length - 1;

        int[] additions = new int[N];
        for(int i = 0; i < N; i++) additions[i] = 0;

        while(additions[N-1] != 2){
            additions[0]++;
            for(int i = 0; i < N - 1; i++){
                if(additions[i] == 2){
                    additions[i] = 0;
                    additions[i+1]++;
                }
            }

            long realSum = 0;
            long current = numbers[0];
            int count = 0;
            for(int i = 1; i <= N; i++){
                if(additions[i-1] == 0)  current = current * 10 + numbers[i];
                else {
                    realSum += current;
                    current = numbers[i];
                    count++;
                }
            }
            realSum += current;
            if(realSum == sum && count < min){
                min = count;
            }
        }
        return min < Integer.MAX_VALUE? min : -1;
    }

    private int minSumsByDP(int[] numbers, int sum){
        return -1;
    }
}
