package com.sakariaslilja.services;

import java.util.ArrayList;
import java.util.Comparator;

import com.sakariaslilja.datastructures.ColoredCollector;
import com.sakariaslilja.datastructures.DoubleVector3D;

/**
 * Compares the z-coordinates of a colored collector with entity coordinates used for drawing.
 * <p> Sorted in descending order
 */
public class EntityZComparator implements Comparator<ColoredCollector<ArrayList<DoubleVector3D>>> {

    @Override
    public int compare(ColoredCollector<ArrayList<DoubleVector3D>> c1, ColoredCollector<ArrayList<DoubleVector3D>> c2) {
        double z1 = c1.obj().get(0).getZ();
        double z2 = c2.obj().get(0).getZ();

        if (z1 < z2) { return 1; }
        if (z1 > z2) { return -1; }
        return 0;
    }
    
}
