package problems.dynamic;

/**
 * We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
 * We want to place these books in order onto bookcase shelves that have total width shelf_width.
 * We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width),
 * then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.
 * We repeat this process until there are no more books to place.
 * Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.
 * For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf,
 * the third book on the second shelf, and the fourth and fifth book on the last shelf.
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 */
public class FillingBookcaseShelves {
    public static void main(String[] args) {
        int[][] books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
        System.out.println(minHeightShelves(books, 4));
    }

    // start placing books one by one, always use a new shelve to begin with
    // after we store new height value at this position in the dp, start looking back at previous books and see while the
    // width permits, how many books we can fit on this new level
    // check to see if bringing said books down reduced overall height, if it did update the lowest height at the position
    private static int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n+1]; // dp[i] = min height of self for books books[0]-books[i-1]
        for (int i = 1; i <= n; i++) {
            int height = books[i-1][1];
            int width = books[i-1][0];
            dp[i] = dp[i-1] + height;
            // check if bringing books from top shelves will lower total height
            for (int j = i - 1; j > 0; j--) {
                width += books[j-1][0];
                if (width > shelf_width) {
                    break;
                }
                height = Math.max(height, books[j-1][1]);
                dp[i] = Math.min(dp[i], dp[j-1] + height);
            }
        }
        return dp[n];
    }
}
