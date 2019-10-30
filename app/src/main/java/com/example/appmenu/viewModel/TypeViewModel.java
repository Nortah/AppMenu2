package com.example.appmenu.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmenu.util.OnAsyncEventListener;
import com.example.appmenu.database.entity.TypeEntity;
import com.example.appmenu.database.repository.TypeRepository;

public class TypeViewModel extends AndroidViewModel
{
    private TypeRepository repository;

    private Context applicationContext;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<TypeEntity> observableType;

 public TypeViewModel(@NonNull Application application, final int typeId,
    TypeRepository typeRepository) {
     super(application);
     repository = typeRepository;

     applicationContext = application.getApplicationContext();

     observableType = new MediatorLiveData<>();
// set to null, until we get data from the database.
     observableType.setValue(null);
     LiveData<TypeEntity> type = repository.getType(typeId, applicationContext);

// observe changes of the client entity from the database andforward them
     observableType.addSource( type, observableType::setValue);
 }
    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final int id;

        private final TypeRepository repository;

        public Factory(@NonNull Application application, int typeId) {
            this.application = application;
            this.id = typeId;
            repository = TypeRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TypeViewModel(application, id, repository);
        }
    }
    // Expose the LiveData ClientEntity query so the UI can observe it.
    public LiveData<TypeEntity> getType() {
        return observableType;
    }
    public void createType(TypeEntity type, OnAsyncEventListener
            callback) {
        repository.insert(type, callback, applicationContext);
    }
    public void updateType(TypeEntity type, OnAsyncEventListener
            callback) {
        repository.update(type, callback, applicationContext);
    }
    public void deleteType(TypeEntity type, OnAsyncEventListener
            callback) {
        repository.delete(type, callback, applicationContext);
    }

}
