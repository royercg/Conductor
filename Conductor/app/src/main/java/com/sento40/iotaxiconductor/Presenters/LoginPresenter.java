package com.sento40.iotaxiconductor.Presenters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sento40.iotaxiconductor.Interactors.LoginImpl;
import com.sento40.iotaxiconductor.Interfaces.ErrorListener;
import com.sento40.iotaxiconductor.Interfaces.LoginInterface;
import com.sento40.iotaxiconductor.Models.AccountBean;
import com.sento40.iotaxiconductor.Models.DriverBean;

public class LoginPresenter implements LoginInterface.Presenter, ErrorListener {

    private LoginInterface.View view;
    private LoginInterface.Model model;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        this.model = new LoginImpl(this);
    }

    @Override
    public void onError(String message) {
        if (view != null) {
            view.onError(message);
        }
    }

    @Override
    public void createAccount() {
        if (view != null) {
            view.createAccount();
        }
    }

    @Override
    public void createProfile() {
        if (view != null) {
            view.createProfile();
        }
    }

    @Override
    public void createAccount(FirebaseAuth firebaseAuth, AccountBean accountBean) {
        if (view != null) {
            model.createAccount(firebaseAuth, accountBean, this);
        }
    }

    @Override
    public void createProfile(FirebaseFirestore firebaseFirestore, DriverBean driverBean) {
        if (view != null) {
            model.createProfile(firebaseFirestore, driverBean, this);
        }
    }
}
