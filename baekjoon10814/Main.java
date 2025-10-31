import java.io.*;
import java.util.*;

class Main {
    static class Profile {
        int id;
        int age;
        String name;
        Profile(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Profile[] arr = new Profile[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            arr[i] = new Profile(i, age, name);
        }
        Arrays.sort(arr, (p1, p2) -> (p1.age == p2.age)? p1.id - p2.id : p1.age - p2.age);
        for (int i = 0; i < N; i++) {
            sb.append(arr[i].age).append(' ').append(arr[i].name).append('\n');
        }
        System.out.print(sb.toString());
    }
}