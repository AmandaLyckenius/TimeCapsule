package org.amanda.timecapsule;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TimeCapsuleRequest {

    @NotBlank(message= "Email is required")
    @Email(message = "Incorrect email format")
    private String email;

    @NotBlank(message = "Message is required")
    private String message;
    @NotNull(message = "A delivery date is required")
    @Future(message = "The delivery date must be in the future")
    private LocalDate deliveryDate;

    public @NotBlank(message = "Email is required") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email String email) {
        this.email = email;
    }

    public @NotBlank(message = "Message is required") String getMessage() {
        return message;
    }

    public void setMessage(@NotBlank(message = "Message is required") String message) {
        this.message = message;
    }

    public @NotNull(message = "A delivery date is required") @Future(message = "The delivery date must be in the future") LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(@NotNull(message = "A delivery date is required") @Future(message = "The delivery date must be in the future") LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
