package assignments0706;


// Question 4
// create a class + function
// input 3 list of Integer, use multi-threading to sum all values from 3 list and return int result
// use CompletableFuture

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Solution4 {
    public static int sum(List<List<Integer>> list) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<CompletableFuture<Integer>> futureList = list
                .stream()
                .map(nums -> CompletableFuture.supplyAsync(()->cal(nums),executor))
                .collect(Collectors.toList());
        CompletableFuture cf = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        CompletableFuture<List<Integer>> res = cf.thenApply(v -> futureList.stream().map(f -> f.join()).collect(Collectors.toList()));
        return cal(res.join());
    }

    public static int cal(List<Integer> list) {
        return list.stream().mapToInt(i -> i.intValue()).sum();


    }

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(new Integer[]{1,1});
        List<Integer> l2 = Arrays.asList(new Integer[]{2,2});
        List<Integer> l3 = Arrays.asList(new Integer[]{3,3});
        List<List<Integer>> temp = new ArrayList<>();
        temp.add(l1);
        temp.add(l2);
        temp.add(l3);
        System.out.println(sum(temp));
    }
}
