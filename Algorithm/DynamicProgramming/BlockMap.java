import java.math.BigInteger;

public class BlockMap {
    // Setting enableCacheInRecursion to true could boost the recursive method
    private boolean enableCacheInRecursion = false;

    private BigInteger[][] recursiveResult = null;
    private BigInteger[][] dynamicProgResults = null;

    public BlockMap(int maxRow, int maxColumn, boolean enableCacheInRecursion) {
        // Call the function initDynamicProgResult to pre-calculate the results
        // for dynamic programming method. Also, it will initialize the results
        // for the recursive method
        // maxRow: the max row of the dynamic programing result table
        // maxColumn: the max column of the dynamic programing result table
        initDynamicProgResult(maxRow, maxColumn);

        this.enableCacheInRecursion = enableCacheInRecursion;
        recursiveResult = new BigInteger[maxRow + 1][maxColumn + 1];
    }

    public BigInteger SW_Recursive(int m, int n) {
        // Use the recursive method to compute the result
        // It is simple to understand but not efficient. However, if we add a
        // "cache" for the results, it will be much faster.
        // m: the row number of the target block
        // n: the column number of the target block
        if (0 == m || 0 == n) {
            return BigInteger.valueOf(1);
        }

        if (!enableCacheInRecursion){
            // Method 1: pure recursion
            return SW_Recursive(m - 1, n).add(SW_Recursive(m, n - 1));
        }

        // Method 2: boost the recursion by adding a "cache"
        if (null != recursiveResult[m][n]){
            return recursiveResult[m][n];
        }

        recursiveResult[m][n] = SW_Recursive(m - 1, n).add(SW_Recursive(m, n - 1));
        return recursiveResult[m][n];
    }

    private void initDynamicProgResult(int maxRow, int maxColumn) {
        // Pre-calculate the results for dynamic programming method
        // maxRow: the max row of the dynamic programing result table
        // maxColumn: the max column of the dynamic programing result table
        dynamicProgResults = new BigInteger[maxRow + 1][maxColumn + 1];

        // Set all elements in the first row to 1
        for (int row = 0; row <= maxRow; ++row) {
            dynamicProgResults[row][0] = BigInteger.valueOf(1);
        }

        // Set all elements in the first column to 1
        for (int column = 0; column <= maxColumn; ++column) {
            dynamicProgResults[0][column] = BigInteger.valueOf(1);
        }

        // Use a nested loop to calculate all other elements
        for (int row = 1; row <= maxRow; ++row) {
            for (int column = 1; column <= maxColumn; ++column) {
                dynamicProgResults[row][column] =
                        dynamicProgResults[row - 1][column].
                                add(dynamicProgResults[row][column - 1]);
            }
        }
    }

    public BigInteger SW_DynamicProg(int m, int n) {
        // Use the dynamic programming method to compute the result
        // The result was pre-calculated in the constructor, so
        // here it just return the result
        // m: the row number of the target block
        // n: the column number of the target block
        return dynamicProgResults[m][n];
    }
}
