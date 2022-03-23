package gr.codelearn.spring.showcase.app.domain;

import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//@formatter:off
@NamedNativeQuery(name = "Customer.purchasedMostExpensiveProduct",
		query =
				"SELECT C.FIRSTNAME || ' ' || C.LASTNAME as fullname, COUNT(*) as purchases " +
						"FROM ORDERS O, ORDER_ITEMS OI, CUSTOMERS C " +
						"WHERE OI.ORDER_ID = O.ID " +
						"AND O.CUSTOMER_ID = C.ID " +
						"AND OI.PRODUCT_ID = (SELECT TOP 1 ID FROM PRODUCTS ORDER BY PRICE DESC) " +
						"GROUP BY O.CUSTOMER_ID " +
						"ORDER BY purchases, c.lastname, c.firstname",
		resultSetMapping = "CustomersPurchasedMostExpensiveProduct")
@SqlResultSetMapping(name = "CustomersPurchasedMostExpensiveProduct",
		classes = @ConstructorResult(
				targetClass = KeyValue.class,
				columns = {
						@ColumnResult(name = "fullname", type = String.class),
						@ColumnResult(name = "purchases", type = Long.class)
				}
		)
)
//@formatter:on

@Data
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CUSTOMERS", indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "CUSTOMERS_SEQ", initialValue = 1, allocationSize = 1)
public class Customer extends BaseModel {
	@NotNull
	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@NotNull
	@Column(length = 20, nullable = false)
	private String firstname;

	@NotNull
	@Column(length = 50, nullable = false)
	private String lastname;

	@NotNull
	@Column(length = 50, nullable = false)
	private String address;

	@Min(value = 18)
	@Max(value = 120)
	private Integer age;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private CustomerCategory customerCategory;
}
