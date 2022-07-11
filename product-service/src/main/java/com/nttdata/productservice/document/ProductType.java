package com.nttdata.productservice.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "productTypes")
public class ProductType {

    @Id
    private String id;

    @NotEmpty
    private String description;
}
