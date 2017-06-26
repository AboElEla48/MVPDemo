package com.mvvm.common.base.presenters;

import com.mvvm.framework.annotation.viewmodelfields.ViewModelTextField;
import com.mvvm.common.base.views.MainActivityChild;
import com.mvvm.common.base.views.MainActivityPresenterChild;
import com.mvvm.common.base.views.MainActivityViewModelChild;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.lang.reflect.Field;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is a test class ofr base presenter
 */
public class BasePresenterTest
{
    private MainActivityChild mainActivity;
    private MainActivityPresenterChild mainPresenter;

    @Before
    public void init() throws Exception {
        mainActivity = PowerMockito.mock(MainActivityChild.class);
        mainPresenter = new MainActivityPresenterChild();
        mainPresenter.initBaseView(mainActivity, mainPresenter);
        //        mainPresenter = PowerMockito.mock(MainActivityPresenterChild.class);
        //        PowerMockito.when(mainPresenter.getClass().getDeclaredMethod("getViewModelFieldsOfAnnotationType", BaseViewModel.class,
        //                Class.class)).thenCallRealMethod();
    }

    @Test
    public void getViewFieldOfResIdAndClass_returnsView() {
        final MainActivityViewModelChild mainActivityViewModelChild = new MainActivityViewModelChild();
        mainActivityViewModelChild.initView(mainActivity, mainActivityViewModelChild);

        // search for text view fields
        mainActivityViewModelChild.getViewModelFieldsOfAnnotationType(ViewModelTextField.class)
                .subscribe(new Consumer<List<Field>>()
                {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull final List<Field> viewModelFieldObjects) throws Exception {
                        Observable.fromIterable(viewModelFieldObjects)
                                .subscribe(new Consumer<Field>()
                                {
                                    @Override
                                    public void accept(@NonNull Field viewModelFieldObject) throws Exception {
                                        // get annotation of field
                                        final int fieldResId = ((ViewModelTextField) viewModelFieldObject.getDeclaredAnnotations()[0]).value();
                                        viewModelFieldObject.setAccessible(true);

                                        // Create publish subject object to view model field
                                        viewModelFieldObject.set(mainActivityViewModelChild, PublishSubject.create());

                                        Assert.assertTrue(viewModelFieldObject.get(mainActivityViewModelChild) instanceof PublishSubject);
                                        Assert.assertTrue(fieldResId > 0);
                                    }
                                });


                    }
                });

    }

}