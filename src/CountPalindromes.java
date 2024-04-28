import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.math.BigInteger;
import parcs.*;

public class CountPalindromes implements AM
{
    private static long startTime = 0;

    public static int countPalindromes(int start, int step, String s)
    {
        int n = s.length();
        int res = 0;
        int l = start;
        while (l<n)
        {
            int j = 0;
            while (l - j >= 0 && l + j < n && s.charAt(l - j) == s.charAt(l + j))
            {
                res += 1;
                j += 1;
            }
            j = 0;
            while (l - j >= 0 && l + j + 1 < n && s.charAt(l - j) == s.charAt(l + j + 1))
            {
                res += 1;
                j += 1;
            }
            l += step;
        }
        return res;
    }


    public static void main(String[] args) throws Exception
    {
        System.err.println("Preparing...");
        if (args.length != 1) {
            System.err.println("Number of workers not specified");
            System.exit(1);
        }
        int numberWorkers = Integer.parseInt(args[0]);
        task curtask = new task();
        curtask.addJarFile("CountPalindromes.jar");
        AMInfo info = new AMInfo(curtask, null);
        System.err.println("Reading input...");
        String S = "";
        try
        {
            Scanner sc = new Scanner(new File(info.curtask.findFile("example_small.txt")));
            S = sc.nextLine();
        }
        catch (IOException e) {e.printStackTrace(); return;}
        int len = S.length();
        System.err.println("Forwarding parts to workers...");
        startTime = System.nanoTime();
        channel[] channels = new channel[numberWorkers];
        for (int i = 0; i < numberWorkers; i++)
        {
            point p = info.createPoint();
            channel c = p.createChannel();
            p.execute("CountPalindromes");
            // c.write(i);
            // c.write(numberWorkers);
            c.write(S);
            channels[i] = c;
        }
        System.err.println("Getting results");
        int[] results = new int[numberWorkers];
        for (int i = 0; i < numberWorkers; i++)
        {
            results[i] = (int) channels[i].readObject();
        }
        System.err.println("Calculation of the result");
        int res = 0;
        for (int i = 0; i < numberWorkers; i++)
        {
            res += results[i];
        }
        long endTime = System.nanoTime();
        System.out.println("Result: " + res);
        long timeElapsed = endTime - startTime;
        double seconds = timeElapsed / 1_000_000_000.0;
        System.err.println("Time passed: " + seconds + " seconds.");
        curtask.end();
    }

    public void run(AMInfo info)
    {
        //int start_pos = (int)info.parent.readObject();
        //int step = (int)info.parent.readObject();
        String s = (String)info.parent.readObject();
        int subresult = countPalindromes(0, 2, s);
        info.parent.write(subresult);
    }
}
