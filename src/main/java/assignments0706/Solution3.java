package assignments0706;

// Question 3
// create a function
// use one stream api to split list into 2 groups based on age

import java.util.ArrayList;
import java.util.List;

public class Solution3 {
    public static List<List<Employee>> solve3(List<Employee> list) {
        List<List<Employee>> ans = list
                                    .stream()
                                    .collect(()->{
                                        List<List<Employee>> list1 = new ArrayList<>();
                                        list1.add(new ArrayList<>());
                                        list1.add(new ArrayList<>());
                                        return list1;
                                    }, (temp, e) -> {
                                        if (e.getAge() < 40) {
                                            temp.get(0).add(e);
                                        } else {
                                            temp.get(1).add(e);
                                        }
                                    },(l1,l2) -> {});
        return ans;
    }
}
