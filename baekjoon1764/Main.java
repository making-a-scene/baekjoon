import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        HashSet<String> neverHeard = new HashSet<>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++)
            neverHeard.add(br.readLine());

        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            String tempInput = br.readLine();
            if(neverHeard.contains(tempInput)) {
                result.add(tempInput);
            }
        }
        br.close();
        Collections.sort(result);
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result.size())+"\n");
        for(String s : result) 
        	bw.write(s+"\n");
        bw.flush();
        bw.close();
        
    }
}