import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer.parseInt(br.readLine());

        Pattern p = Pattern.compile("I(OI)+");
        Matcher m = p.matcher(br.readLine());
        
        long sum = 0;
        while (m.find()) {
            String s = m.group();
            if (s.length() >= (N << 1) + 1) {
                sum += (s.length() >> 1) + 1 - N;
            }
        }
        System.out.println(sum);
    }
}