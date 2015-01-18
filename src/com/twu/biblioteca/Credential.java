package com.twu.biblioteca;

/**
 * Created by mahalaks on 15/01/15.
 */
public class Credential {

    private String userId;
    private String password;

    public Credential(String userId, String password)
    {
        this.userId = userId;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Credential))
            return false;
        if (obj == this)
            return true;
        return  this.userId.equals( ((Credential) obj).userId) &&
                this.password.equals(((Credential) obj).password);
    }

    @Override
    public int hashCode() {
        return (userId + password).hashCode();
    }
}
