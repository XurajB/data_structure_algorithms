package problems.string;

/**
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
 * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
 *
 * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
 * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
 * Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 */
public class ValidIpAddress {
    public static void main(String[] args) {
        System.out.println(validIPAddress("172.16.254.255"));
        System.out.println(validIPAddress("f:f:f:f:f:f:f:f"));
    }

    private static String validIPAddress(String IP) {
        if (IP == null || IP.length() == 0) {
            return "Neither";
        }

        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            return validateIPv4(IP);
        }
        else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return validateIPv6(IP);
        }
        else return "Neither";
    }

    private static String validateIPv4(String IP) {
        // The limit parameter controls the number of times the pattern is applied and therefore affects the length of the resulting array.
        // If the limit n is greater than zero then the pattern will be applied at most n - 1 times, the array's length will be no greater than n,
        // and the array's last entry will contain all input beyond the last matched delimiter.
        // If n is non-positive then the pattern will be applied as many times as possible and the array can have any length.
        // If n is zero then the pattern will be applied as many times as possible, the array can have any length, and trailing empty strings will be discarded.
        String[] nums = IP.split("\\.", -1); // trailing empty strings will be discarded
        for (String x : nums) {
            // Validate integer in range (0, 255):
            // 1. length of chunk is between 1 and 3
            if (x.length() == 0 || x.length() > 3) return "Neither";
            // 2. no extra leading zeros
            if (x.charAt(0) == '0' && x.length() != 1) return "Neither";
            // 3. only digits are allowed
            for (char ch : x.toCharArray()) {
                if (!Character.isDigit(ch)) return "Neither";
            }
            // 4. less than 255
            if (Integer.parseInt(x) > 255) return "Neither";
        }
        return "IPv4";
    }

    private static String validateIPv6(String IP) {
        String[] nums = IP.split(":", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : nums) {
            // Validate hexadecimal in range (0, 2**16):
            // 1. at least one and not more than 4 hexdigits in one chunk
            if (x.length() == 0 || x.length() > 4) return "Neither";
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (Character ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    }

    //////////////
    // simple version
    public String validIPAddress2(String IP) {
        if (IP.isEmpty()) {
            return "Neither";
        }
        int countDot = 0;
        int countColon = 0;
        for (char c: IP.toCharArray()) {
            if (c == '.') {
                countDot++;
            } else if (c == ':') {
                countColon++;
            }
        }
        if (countDot == 3) {
            if (validateIPV4(IP)) {
                return "IPv4";
            }
        } else if (countColon == 7) {
            if (validateIPV6(IP)) {
                return "IPv6";
            }
        }
        return "Neither";
    }

    private boolean validateIPV6(String ip) {
        String[] parts = ip.split(":");
        if (parts.length != 8) {
            return false;
        }
        String hexdigits = "0123456789abcdefABCDEF";
        for (String part: parts) {
            if (part.length() == 0 || part.length() > 4) {
                return false;
            }
            for (char c: part.toCharArray()) {
                if (hexdigits.indexOf(c) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateIPV4(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String part: parts) {
            if (part.length() == 0 || part.length() > 3) {
                return false;
            }
            if (part.length() > 1 && part.charAt(0) == '0') {
                return false;
            }
            for (char c: part.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            if (Integer.parseInt(part) > 255) {
                return false;
            }
        }
        return true;
    }
}
