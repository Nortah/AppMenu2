package com.example.appmenu.ui;

import androidx.appcompat.app.AppCompatActivity;

public class TypeDetails extends AppCompatActivity
{
    /*
    private static final String TAG = "ClientDetails";

    private static final int CREATE_TYPE = 0;
    private static final int EDIT_TYPE = 1;
    private static final int DELETE_TYPE = 2;

    private Toast statusToast;

    private boolean isEditable;

    private EditText etName;
    private EditText etImage;

    private TypeViewModel viewModel;

    private TypeEntity type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_type_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int typeId = getIntent().getIntExtra("typeId",0);


        TypeViewModel.Factory factory = new TypeViewModel.Factory(getApplication(), typeId);
        viewModel = ViewModelProviders.of(this, factory).get(TypeViewModel.class);
        viewModel.getType().observe(this, accountEntity -> {
            if (accountEntity != null) {
                type = accountEntity;
                updateContent(); // Updates UI if account was recieved
            }
    });
        if (typeId != 0) {
            setTitle(R.string.title_activity_details);
        } else {
            setTitle(R.string.title_activity_create);
            switchEditableMode();
        }
    }

    public void createClient(String name, String image)
    {
        type = new TypeEntity(name, image);
        type.setName(name);
        type.setImage(image);
        viewModel.createType(type, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "createClient: success");
                onBackPressed();
            }
            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "createClient: failure", e);
            }
        });
    }
    */
}
