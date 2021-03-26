package interview;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 数组元素删除 (B)
 * 两个已经排好序的数组A和B(每个数组中的元素不重复)，对A和B中任意一个元素，只保留在其排名靠前的数组中，如果排名相同则保留在数组A中。
 * 考察点：元素遍历过程中删除操作删除元素后会改变在整个数组中元素的排名
 * 对Arrays.asList()生成的ArrayList 会报UnsupportedOperationException
 */
class NumberDelete {
    private static void deleteDuplicateElement(List<Integer> listA, List<Integer> listB) {
        if (CollectionUtils.isEmpty(listA) || CollectionUtils.isEmpty(listB)) {
            return;
        }
        Iterator<Integer> listAIterator = listA.iterator();
        Iterator<Integer> listBIterator = listB.iterator();
        int aIndex = 1, bIndex = 1;
        boolean nextFlagA = true, nextFlagB = true;
        int elementA = 0, elementB = 0;
        while ((!nextFlagA || listAIterator.hasNext()) && (!nextFlagB || listBIterator.hasNext())) {
            if (nextFlagA) {
                elementA = listAIterator.next();
            }
            if (nextFlagB) {
                elementB = listBIterator.next();
            }
            if (elementA == elementB) {
                if (aIndex <= bIndex) {
                    listBIterator.remove();
                } else {
                    listAIterator.remove();
                }
                aIndex++;
                bIndex++;
                nextFlagA = true;
                nextFlagB = true;
            } else {
                if (elementA < elementB) {
                    aIndex++;
                    nextFlagA = true;
                    nextFlagB = false;
                } else {
                    bIndex++;
                    nextFlagA = false;
                    nextFlagB = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> listA = new ArrayList<>(Arrays.asList(1, 3, 4, 9, 12, 45, 55, 57));
        ArrayList<Integer> listB = new ArrayList<>(Arrays.asList(3, 4, 9, 11, 12, 45, 56, 57));
        deleteDuplicateElement(listA, listB);
        System.out.println(listA);
        System.out.println(listB);
    }
}
/*

[1, 4, 12, 45, 55]
[3, 9, 11, 56, 57]

 */