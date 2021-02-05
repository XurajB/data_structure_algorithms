package problems.math;

/**
 * Given two numbers, hour and minutes. Return the smaller angle (in degrees) formed between the hour and the minute hand.
 */
public class AngleBetweenHandsOfAClock {
    public static void main(String[] args) {
        System.out.println(angleClock(12, 30));
    }

    // O(1)
    private static double angleClock(int hour, int minutes) {
        // angle per minute (360/60)
        double anglePerMinute = 6.0;
        // angle per hour (360/12)
        double anglePerHour = 30.0;

        double minuteAngle = minutes * anglePerMinute;
        // hour angle: hour angle + minute changes
        double hourAngle = (hour % 12 + minutes/60.0) * anglePerHour;

        double diff = Math.abs(hourAngle - minuteAngle);

        return Math.min(diff, 360-diff);
    }
}
