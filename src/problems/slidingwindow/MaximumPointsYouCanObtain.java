package problems.slidingwindow;

public class MaximumPointsYouCanObtain {
    public static void main(String[] args) {
        int[] cardPoints = {1,2,3,4,5,6,1};
        System.out.println(maxScore(cardPoints, 3));
    }

    // to get max out of the k points, we want to remove len - k points from array
    // which means - if we have a sliding window of len-k size, we want to remove this window
    // somewhere along the path to make sure we have k points remaining and their sum is max
    private static int maxScore(int[] cardPoints, int k) {
        if (cardPoints == null || cardPoints.length == 0) {
            return 0;
        }

        int totalSum = 0;
        for (int c: cardPoints) {
            totalSum += c;
        }

        int windowSum = 0;
        int max = 0;
        int windowSize = cardPoints.length - k;

        for (int i = 0; i < cardPoints.length; i++) {
            if (i < windowSize) {
                windowSum += cardPoints[i];
            } else {
                // update the max
                max = Math.max(max, totalSum - windowSum);
                // slide the window, subtract last element and add next element
                windowSum = windowSum - cardPoints[i - windowSize] + cardPoints[i];
            }
        }

        // for remaining element
        max = Math.max(max, totalSum - windowSum);
        return max;
    }

    ///
    /// now our travelling window is of size k
    // we create window upto k from left, and move window to left (remove from left and add from right)
    public int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length;

        int leftSum = 0;
        int rightSum = 0;

        for (int i = 0; i < k; i++) {
            leftSum += cardPoints[i];
        }

        int max = leftSum;

        for (int i = 0; i < k; i++) {
            leftSum -= cardPoints[k-1-i];
            rightSum += cardPoints[n-1-i];
            max = Math.max(max, leftSum + rightSum);
        }

        return max;
    }
}
