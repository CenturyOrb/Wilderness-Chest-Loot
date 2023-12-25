package com.rosed.wildernesschestloot.customitems.tracker;

import com.rosed.wildernesschestloot.util.Tracker;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

public class ExcaliburTracker implements Tracker<LivingEntity, Integer> {

    private final Map<LivingEntity, Integer> excaliburMap;
    private final int defaultValue = 0;

    public ExcaliburTracker() {
        excaliburMap = new HashMap<>();

    }

    @Override
    public void increment(LivingEntity entity) {
        excaliburMap.replace(entity, excaliburMap.get(entity) + 1);
        if (excaliburMap.get(entity) > 4) excaliburMap.replace(entity, defaultValue);
    }

    @Override
    public void decrement(LivingEntity entity) {
        if (excaliburMap.get(entity) > 0)
            excaliburMap.replace(entity, excaliburMap.get(entity) - 1);
    }

    @Override
    public void set(LivingEntity entity, Integer value) {
        excaliburMap.replace(entity, value);
    }

    @Override
    public Integer get(LivingEntity entity) {
        if (!excaliburMap.containsKey(entity))
            return -1;
        return excaliburMap.get(entity);
    }

    @Override
    public void add(LivingEntity entity) {
        excaliburMap.put(entity, defaultValue);
    }

    @Override
    public void add(LivingEntity entity, Integer integer) {
        excaliburMap.put(entity, integer);
    }

    @Override
    public void remove(LivingEntity entity) {
        excaliburMap.remove(entity);
    }
}
