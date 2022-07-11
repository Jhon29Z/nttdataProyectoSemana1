package com.nttdata.transactionservice.document;

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
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    private String accountId;
    private String amount;
    private String transactionTypeId;
    private String creationDate;

}
