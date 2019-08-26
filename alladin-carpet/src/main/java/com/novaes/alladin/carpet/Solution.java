/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novaes.alladin.carpet;

/**
 *
 * @author andre
 */
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'optimalPoint' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY magic
     *  2. INTEGER_ARRAY dist
     */
    public static int optimalPoint(List<Integer> magic, List<Integer> dist) {
        int result = -1;
        int size = magic.size();
        for (int i = 0; i < size; i++) {
            int r = magic.get(0);
            for (int j = 1; j <= magic.size(); j++) {
                if (r < dist.get(j - 1)) {
                    r = -1;
                    break;
                }
                if (j < magic.size()) {
                    r = walk(r, dist.get(j - 1), magic.get(j));
                } else {
                    r = walk(r, dist.get(j - 1), 0);
                }
            }
            if (r > 0) {
                result = i;
                break;
            }
            shift(magic, dist);
        }
        return result;
    }

    public static int walk(int position, int distance, int magic) {
        return position - distance + magic;
    }

    public static void shift(List<Integer> magic, List<Integer> dist) {
        Integer magicFirst = magic.get(0);
        magic.remove(0);
        magic.add(magicFirst);

        Integer distFirst = dist.get(0);
        dist.remove(0);
        dist.add(distFirst);

    }

}

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\andre\\Desktop\\teste.txt"));

        int magicCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> magic = IntStream.range(0, magicCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int distCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> dist = IntStream.range(0, distCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.optimalPoint(magic, dist);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
