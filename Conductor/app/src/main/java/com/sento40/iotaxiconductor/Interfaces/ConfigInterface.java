package com.sento40.iotaxiconductor.Interfaces;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public interface ConfigInterface {

    interface View extends ModelBase {
        void getTokenFcmResult(String token);
        void updateTokenFcmResult();
    }

    interface Presenter {
        void getTokenFcmResult(String token);
        void updateTokenFcmResult();

        void getTokenFcm();
        void updateTokenFcmResult(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth, String token);
    }

    interface Model {
        void getTokenFcm(ErrorListener errorListener);
        void updateTokenFcm(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth, String token, ErrorListener errorListener);
    }
}
