package problems.binarysearch;

/**
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 */
public class DivideChocolate {
    public static void main(String[] args) {
        int[] sweetness = {1,2,3,4,5,6,7,8,9};
        System.out.println(maximizeSweetness(sweetness, 5));
    }

    // O(NlogN)
    private static int maximizeSweetness(int[] sweetness, int K) {
        if (sweetness == null || sweetness.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int sweet : sweetness) {
            min = Math.min(min, sweet);
            sum += sweet;
        }
        int left = min;
        int right = sum;
        while(left < right) {
            int targetSweetness = left + (right - left) / 2;
            int chunks = 0;
            int currentSweetness = 0;
            for(int chunk : sweetness) {
                currentSweetness += chunk;
                if (currentSweetness > targetSweetness) {
                    chunks ++;
                    currentSweetness = 0;
                }
            }
            if(chunks > K) {
                left = targetSweetness + 1;
            } else {
                right = targetSweetness;
            }
        }
        return left;
    }
}
