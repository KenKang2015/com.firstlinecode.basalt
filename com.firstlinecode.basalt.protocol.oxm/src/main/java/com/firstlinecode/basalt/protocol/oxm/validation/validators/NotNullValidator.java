package com.firstlinecode.basalt.protocol.oxm.validation.validators;

import com.firstlinecode.basalt.protocol.oxm.validation.IValidator;
import com.firstlinecode.basalt.protocol.oxm.validation.ValidationException;

public class NotNullValidator<T> implements IValidator<T>  {

	@Override
	public void validate(Object object) throws ValidationException {
		if (object == null || "".equals(object)) {
			throw new ValidationException("Value mustn't be null.");
		}
	}

}
