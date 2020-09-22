package problems.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards)
 * for which the price of the stock was less than or equal to today's price.
 */
public class OnlineStockSpan {
    public static void main(String[] args) {

    }

    List<Integer> prices;
    public OnlineStockSpan() {
        prices = new ArrayList<>();
    }

    // O(N)
    public int next(int price) {
        prices.add(price);
        int count = 1;
        if (price <= prices.get(prices.size() - 1)) {
            int i = prices.size() - 2;
            while (i >= 0 && prices.get(i) <= price) {
                i--;
                count++;
            }
        }
        return count;
    }

    ///////////////////////
    // monotonous stack / monotonous queue
    Stack<int[]> stack = new Stack<>(); // price, count
    public int next2(int price) {
        if (stack.isEmpty()) {
            stack.push(new int[] {price, 1});
            return 1;
        }

        int span = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.peek()[1];
            stack.pop();
        }
        stack.push(new int[] {price, span});
        return span;
    }

}
