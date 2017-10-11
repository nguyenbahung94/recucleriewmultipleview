package com.example.nbhung.demorecyclerviewmultipleviewtypes.model;

/**
 * Created by nbhung on 10/11/2017.
 */

public class item {
    private String text, subText;
    private boolean isExpandable;

    public item(String text, String subText, boolean isExpandable) {
        this.text = text;
        this.subText = subText;
        this.isExpandable = isExpandable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
