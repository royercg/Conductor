package com.sento40.iotaxiconductor.Presenters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sento40.iotaxiconductor.Interactors.ConfigImpl;
import com.sento40.iotaxiconductor.Interfaces.ConfigInterface;
import com.sento40.iotaxiconductor.Interfaces.ErrorListener;

public class ConfigPresenter implements ConfigInterface.Presenter, ErrorListener {

    private ConfigInterface.View view;
    private ConfigInterface.Model model;

    public ConfigPresenter(ConfigInterface.View view) {
        this.view = view;
        this.model = new ConfigImpl(this);
    }

    @Override
    public void getTokenFcmResult(String token) {
        if (view != null) {
            view.getTokenFcmResult(token);
        }
    }

    @Override
    public void updateTokenFcmResult() {
        if (view != null) {
            view.updateTokenFcmResult();
        }
    }

    @Override
    public void getTokenFcm() {
        if (view != null) {
            model.getTokenFcm(this);
        }
    }

    @Override
    public void updateTokenFcmResult(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth, String token) {
        if (view != null) {
            model.updateTokenFcm(firebaseFirestore, firebaseAuth, token, this);
        }
    }

    @Override
    public void onError(String message) {
        if (view != null) {
            view.onError(message);
        }
    }
}
