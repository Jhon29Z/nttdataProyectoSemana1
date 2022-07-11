package com.nttdata.accountservice.document;

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
@Document(collection = "authorizedSigners")
public class AuthorizedSigner {

    @Id
    private String id;
    private String accountId;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String email;
    private String dateOfBirth;
}
