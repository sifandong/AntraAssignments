package assignments0706;

import java.util.List;
import java.util.stream.Collectors;

public class Solution2 {
    // Question 2
    // create a function
    // use stream api, get employees whose age larger than 40
    public static List<Employee> solve2(List<Employee> list) {
        List<Employee> ans = list
                .stream()
                .filter(x -> x.getAge() >= 40)
                .collect(Collectors.toList());
        return ans;
    }
}
