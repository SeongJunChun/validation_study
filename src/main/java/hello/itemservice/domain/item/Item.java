package hello.itemservice.domain.item;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import hello.itemservice.web.validation.form.ItemSaveForm;
import hello.itemservice.web.validation.form.ItemUpadateForm;
import lombok.Data;

@Data
public class  Item {

	//@NotNull(groups=UpdateCheck.class)
	private Long id;

	//@NotBlank(groups={UpdateCheck.class,SaveCheck.class})
	private String itemName;

	//@NotNull(groups={UpdateCheck.class,SaveCheck.class})
	//@Range(min = 1000, max = 1000000,groups={UpdateCheck.class,SaveCheck.class})
	private Integer price;

	//@NotNull(groups={UpdateCheck.class,SaveCheck.class})
	//@Max(value=9999, groups={SaveCheck.class})
	private Integer quantity;

	public Item() {
	}

	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
	public Item(ItemSaveForm form) {
		this.itemName = form.getItemName();
		this.price = form.getPrice();
		this.quantity = form.getQuantity();
	}
	public Item(ItemUpadateForm form) {
		this.id=form.getId();
		this.itemName = form.getItemName();
		this.price = form.getPrice();
		this.quantity = form.getQuantity();
	}
}
