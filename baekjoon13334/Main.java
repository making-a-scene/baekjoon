import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Route> waiting = new PriorityQueue<>((r1, r2) -> r1.end - r2.end);
        int under = Integer.MAX_VALUE;
        int upper = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            if (h < o) {
                under = Math.min(under, h);
                upper = Math.max(upper, o);
            } else {
                under = Math.min(under, o);
                upper = Math.max(upper, h);
            }
            waiting.offer(new Route(h, o));
        }

        int d = Integer.parseInt(br.readLine());
        PriorityQueue<Route> inRange = new PriorityQueue<>((r1, r2) -> r1.start - r2.start);
        while (!waiting.isEmpty()) {
            Route next = waiting.peek();
            if (next.end > under + d) {
                break;
            }
            waiting.poll();
            if (next.start >= under) {
                inRange.offer(next);
            }
        }
        int result = inRange.size();
        for (int i = under + 1; i + d <= upper; i++) {
            while (!inRange.isEmpty() && inRange.peek().start < i) {
                inRange.poll();
            }
            while (!waiting.isEmpty()) {
                Route next = waiting.peek();
                if (next.end > i + d) {
                    break;
                }
                waiting.poll();
                if (next.start >= i) {
                    inRange.offer(next);
                }
            }
            result = Math.max(result, inRange.size());
        }

        System.out.println(result);
    }

    static class Route {
        int start;
        int end;
        Route (int h, int o) {
            if (h < o) {
                this.start = h;
                this.end = o;
            } else {
                this.start = o;
                this.end = h;
            }
        }
    }
}