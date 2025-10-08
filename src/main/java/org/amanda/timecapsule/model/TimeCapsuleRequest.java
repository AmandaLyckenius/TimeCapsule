package org.amanda.timecapsule.model;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class TimeCapsuleRequest {

    @NotBlank(message= "Email is required")
    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Message is required")
    @Size(max = 5000, message = "Message may be at most 5000 characters")
    private String message;
    @NotNull(message = "A delivery date is required")
    @FutureOrPresent(message = "The delivery date must be today or in the future")
    private LocalDate deliveryDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
