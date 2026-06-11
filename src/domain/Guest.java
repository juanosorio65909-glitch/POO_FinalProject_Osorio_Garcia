package domain;

import java.io.Serializable;

/**
 * Represents a hotel guest with contact and identification information.
 */
public class Guest implements Serializable {

    private String fullName;
    private String email;
    private long phoneNumber;
    private long documentId;

    /**
     * Creates a guest using the received personal information.
     */
    public Guest(String fullName, String email, long phoneNumber, long documentId) {
        setFullName(fullName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setDocumentId(documentId);
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null || fullName.isBlank() ? "Unknown" : fullName.trim();
    }

    public void setEmail(String email) {
        this.email = email == null || email.isBlank() ? "not-provided" : email.trim();
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber > 0 ? phoneNumber : 0;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId > 0 ? documentId : 0;
    }

    @Override
    public String toString() {
        return "Guest{documentId=" + documentId + ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' + ", phoneNumber=" + phoneNumber + '}';
    }
}
