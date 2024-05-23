package hello.itemservice.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import hello.itemservice.domain.item.Item;

@Component
public class ItemValidator implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return Item.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Item item = (Item) o;
		if(!StringUtils.hasText(item.getItemName())){
			errors.rejectValue("itemName","required");
		}
		if(item.getPrice()==null||item.getPrice()<1000||item.getPrice()>1000000){
			//bindingResult.addError(new FieldError("item","price",item.getPrice(),false,new String[]{"range.item.price"},new Object[]{1000,1000000},null));
			errors.rejectValue("price","range",new Object[]{1000,1000000},null);
		}
		if(item.getQuantity()==null||item.getQuantity()>10000){
			//bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999},null));
			errors.rejectValue("quantity","max",new Object[]{9999},null);
		}
		//특정 필드가 아닌 복합 룰 검증
		if(item.getPrice()!=null&&item.getQuantity()!=null){
			int resultPrice = item.getPrice()*item.getQuantity();
			if(resultPrice<10000){
				//bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},null));
				errors.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
			}
		}


	}
}
