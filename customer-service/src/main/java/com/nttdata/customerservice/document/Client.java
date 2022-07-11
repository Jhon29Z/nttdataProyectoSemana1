package com.nttdata.customerservice.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
public class Client {

    @Id
    private String id;
    private String customerTypeId;
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

