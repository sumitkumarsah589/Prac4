
package com.demo.sumit.prac4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDemoData {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("user_data")
    @Expose
    private UserData userData;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}
