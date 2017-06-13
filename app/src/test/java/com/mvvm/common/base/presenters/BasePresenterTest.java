package com.mvvm.common.base.presenters;

import com.mvvm.common.annotation.ViewModelTextField;
import com.mvvm.common.base.views.MainActivityChild;
import com.mvvm.common.base.views.MainActivityPresenterChild;
import com.mvvm.common.base.views.MainActivityViewModelChild;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.lang.reflect.Field;

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
        mainPresenter.initBaseView(mainActivity);
//        mainPresenter = PowerMockito.mock(MainActivityPresenterChild.class);
//        PowerMockito.when(mainPresenter.getClass().getDeclaredMethod("getViewModelFieldsOfAnnotationType", BaseViewModel.class,
//                Class.class)).thenCallRealMethod();
    }

        @Test
        public void getViewFieldOfResIdAndClass_returnsView() {
            final MainActivityViewModelChild mainActivityViewModelChild = new MainActivityViewModelChild();
            mainActivityViewModelChild.initView(mainActivity);

            // search for text view fields
            mainPresenter.getViewModelFieldsOfAnnotationType(mainActivityViewModelChild, ViewModelTextField.class)
                    .subscribe(new Consumer<Object>()
                    {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull final Object viewModelFieldObject) throws Exception {
                            // get annotation of field
                            final int fieldResId = ((ViewModelTextField) ((Field) viewModelFieldObject).getDeclaredAnnotations()[0]).value();
                            ((Field) viewModelFieldObject).setAccessible(true);

                            // Create publish subject object to view model field
                            ((Field) viewModelFieldObject).set(mainActivityViewModelChild, PublishSubject.create());

                            Assert.assertTrue(((Field) viewModelFieldObject).get(mainActivityViewModelChild) instanceof PublishSubject);
                            Assert.assertTrue(fieldResId > 0);

                        }
                    });

        }

}