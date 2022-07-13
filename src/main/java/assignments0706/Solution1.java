package assignments0706;

// Question 1
// write a function which takes list of employees as input
// remove duplicate

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution1 {
    public static List<Employee> solve1(List<Employee> list) {
        Set<Employee> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }
}
