public static class GcdSegmentTree {
    private long[] arr;
    private long[] gcdTree;
    private int n;

    public GcdSegmentTree(long[] input) {
        n = input.length;
        arr = input;
        gcdTree = new long[2 * n];
        buildTree();
    }

    private void buildTree() {
        // Initialize leaves
        for (int i = 0; i < n; i++) {
            gcdTree[n + i] = arr[i];
        }

        // Build the segment tree for GCD
        for (int i = n - 1; i > 0; i--) {
            gcdTree[i] = gcd(gcdTree[2 * i], gcdTree[2 * i + 1]);
        }
    }

    // Query the range [left, right) for GCD
    public long rangeGcd(int left, int right) {
        long gcdVal = 0;

        // Adjust indices for the tree representation
        for (left += n, right += n; left < right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1) {
                gcdVal = gcd(gcdVal, gcdTree[left]);
                left++;
            }
            if ((right & 1) == 1) {
                right--;
                gcdVal = gcd(gcdVal, gcdTree[right]);
            }
        }
        return gcdVal;
    }

    // Update the value at index i to value
    public void update(int index, long value) {
        index += n;
        gcdTree[index] = value;

        // Update the segment tree
        for (index >>= 1; index > 0; index >>= 1) {
            gcdTree[index] = gcd(gcdTree[2 * index], gcdTree[2 * index + 1]);
        }
    }

    // Helper function to calculate GCD of two numbers
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}