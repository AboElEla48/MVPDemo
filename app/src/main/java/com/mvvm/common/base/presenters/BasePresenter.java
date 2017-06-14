package com.mvvm.common.base.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mvvm.common.annotation.viewmodelfields.ViewModelCheckBoxField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelHintEditTextField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelRadioButtonField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelTextField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelTextViewTextColorField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelViewVisibilityField;
import com.mvvm.common.base.scanners.FieldTypeScanner;
import com.mvvm.common.base.viewmodels.BaseViewModel;
import com.mvvm.common.interfaces.ActivityLifeCycle;
import com.mvvm.common.interfaces.BaseView;
import com.mvvm.common.interfaces.FragmentLifeCycle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by AboelelaA on 6/6/2017.
 *
 * This is the parent class for all presenters
 */

public class BasePresenter<V extends BaseView> implements ActivityLifeCycle, FragmentLifeCycle
{
    private V baseView;

    private ArrayList<PublishSubject<Object>> allViewModelsFields;

    /**
     * init base baseView object
     * @param baseView: this is te base baseView that will be accessed from presenter
     */
    public void initBaseView(@NonNull V baseView) {
        this.baseView = baseView;
        allViewModelsFields = new ArrayList<>();
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

        // Destroy all publish subjects
        Observable.fromIterable(allViewModelsFields)
                .subscribe(new Consumer<PublishSubject<Object>>()
                {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull PublishSubject<Object> publishSubject) throws Exception {
                        publishSubject.onComplete();
                    }
                });
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
    public void associateViewModelWithViews(final BaseViewModel viewModel) {
        // Loop all fields defined in ViewModel

        // Search for text views
        associateViewModelTextViewsValues(viewModel, ViewModelTextField.class);

    }


    private void associateViewModelTextViewsValues(final BaseViewModel viewModel, final Class<?> viewModelFieldAnnotation) {
        // search for text view fields
        getViewModelFieldsOfAnnotationType(viewModel, viewModelFieldAnnotation)
                .subscribe(collectViewModelFields(viewModel, viewModelFieldAnnotation));
    }


    private Consumer<List<Field>> collectViewModelFields(final BaseViewModel viewModel, final Class<?> viewModelFieldAnnotation) {
        return new Consumer<List<Field>>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull final List<Field> viewModelFieldObjects) throws Exception {
                Observable.fromIterable(viewModelFieldObjects)
                        .subscribe(createFieldPublishSubjects(viewModel, viewModelFieldAnnotation));
            }
        };
    }

    private Consumer<Field> createFieldPublishSubjects(final BaseViewModel viewModel, final Class<?> viewModelFieldAnnotation) {
        return new Consumer<Field>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull final Field viewModelFieldObject) throws Exception {
                // get annotation of field
                final int fieldResId = getViewModelResIdOfAnnotationField(viewModelFieldObject, viewModelFieldAnnotation);
                viewModelFieldObject.setAccessible(true);

                // Create publish subject object to view model field
                viewModelFieldObject.set(viewModel, PublishSubject.create());
                allViewModelsFields.add((PublishSubject<Object>) viewModelFieldObject.get(viewModel));

                getViewFieldOfResIdAndClass(getViewClass(viewModelFieldAnnotation), fieldResId)
                        .subscribe(setViewValueFromViewModel(viewModel, viewModelFieldObject, viewModelFieldAnnotation));
            }
        };
    }

    private Class getViewClass(Class<?> viewModelFieldAnnotation) {
        if (viewModelFieldAnnotation.getName().equals(ViewModelCheckBoxField.class.getName())) {
            return CheckBox.class;
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelHintEditTextField.class.getName())) {
            return EditText.class;
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelRadioButtonField.class.getName())) {
            return RadioButton.class;
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelTextField.class.getName())) {
            return TextView.class;
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelTextViewTextColorField.class.getName())) {
            return View.class;
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelViewVisibilityField.class.getName())) {
            return View.class;
        }

        return null;
    }

    /**
     * Set value from ViewModel to corresponding view in View
     * @param viewModel
     * @param viewModelFieldObject
     * @return
     */
    private Consumer<View> setViewValueFromViewModel(final BaseViewModel viewModel, final Field viewModelFieldObject, final Class<?> viewModelFieldAnnotation) {
        return new Consumer<View>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull View view) throws Exception {
                if (viewModelFieldAnnotation.getName().equals(ViewModelCheckBoxField.class.getName())) {
                    // TODO: set check box value
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelHintEditTextField.class.getName())) {
                    // TODO: set Hint text in edit text
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelRadioButtonField.class.getName())) {
                    // TODO: set Radio Button
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelTextField.class.getName())) {
                    // set text
                    ((PublishSubject<String>)  viewModelFieldObject.get(viewModel))
                            .subscribe(setTextViewText((TextView) view));
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelTextViewTextColorField.class.getName())) {
                    // TODO: set text color
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelViewVisibilityField.class.getName())) {
                    // TODO: set view visibility
                }


            }
        };
    }

    private Consumer<String> setTextViewText(final TextView textView) {
        return new Consumer<String>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {

                // set text to text view
                textView.setText(s);
            }
        };
    }


    protected Observable<List<Field>> getViewModelFieldsOfAnnotationType(BaseViewModel viewModel, Class annotationType) {
        return Observable.just(new FieldTypeScanner().apply(viewModel.getClass().getDeclaredFields(), annotationType))
                .filter(new Predicate<List<Field>>()
                {
                    @Override
                    public boolean test(@io.reactivex.annotations.NonNull List<Field> fields) throws Exception {
                        return fields.size() > 0;
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

    private int getViewModelResIdOfAnnotationField(Field field, Class<?> viewModelFieldAnnotation) {
        if (viewModelFieldAnnotation.getName().equals(ViewModelCheckBoxField.class.getName())) {
            return ((ViewModelCheckBoxField) field.getDeclaredAnnotations()[0]).value();
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelHintEditTextField.class.getName())) {
            return ((ViewModelHintEditTextField) field.getDeclaredAnnotations()[0]).value();
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelRadioButtonField.class.getName())) {
            return ((ViewModelRadioButtonField) field.getDeclaredAnnotations()[0]).value();
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelTextField.class.getName())) {
            return ((ViewModelTextField) field.getDeclaredAnnotations()[0]).value();
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelTextViewTextColorField.class.getName())) {
            return ((ViewModelTextViewTextColorField) field.getDeclaredAnnotations()[0]).value();
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelViewVisibilityField.class.getName())) {
            return ((ViewModelViewVisibilityField) field.getDeclaredAnnotations()[0]).value();
        }

        return 0;

    }


}
