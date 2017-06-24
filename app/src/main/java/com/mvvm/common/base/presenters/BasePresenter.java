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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mvvm.common.annotation.viewmodelfields.ViewModelCheckBoxField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelHintEditTextField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelImageViewField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelTextField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelTextViewTextColorField;
import com.mvvm.common.annotation.viewmodelfields.ViewModelViewVisibilityField;
import com.mvvm.common.base.creators.SingletonCreator;
import com.mvvm.common.base.models.BaseModel;
import com.mvvm.common.base.scanners.FieldTypeScanner;
import com.mvvm.common.base.viewmodels.BaseViewModel;
import com.mvvm.common.interfaces.ActivityLifeCycle;
import com.mvvm.common.interfaces.BaseView;
import com.mvvm.common.interfaces.FragmentLifeCycle;
import com.mvvm.common.messaging.CustomMessage;
import com.mvvm.common.messaging.InboxHolder;
import com.mvvm.common.messaging.MessagesServer;

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
 * <p>
 * This is the parent class for all presenters
 */

public class BasePresenter<V extends BaseView> implements ActivityLifeCycle, FragmentLifeCycle, InboxHolder
{
    private V baseView;

    private ArrayList<BaseViewModel> allViewModels;
    private ArrayList<Object> allSingletonPerSessionObjects;

    /**
     * init base baseView object
     *
     * @param baseView: this is te base baseView that will be accessed from presenter
     */
    public void initBaseView(@NonNull V baseView) {
        this.baseView = baseView;
        allViewModels = new ArrayList<>();
        allSingletonPerSessionObjects = new ArrayList<>();
    }

    public void addSingletonSessionObject(Object object) {
        allSingletonPerSessionObjects.add(object);
    }


    @Override
    public void onMessageReceived(CustomMessage msg) {

    }

    /**
     * Add new view model to list of View Models holded in presenter
     *
     * @param baseViewModel
     */
    public void addViewModel(BaseViewModel baseViewModel) {
        allViewModels.add(baseViewModel);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        MessagesServer.getInstance().registerInboxHolder(this);
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

        // Destroy all publish subjects of view model
        Observable.fromIterable(allViewModels)
                .subscribe(new Consumer<BaseViewModel>()
                {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull BaseViewModel baseViewModel) throws Exception {
                        baseViewModel.releaseViewModelValues();
                    }
                });

        Observable.fromIterable(allSingletonPerSessionObjects)
                .subscribe(new Consumer<Object>()
                {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {
                        SingletonCreator.getCreator().removeInstance(o.getClass());
                    }
                });

        MessagesServer.getInstance().unRegisterInboxHolder(this);

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
     *
     * @param viewModel :  the created view model object for this presenter
     */
    public void associateViewModelWithViews(final BaseViewModel viewModel) {
        // Loop all fields defined in ViewModel

        // Search for text views
        associateViewModelFieldValuesOfType(viewModel, ViewModelTextField.class);

        // Search for visibility values in ViewModel
        associateViewModelFieldValuesOfType(viewModel, ViewModelViewVisibilityField.class);

        // Search for text view text color values in ViewModel
        associateViewModelFieldValuesOfType(viewModel, ViewModelTextViewTextColorField.class);

        // Search for fields reflecting Hint text in Edit View
        associateViewModelFieldValuesOfType(viewModel, ViewModelHintEditTextField.class);

        // Search for fields reflecting drawable in Image View
        associateViewModelFieldValuesOfType(viewModel, ViewModelImageViewField.class);

        // Search for fields reflecting check box value
        associateViewModelFieldValuesOfType(viewModel, ViewModelCheckBoxField.class);

    }


    private void associateViewModelFieldValuesOfType(final BaseViewModel viewModel, final Class<?> viewModelFieldAnnotation) {
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

                // Create Publish Subject corresponding to this field
                PublishSubject fieldPublishSubject = PublishSubject.create();
                viewModel.addField(viewModelFieldObject, fieldPublishSubject);

                getViewFieldOfResIdAndClass(getViewClass(viewModelFieldAnnotation), fieldResId)
                        .subscribe(setViewValueFromViewModel(fieldPublishSubject, viewModelFieldAnnotation));
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
        else if (viewModelFieldAnnotation.getName().equals(ViewModelTextField.class.getName())) {
            return TextView.class;
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelTextViewTextColorField.class.getName())) {
            return TextView.class;
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelViewVisibilityField.class.getName())) {
            return View.class;
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelImageViewField.class.getName())) {
            return ImageView.class;
        }

        return null;
    }

    /**
     * Set value from ViewModel to corresponding view in View
     *
     * @param viewModelPublishSubject
     * @return
     */
    private Consumer<View> setViewValueFromViewModel(final PublishSubject viewModelPublishSubject,
                                                     final Class<?> viewModelFieldAnnotation) {
        return new Consumer<View>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull View view) throws Exception {
                if (viewModelFieldAnnotation.getName().equals(ViewModelCheckBoxField.class.getName())) {
                    // set check box value
                    ((PublishSubject<Boolean>)viewModelPublishSubject)
                            .subscribe(setCheckBoxValue((CheckBox) view));
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelHintEditTextField.class.getName())) {
                    // set Hint text in edit text
                    ((PublishSubject<String>)viewModelPublishSubject)
                            .subscribe(setEditViewHintText((EditText) view));
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelImageViewField.class.getName())) {
                    // set Image view drawable
                    ((PublishSubject<Integer>) viewModelPublishSubject)
                            .subscribe(setImageViewDrawable((ImageView) view));
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelTextField.class.getName())) {
                    // set text
                    ((PublishSubject<String>)viewModelPublishSubject)
                            .subscribe(setTextViewText((TextView) view));
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelTextViewTextColorField.class.getName())) {
                    //  set text color
                    ((PublishSubject<Integer>) viewModelPublishSubject)
                            .subscribe(setTextViewTextColor((TextView) view));
                }
                else if (viewModelFieldAnnotation.getName().equals(ViewModelViewVisibilityField.class.getName())) {
                    // set view visibility
                    ((PublishSubject<Integer>) viewModelPublishSubject)
                            .subscribe(setViewVisibility(view));
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

    private Consumer<Integer> setViewVisibility(final View view) {
        return new Consumer<Integer>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Integer visibility) throws Exception {

                // set view visibility
                switch (visibility.intValue()) {
                    case View.VISIBLE:
                    case View.GONE:
                    case View.INVISIBLE:
                    {
                        view.setVisibility(visibility.intValue());
                        break;
                    }
                }

            }
        };
    }

    private Consumer<Integer> setTextViewTextColor(final TextView textView) {
        return new Consumer<Integer>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Integer color) throws Exception {

                // set text view text color
                textView.setTextColor(color);
            }
        };
    }

    private Consumer<String> setEditViewHintText(final EditText editText) {
        return new Consumer<String>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull String val) throws Exception {

                // set hint text
                editText.setHint(val);

            }
        };
    }

    private Consumer<Integer> setImageViewDrawable(final ImageView imageView) {
        return new Consumer<Integer>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Integer drawableID) throws Exception {

                // set Image View drawable
                imageView.setImageDrawable(imageView.getContext().getDrawable(drawableID));
            }
        };
    }

    private Consumer<Boolean> setCheckBoxValue(final CheckBox checkBox) {
        return new Consumer<Boolean>()
        {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Boolean val) throws Exception {

                // set check box value
                checkBox.setChecked(val);
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
        else if (viewModelFieldAnnotation.getName().equals(ViewModelTextField.class.getName())) {
            return ((ViewModelTextField) field.getDeclaredAnnotations()[0]).value();
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelTextViewTextColorField.class.getName())) {
            return ((ViewModelTextViewTextColorField) field.getDeclaredAnnotations()[0]).value();
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelViewVisibilityField.class.getName())) {
            return ((ViewModelViewVisibilityField) field.getDeclaredAnnotations()[0]).value();
        }
        else if (viewModelFieldAnnotation.getName().equals(ViewModelImageViewField.class.getName())) {
            return ((ViewModelImageViewField) field.getDeclaredAnnotations()[0]).value();
        }

        return 0;

    }


}
