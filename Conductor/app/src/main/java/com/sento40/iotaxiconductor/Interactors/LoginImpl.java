package com.sento40.iotaxiconductor.Interactors;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sento40.iotaxiconductor.Interfaces.ErrorListener;
import com.sento40.iotaxiconductor.Interfaces.LoginInterface;
import com.sento40.iotaxiconductor.Models.AccountBean;
import com.sento40.iotaxiconductor.Models.DriverBean;

import java.util.Objects;

import static com.sento40.core.utils.UtilsConstants._COLLECTIONS_DRIVERS;

public class LoginImpl implements LoginInterface.Model {

    private LoginInterface.Presenter presenter;

    public LoginImpl(LoginInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void createAccount(FirebaseAuth firebaseAuth, AccountBean accountBean, final ErrorListener errorListener) {
        firebaseAuth.createUserWithEmailAndPassword(accountBean.getUser(), accountBean.getPass()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    presenter.createAccount();
                } else {
                    errorListener.onError(Objects.requireNonNull(task.getException()).getMessage());
                }
            }
        });
    }

    @Override
    public void createProfile(FirebaseFirestore firebaseFirestore, DriverBean driverBean, ErrorListener errorListener) {
        firebaseFirestore.collection(_COLLECTIONS_DRIVERS).document(driverBean.getUuid()).set(driverBean);
        presenter.createProfile();
    }
}
