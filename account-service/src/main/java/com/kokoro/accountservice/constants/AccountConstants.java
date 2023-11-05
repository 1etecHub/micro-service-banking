package com.kokoro.accountservice.constants;

import lombok.Data;

@Data
public class AccountConstants {

    public static final String SAVINGS = "Savings";
    public static final String ADDRESS = "majek estate";
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "rEQUEST PROCESSED SUCCESSFULLY";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occured please try again";
    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Update operation failed, please try again or contact dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed, please try again or contact dev team";

}
