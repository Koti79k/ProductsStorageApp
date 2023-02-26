package Products.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(indexName = "products")  //@Document – To specify the index(database).
public class Product {

	@Id
	private String id;
	
	@Field(type =FieldType.Text, name = "name" ) //Field -> Columns
	private String name;
	
    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Double, name = "price")
    private double price;
}
