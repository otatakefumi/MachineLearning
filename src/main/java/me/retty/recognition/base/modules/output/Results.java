package me.retty.recognition.base.modules.output;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by takefumiota on 2015/09/27.
 */
public class Results<T> {
    private List<IOutput<T>> resultList;

    public Results() {
        this.resultList = new ArrayList<>();
    }

    public void add(IOutput<T> result) {
        this.resultList.add(result);
    }

    public void finalResult() {
        int[] correctCount = {0};
        Map<T, List<IOutput<T>>> resultMap = new HashMap<>();
        Map<T, Integer> correctMap = new HashMap<>();
        this.resultList.forEach(result -> {
            T cls = result.getAnswer();
            if (!resultMap.containsKey(cls)) {
                resultMap.put(cls, new ArrayList<>());
                correctMap.put(cls, 0);
            }
            resultMap.get(cls).add(result);
            if (result.isCorrect()) {
                correctCount[0]++;
                correctMap.put(cls, correctMap.get(cls) + 1);
            }
        });

        System.out.println("[Result]");
        System.out.println("total input num: " + this.resultList.size());
        System.out.println("total correct num: " + correctCount[0]);
        System.out.println("total correct percentage: " + (((double) correctCount[0]) / this.resultList.size() * 100) + "%");
        System.out.println("-----------------");
        resultMap.keySet().forEach(key -> {
            System.out.println("\n");
            System.out.println("input num of " + key + ": " + resultMap.get(key).size());
            System.out.println("correct num of " + key + ": " + correctMap.get(key));
            System.out.println("correct percentage of " + key + ": " + (((double) correctMap.get(key)) / resultMap.get(key).size() * 100) + "%");
        });
        System.out.println("------------------------");
    }
}
