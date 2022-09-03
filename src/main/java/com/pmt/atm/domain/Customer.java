package com.pmt.atm.domain;

import com.pmt.atm.domain.exceptions.PasswordIsIncorrectException;
import com.pmt.atm.domain.exceptions.ValidationException;
import com.pmt.atm.infra.persistence.attributeConverter.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Entity
public class Customer {

    @Id
    private final String id;

    @Column(name = "username")
    @Convert(converter = UsernameAttributeConverter.class)
    private final Username username;

    @Column(name = "first_name")
    @Convert(converter = FirstNameAttributeConverter.class)
    private FirstName firstName;

    @Column(name = "last_name")
    @Convert(converter = LastNameAttributeConverter.class)
    private LastName lastName;

    @Column(name = "password")
    @Convert(converter = PasswordAttributeConverter.class)
    private Password password;

    @Column(name = "credit_in_tomans")
    @Convert(converter = TomanAttributeConverter.class)
    private Toman credit;

    @Column(name = "created_at")
    private final LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @OneToMany
    private final Set<Account> accounts = new HashSet<>();

    public Customer(
            Username username,
            FirstName firstName,
            LastName lastName,
            Password password
    ) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
        this.credit = Toman.createZero();
    }

    public Customer() {
        this.id = UUID.randomUUID().toString();
        this.username = null;
        this.password = null;
        this.createdAt = LocalDateTime.now();
    }

    public void changeInfo(Optional<FirstName> firstNameOrEmpty, Optional<LastName> lastNameOrEmpty) {
        firstNameOrEmpty.ifPresent(newValue -> {
            if(newValue.equals(firstName)) return;
            firstName = newValue;
            modified();
        });
        lastNameOrEmpty.ifPresent(newValue -> {
            if(newValue.equals(lastName)) return;
            lastName = newValue;
            modified();
        });
    }

    public void changePassword(Password newPassword, Password currentPassword) {
        if(newPassword == null || currentPassword == null)
            throw new ValidationException("New password is required.");
        if(!password.equals(currentPassword)) throw new PasswordIsIncorrectException();
        if(newPassword.equals(currentPassword)) return;
        password = newPassword;
        modified();
    }

    public void openNewAccount(Account account) {
        accounts.add(account);
        modified();
    }

    public void calculateCredit() {

    }

    public void modified() {
        this.lastModifiedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public Password getPassword() {
        return password;
    }

    public Toman getCredit() {
        return credit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

}
