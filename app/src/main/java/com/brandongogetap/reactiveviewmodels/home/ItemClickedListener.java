package com.brandongogetap.reactiveviewmodels.home;

import com.brandongogetap.reactiveviewmodels.database.ListItem;

interface ItemClickedListener {

    void itemClicked(ListItem listItem);

    void itemLongClicked(ListItem listItem);
}
