package com.mvvm.common.base.presenters;

import android.widget.TextView;

import com.mvvm.R;
import com.mvvm.common.annotation.ViewModelTextField;
import com.mvvm.common.base.samples.SampleBasePresenter;
import com.mvvm.common.base.samples.SampleBaseView;
import com.mvvm.common.base.views.SampleLifeCycleDelegateChild;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by AboelelaA on 6/7/2017.
 * This is a test class ofr base presenter
 */
public class BasePresenterTest
{
    private SampleBaseView sampleBaseView;
    private SampleBasePresenter sampleBasePresenter;

    @Before
    public void init() throws Exception {

        sampleBaseView = new SampleBaseView();
//        sampleBaseView.onCreate(null);
//        sampleBaseView.tempInit();
        final SampleLifeCycleDelegateChild lifeCycleDelegate = new SampleLifeCycleDelegateChild(sampleBaseView);
        lifeCycleDelegate.onCreate(null);

        sampleBasePresenter = sampleBaseView.getPresenterObject();

//        sampleBasePresenter.initBaseView(sampleBaseView);
        //                sampleBasePresenter.onCreate(null);
        //                sampleBasePresenter.onStart();
        //        sampleBasePresenter.onResume();
    }

    @Test
    public void initBaseView_NoNull() {
        Assert.assertTrue(sampleBasePresenter.getBaseView() != null);
        Assert.assertTrue(sampleBasePresenter.getBaseView() instanceof SampleBaseView);
    }


    @Test
    public void getViewModelFieldsOfAnnotationType_ReturnsTextView() {
        sampleBasePresenter.getViewModelFieldsOfAnnotationType(sampleBasePresenter.getSampleViewModel1(), ViewModelTextField.class)
                .subscribe(new Consumer()
                {
                    @Override
                    public void accept(@NonNull Object viewModelFieldObject) throws Exception {
                        final int fieldResId = ((ViewModelTextField) ((Field) viewModelFieldObject).getDeclaredAnnotations()[0]).value();
                        Assert.assertTrue(fieldResId == R.id.main_activity_title_text_view);
                    }
                });
    }

    @Test
    public void getViewFieldOfResId_ReturnsSameTextView() {
        sampleBasePresenter.getViewFieldOfResIdAndClass(TextView.class, R.id.main_activity_title_text_view)
                .subscribe(new Consumer<Field>()
                {
                    @Override
                    public void accept(@NonNull Field field) throws Exception {
                        field.setAccessible(true);
                        TextView textView = (TextView) field.get(sampleBasePresenter);
                        Assert.assertTrue(textView != null);
                    }
                });
    }


}