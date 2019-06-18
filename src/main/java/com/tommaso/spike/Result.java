package com.tommaso.spike;

import java.util.ArrayList;
import java.util.List;

public class Result {
    List<Integer> okList = new ArrayList<>();
    List<Integer> KoList = new ArrayList<>();

    public void addOk(Integer integer) {
        okList.add(integer);
    }

    public void addKo(Integer integer) {
        KoList.add(integer);
    }

    public List<Integer> getKoList() {
        return KoList;
    }

    public List<Integer> getOkList() {
        return okList;
    }
}
