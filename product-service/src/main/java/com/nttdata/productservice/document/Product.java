package com.nttdata.productservice.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @NotEmpty
    private String category;

    @NotEmpty
    private String productTypeId;
}
