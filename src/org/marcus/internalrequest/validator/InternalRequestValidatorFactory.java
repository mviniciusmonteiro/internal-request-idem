package org.marcus.internalrequest.validator;


import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;

public class InternalRequestValidatorFactory implements IModelValidatorFactory {

	@Override
	public ModelValidator newModelValidatorInstance(String className) {
		if (InternalRequestModelValidator.class.getName().equals(className)) {
            return new InternalRequestModelValidator();
        }
        return null;
	}

}
