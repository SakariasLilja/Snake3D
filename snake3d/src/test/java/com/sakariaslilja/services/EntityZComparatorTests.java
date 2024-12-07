package com.sakariaslilja.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sakariaslilja.datastructures.ColoredCollector;
import com.sakariaslilja.datastructures.DoubleVector3D;

public class EntityZComparatorTests {

    @Test
    @DisplayName("Entity z-comparator")
    public void verifyComparator() {
        DoubleVector3D pos1 = new DoubleVector3D(0, 0, 1);
        DoubleVector3D pos2 = new DoubleVector3D(0, 0, -1);
        DoubleVector3D pos3 = new DoubleVector3D(0, 0, 0);

        ArrayList<DoubleVector3D> collection1 = new ArrayList<>();
        ArrayList<DoubleVector3D> collection2 = new ArrayList<>();
        ArrayList<DoubleVector3D> collection3 = new ArrayList<>();

        collection1.add(pos1);
        collection2.add(pos2);
        collection3.add(pos3);
        
        ColoredCollector<ArrayList<DoubleVector3D>> cc1 = new ColoredCollector<ArrayList<DoubleVector3D>>(collection1, null);
        ColoredCollector<ArrayList<DoubleVector3D>> cc2 = new ColoredCollector<ArrayList<DoubleVector3D>>(collection2, null);
        ColoredCollector<ArrayList<DoubleVector3D>> cc3 = new ColoredCollector<ArrayList<DoubleVector3D>>(collection3, null);

        ArrayList<ColoredCollector<ArrayList<DoubleVector3D>>> e1 = new ArrayList<>();
        ArrayList<ColoredCollector<ArrayList<DoubleVector3D>>> sorted = new ArrayList<>();

        e1.add(cc1);
        e1.add(cc2);
        e1.add(cc3);

        sorted.add(cc1);
        sorted.add(cc3);
        sorted.add(cc2);

        @SuppressWarnings("unchecked")
        Comparator<ColoredCollector<ArrayList<DoubleVector3D>>> comparator = new EntityZComparator();

        Collections.sort(e1, comparator);

        for (int i = 0; i < 3; i++) {
            assertEquals(sorted.get(i).obj(), e1.get(i).obj(), "The comparator should sort correctly");
        }
    }
    
}
