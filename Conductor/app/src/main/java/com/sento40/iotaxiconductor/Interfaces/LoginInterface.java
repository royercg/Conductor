package com.sento40.iotaxiconductor.Interfaces;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sento40.iotaxiconductor.Models.AccountBean;
import com.sento40.iotaxiconductor.Models.DriverBean;

public interface LoginInterface {

    interface View extends ModelBase {
        void createAccount();
        void createProfile();
    }

    interface Presenter {

        void createAccount();
        void createProfile();

        void createAccount(FirebaseAuth firebaseAuth, AccountBean accountBean);
        void createProfile(FirebaseFirestore firebaseFirestore, DriverBean driverBean);
    }

    interface Model {
        void createAccount(FirebaseAuth firebaseAuth, AccountBean accountBean, ErrorListener errorListener);
        void createProfile(FirebaseFirestore firebaseFirestore, DriverBean driverBean, ErrorListener errorListener);
    }

}
