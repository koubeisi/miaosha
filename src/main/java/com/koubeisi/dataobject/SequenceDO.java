package com.koubeisi.dataobject;

public class SequenceDO {
    private String name;

    private Integer currentVal;

    private Integer step;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(Integer currentVal) {
        this.currentVal = currentVal;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}