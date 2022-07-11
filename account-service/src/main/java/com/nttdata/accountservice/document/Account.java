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
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;
    private String accountNumber;
    private String clientId;
    private String productId;
    private String currencyId;
    private Double balance;
    private String creationDate;
}
