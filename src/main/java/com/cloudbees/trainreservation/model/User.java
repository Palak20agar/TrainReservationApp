package com.cloudbees.trainreservation.model;

public class User {
    private final String firstName;
    private final String lastName;
    private final String emailId;

    public User(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof User)) {
            return false;
        }
        final User otherUser = (User) other;
        return otherUser.getEmailId().equals(this.emailId) && otherUser.getFirstName().equals(this.firstName)
                && otherUser.getLastName().equals(this.lastName);
    }

    @Override
    public int hashCode() {
        int hashCode = emailId.hashCode();
        hashCode = 31 * hashCode + firstName.hashCode();
        hashCode = 31 * hashCode + lastName.hashCode();
        return hashCode;
    }
}
