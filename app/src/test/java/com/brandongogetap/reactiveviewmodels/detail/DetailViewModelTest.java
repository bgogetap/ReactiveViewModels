package com.brandongogetap.reactiveviewmodels.detail;

import com.brandongogetap.reactiveviewmodels.database.ItemDatabase;
import com.brandongogetap.reactiveviewmodels.database.ListItem;
import com.jakewharton.rxrelay2.PublishRelay;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class DetailViewModelTest {

    private static final ListItem FIRST = ListItem.create(1, "first", "first message", 1);
    private static final ListItem SECOND = ListItem.create(1, "second", "second message", 2);

    private PublishRelay<ListItem> itemRelay;
    private DetailViewModel viewModel;

    @Before public void setUp() {
        ItemDatabase database = mock(ItemDatabase.class);
        itemRelay = PublishRelay.create();
        when(database.itemForId(anyInt())).thenReturn(itemRelay);
        DetailRepository repository = new DetailRepository(database, 1);
        viewModel = new DetailViewModel(repository);
    }

    @Test public void listItem() {
        TestObserver<String> titleTestObserver = new TestObserver<>();
        TestObserver<Integer> idTestObserver = new TestObserver<>();
        TestObserver<String> messageTestObserver = new TestObserver<>();
        TestObserver<Integer> colorTestObserver = new TestObserver<>();
        viewModel.title().subscribe(titleTestObserver);
        viewModel.message().subscribe(messageTestObserver);
        viewModel.id().subscribe(idTestObserver);
        viewModel.color().subscribe(colorTestObserver);

        itemRelay.accept(FIRST);
        itemRelay.accept(SECOND);

        titleTestObserver.assertValues(FIRST.title(), SECOND.title());
        messageTestObserver.assertValues(FIRST.message(), SECOND.message());
        idTestObserver.assertValues(FIRST.id(), SECOND.id());
        colorTestObserver.assertValues(FIRST.color(), SECOND.color());
    }
}
