package solutions._56;

import java.util.ArrayList;
import java.util.List;

/**
 * 56. Merge Intervals
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<>();
        if (intervals.size() == 0) {
            return list;
        }
        intervals.sort((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            } else {
                return o1.start - o2.start;
            }
        });

        Interval cur = intervals.get(0);
        int i = 1;
        while (i < intervals.size()) {
            Interval p = intervals.get(i);
            if (cur.end < p.start) {
                list.add(cur);
                cur = p;
            } else if (cur.start <= p.start && cur.end <= p.end) {
                cur.end = p.end;
            }
            i++;
        }
        list.add(cur);
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Interval> intervals = new ArrayList<>();
        Interval interval1 = new Interval(1, 4);
        Interval interval2 = new Interval(2, 3);

        intervals.add(interval1);
        intervals.add(interval2);

        List<Interval> merge = solution.merge(intervals);
        System.out.println(merge);
    }
}