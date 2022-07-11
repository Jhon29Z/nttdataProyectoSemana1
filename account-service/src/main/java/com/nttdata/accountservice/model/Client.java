package com.nttdata.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private String name;
    private String surname;
    private String businessName;
    private String phone;
    private String address;
    private String email;
    private String documentTypeId;
    private String numberDocumentType;
    private String dateOfBirth;
}
