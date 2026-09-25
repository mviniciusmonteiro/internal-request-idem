package org.marcus.internalrequest.process;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

public class InternalRequestProcessFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		if (ResolveInternalRequestProcess.class.getName().equals(className)) {
            return new ResolveInternalRequestProcess();
        }
		return null;
	}

}
