package com.brandongogetap.reactiveviewmodels.home;

import com.brandongogetap.reactiveviewmodels.database.ItemDatabase;
import com.brandongogetap.reactiveviewmodels.database.ListItem;
import com.jakewharton.rxrelay2.PublishRelay;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class HomeViewModelTest {

    private HomeViewModel viewModel;
    private PublishRelay<List<ListItem>> itemRelay;

    @Before public void setUp() {
        ItemDatabase database = mock(ItemDatabase.class);
        itemRelay = PublishRelay.create();
        when(database.items()).thenReturn(itemRelay);
        ListRepository repository = new ListRepository(database);
        viewModel = new HomeViewModel(repository);
    }

    @Test public void listItems() {
        TestObserver<List<ListItem>> testObserver = new TestObserver<>();
        viewModel.listItems().subscribe(testObserver);
        itemRelay.accept(getListWithCount(2));
        itemRelay.accept(getListWithCount(4));
        testObserver.assertValues(getListWithCount(2), getListWithCount(4));
    }

    private List<ListItem> getListWithCount(int count) {
        List<ListItem> items = new ArrayList<>();
        for (int i = 1; i < count; i++) {
            items.add(generateListItem(i));
        }
        return items;
    }

    private ListItem generateListItem(int id) {
        return ListItem.create(id, "", "", id);
    }
}
