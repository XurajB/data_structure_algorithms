package problems.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 */
public class KthSmallestElementInASortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,5,9},
                {10,11,13},
                {12,13,15}
        };
        System.out.println(kthSmallest(matrix, 8));
    }

    // O(nlog(max - min)), min = 0,0..
    // O(1)
    private static int kthSmallest(int[][] matrix, int k) {
        // the idea is to consider this as a 1d matrix - from 0,0 to bottom right. get the arbitrary middle number and see if we have k elements to its left
        // we are not using index to search, we are using values
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1]; // n x n matrix

        while (low <= high) {
            int mid = low + (high - low) / 2; // int mid = lo + ((hi - lo) >> 1);
            int count = countNonBigger(matrix, mid); // find numbers to its left in a sorted array

            if (count < k) {
                // increment values
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    private static int countNonBigger(int[][] matrix, int target) {
        // this is similar to search a 2d matrix where rows are sorted and cols are sorted
        int n = matrix.length;
        // start from bottom left
        int x = n - 1;
        int y = 0;
        int count = 0;

        while (x > 0 && y < n) {
            if (matrix[x][y] > target) {
                x--; // go top
            } else {
                count = count + x + 1; // going from left to right. count how many numbers smaller than this
                y++;
            }
        }
        return count;
    }

    /////////////////////////// Using heap
    // x = min(n, k)
    // O(klogx), O(k)
    private static int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        // we only need to maintain minimum of k or n
        PriorityQueue<Node> pq = new PriorityQueue<>(Math.min(n, k), Comparator.comparingInt(a -> a.val));

        // like in merge sorted lists, insert first element of each row - which is (first col)
        // we need x, y detail about a value so we can go to next
        for (int i = 0; i < Math.min(n, k); i++) {
            pq.offer(new Node(i, 0, matrix[i][0]));
        }

        // we poll element and go from left to right in the matrix;
        while (k-- > 1) {
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;
            if (y < matrix.length - 1) { // n x n matrix
                pq.offer(new Node(x, y + 1, matrix[x][y+1]));
            }
        }

        return pq.poll().val;
    }

    static class Node {
        int x;
        int y;
        int val;
        Node (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}
