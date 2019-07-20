package com.sento40.iotaxiconductor.Interactors;

import static com.sento40.core.utils.UtilsConstants._COLLECTIONS_DRIVERS;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.sento40.iotaxiconductor.Interfaces.ConfigInterface;
import com.sento40.iotaxiconductor.Interfaces.ErrorListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConfigImpl implements ConfigInterface.Model {

    private ConfigInterface.Presenter presenter;

    public ConfigImpl(ConfigInterface.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getTokenFcm(final ErrorListener errorListener) {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    errorListener.onError(Objects.requireNonNull(task.getException()).getMessage());
                } else {
                    presenter.getTokenFcmResult(Objects.requireNonNull(task.getResult()).getToken());
                }
            }
        });
    }

    @Override
    public void updateTokenFcm(FirebaseFirestore firebaseFirestore, FirebaseAuth firebaseAuth, String token, ErrorListener errorListener) {

        Map<String, Object> data = new HashMap<>();
        data.put("fcmKey", token);

        firebaseFirestore.collection(_COLLECTIONS_DRIVERS).document(Objects.requireNonNull(firebaseAuth.getUid())).set(data, SetOptions.merge());
        presenter.updateTokenFcmResult();
    }


}
