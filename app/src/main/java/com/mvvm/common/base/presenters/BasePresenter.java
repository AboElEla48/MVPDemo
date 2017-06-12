package com.mvvm.common.base.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvvm.common.annotation.ViewModelTextField;
import com.mvvm.common.base.InvalidObject;
import com.mvvm.common.base.scanners.FieldTypeScanner;
import com.mvvm.common.base.viewmodels.BaseViewModel;
import com.mvvm.common.interfaces.ActivityLifeCycle;
import com.mvvm.common.interfaces.BaseView;
import com.mvvm.common.interfaces.FragmentLifeCycle;

import java.lang.reflect.Field;
import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is the parent class for all presenters
 */

public class BasePresenter<V extends BaseView> implements ActivityLifeCycle, FragmentLifeCycle
{
    private V baseView;

    /**
     * init base baseView object
     * @param baseView: this is te base baseView that will be accessed from presenter
     */
    public void initBaseView(@NonNull V baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }


    public V getBaseView() {
        return baseView;
    }

    /**
     * given the view model object, associate all values in ViewModel with views in BaseView
     * @param viewModel :  the created view model object for this presenter
     */
    public void associateViewModelWithViews(BaseViewModel viewModel) {
        // Loop all fields defined in ViewModel

        // search for text view fields
        getViewModelFieldsOfAnnotationType(viewModel, ViewModelTextField.class)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull final Object viewModelFieldObject) throws Exception {
                        // get annotation of field
                        final int fieldResId = ((ViewModelTextField) ((Field) viewModelFieldObject).getDeclaredAnnotations()[0]).value();
                        getViewFieldOfResIdAndClass(TextView.class, fieldResId)
                                .subscribe(new Consumer<View>()
                                {
                                    @Override
                                    public void accept(@io.reactivex.annotations.NonNull View view) throws Exception {
                                        TextView textView = (TextView) view;
                                        textView.setText("String text here");
                                    }
                                });
                    }
                });
    }

    protected Observable getViewModelFieldsOfAnnotationType(BaseViewModel viewModel, Class annotationType) {
        return Observable.just(new FieldTypeScanner().apply(viewModel.getClass().getDeclaredFields(), annotationType))
                .filter(new Predicate<Object>()
                {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull Object o) throws Exception {
                        return !(o instanceof InvalidObject);
                    }
                });
    }

    protected Observable getViewFieldOfResIdAndClass(final Class clz, final int resId) {
        return Observable.fromIterable(Arrays.asList(getBaseView().getClass().getDeclaredFields()))
                .filter(new Predicate<Field>()
                {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull Field field) throws Exception {
                        // search for field with the same resource Id of view model resource Id
                        if (field != null && field.getType().equals(clz)) {
                            field.setAccessible(true);
                        }
                        else {
                            return false;
                        }

                        return ((View) field.get(getBaseView())).getId() == resId;

                    }
                })
                .map(new Function<Field, View>()
                {
                    @Override
                    public View apply(@io.reactivex.annotations.NonNull Field field) throws Exception {
                        return ((View) field.get(getBaseView()));
                    }
                });
    }


}
