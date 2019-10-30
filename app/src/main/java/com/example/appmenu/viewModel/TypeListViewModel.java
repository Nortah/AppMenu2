package com.example.appmenu.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmenu.database.BaseApp;
import com.example.appmenu.util.OnAsyncEventListener;
import com.example.appmenu.database.entity.TypeEntity;
import com.example.appmenu.database.repository.TypeRepository;

import java.util.List;

public class TypeListViewModel extends AndroidViewModel {

    private Application application;

    private TypeRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<TypeEntity>> observableType;


    public TypeListViewModel(@NonNull Application application,
                                TypeRepository typeRepository)
    {
        super(application);

        this.application = application;

        repository = typeRepository;

        observableType = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableType.setValue(null);


        LiveData<List<TypeEntity>> types = repository.getAllTypes(application);

        // observe the changes of the entities from the database and forward them
        observableType.addSource(types, observableType::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;


        private final TypeRepository typeRepository;

        public Factory(@NonNull Application application) {
            this.application = application;
            typeRepository = ((BaseApp)application).getTypeRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TypeListViewModel(application, typeRepository);
        }
    }

    /**
     * Expose the LiveData ClientAccounts query so the UI can observe it.
     */
    public LiveData<List<TypeEntity>> getTypes() {
        return observableType;
    }

    public void deleteAccount(TypeEntity type, OnAsyncEventListener callback) {
        repository.delete(type, callback, application);
    }

}
