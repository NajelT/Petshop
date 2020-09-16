package com.buseduc.javacourse.petshop;

import java.util.ArrayList;
import java.util.List;

public class ChangesHistory {
    private static ChangesHistory history;


    List<ChangeItem> changeItems = new ArrayList<>();

    private ChangesHistory() {}


    public static ChangesHistory getInstance() {
        if(history == null) {
            history = new ChangesHistory();
        }
        return history;
    }

    public void update(Animal sold, Double currentBalance, boolean isSale) {
        ChangeItem changeItem = isSale? new SaleItem(sold, currentBalance) : new ReceiveItem(sold, currentBalance);
        changeItems.add(changeItem);

    }

    public List<ChangeItem> getChangeItems() {
        return changeItems;
    }
}
