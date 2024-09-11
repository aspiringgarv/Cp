 static long c(long a1, long d1, long a2, long d2, long x) {
    long c1 = (x >= a1) ? (1 + (x - a1) / d1) : 0;
    long c2 = (x >= a2) ? (1 + (x - a2) / d2) : 0;
    return c1 + c2;
}
public static long f(long a1, long d1, long a2, long d2, long k) {
    long l = Math.min(a1, a2);
    long r = Math.max(a1, a2) + (long) Math.max(d1, d2) * k; 
    while (l < r) {
        long mid = l + (r - l) / 2;
        if (c(a1, d1, a2, d2, mid) < k) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    
    return  l;
}