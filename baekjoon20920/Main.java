import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (input.length() < M) {
                continue;
            }
            wordFrequency.put(input, wordFrequency.getOrDefault(input, 0) + 1);
        }
        PriorityQueue<Voca> pq = new PriorityQueue<>(
            (o1, o2) -> (o1.frequency == o2.frequency)? 
            (o1.word.length() == o2.word.length())? 
            o1.word.compareTo(o2.word) : o2.word.length() - o1.word.length() : o2.frequency - o1.frequency);
        for (String word : wordFrequency.keySet()) {
            pq.offer(new Voca(word, wordFrequency.get(word)));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().word).append('\n');
        }
        System.out.print(sb.toString());
    }

    static class Voca {
        String word;
        int frequency;
        Voca (String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }
}
