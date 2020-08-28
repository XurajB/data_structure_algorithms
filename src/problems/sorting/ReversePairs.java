package problems.sorting;

/**
 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
 * You need to return the number of important reverse pairs in the given array.
 */
public class ReversePairs {
    public static void main(String[] args) {
        int[] nums = {1,3,2,3,1};
        System.out.println(reversePairs2(nums));
    }

    ////// homemade BST, worse case: O(N^2) for unbalanced BST
    private static int reversePairs(int[] nums) {
        int ans = 0;
        Node root = null;
        for (int num: nums) {
            // search for all elements no less than twice current element
            // first condition (i < j) is met automatically as we are inserting from i -> j
            ans += search(root, 2L * num + 1);
            root = insert(root, num);
        }

        return ans;
    }

    private static int search(Node root, long val) {
        if (root == null) {
            return 0;
        } else if (val == root.val) {
            return root.count;
        } else if (val < root.val) {
            return root.count + search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }

    private static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node(val);
        } else if (val == root.val) {
            root.count++;
        } else if (val < root.val) {
            root.left = insert(root.left, val);
        } else {
            root.count++;
            root.right = insert(root.right, val);
        }
        return root;
    }

    private static class Node {
        int val;
        int count; // count of all nodes whose values are greater or equal to val
        Node left, right;
        Node (int val) {
            this.val = val;
        }
    }

    ////////////////////////
    /// merge sort O(nlogn)
    // similar to #CountOfSmallerNumberAfterSelf
    // count the array before merge
    ///////////////////////
    private static int reversePairs2(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        if (right <= left) {
            return 0;
        }
        int middle = left + (right - left) / 2;

        int ret = 0;
        ret += mergeSort(nums, left, middle);
        ret += mergeSort(nums, middle + 1, right);

        //count elements
        int count = 0;
        for (int l = left, r = middle + 1; l <= middle; ) {
            if (r > right || (long) nums[l] <= 2 * (long) nums[r]) {
                l++;
                ret += count;
            } else {
                r++;
                count++;
            }
        }

        //merge sort
        int[] temp = new int[right - left + 1];
        for (int l = left, r = middle + 1, k = 0; l <= middle || r <= right; ) {
            if (l <= middle && ((r > right) || nums[l] < nums[r])) {
                temp[k++] = nums[l++];
            } else {
                temp[k++] = nums[r++];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            nums[left + i] = temp[i];
        }

        return ret;
    }
}
