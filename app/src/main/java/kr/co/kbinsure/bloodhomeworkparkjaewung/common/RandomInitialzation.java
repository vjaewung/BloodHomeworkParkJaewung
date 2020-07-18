package kr.co.kbinsure.bloodhomeworkparkjaewung.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import kr.co.kbinsure.bloodhomeworkparkjaewung.R;

public class RandomInitialzation {

    private static Random random = new Random(System.currentTimeMillis());
    private static HashMap<Integer, String> girlGenerationMaps;

    static {
        girlGenerationMaps = new HashMap<>();

        girlGenerationMaps.put(R.drawable.girls_generation_all, "소녀시대");
        girlGenerationMaps.put( R.drawable.girls_generation_hyoyeon, "효연");
        girlGenerationMaps.put( R.drawable.girls_generation_seohyun, "서현");
        girlGenerationMaps.put(R.drawable.girls_generation_sunny, "써니");
        girlGenerationMaps.put(R.drawable.girls_generation_suyoung, "수영");
        girlGenerationMaps.put( R.drawable.girls_generation_taeyeon, "태연");
        girlGenerationMaps.put( R.drawable.girls_generation_tifany, "티파니");
        girlGenerationMaps.put(R.drawable.girls_generation_yuri, "유리");
        girlGenerationMaps.put(R.drawable.girls_generation_jesica, "제시카");
        girlGenerationMaps.put(R.drawable.girls_generation_yuna, "윤아");
    }

    public static ArrayList<Integer> shuffleGirlsGeneration() {
        ArrayList<Integer> keys = new ArrayList<>(girlGenerationMaps.keySet());
        Collections.shuffle(keys, random);

        return keys;
    }

    public static String  getGirlGenerationName(Integer key) {

        return girlGenerationMaps.get(key);
    }

}
