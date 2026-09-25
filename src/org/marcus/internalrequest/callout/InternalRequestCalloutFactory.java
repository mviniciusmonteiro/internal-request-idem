package org.marcus.internalrequest.callout;

import java.util.ArrayList;
import java.util.List;
import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.marcus.internalrequest.model.*;

public class InternalRequestCalloutFactory implements IColumnCalloutFactory {

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		
		List<IColumnCallout> list = new ArrayList<>();
		
		if (I_XX_InternalRequest.Table_Name.equalsIgnoreCase(tableName)) {
			if (I_XX_InternalRequest.COLUMNNAME_Priority.equalsIgnoreCase(columnName)) {
				list.add(new InternalRequestPriorityCallout());
			}
		}
		
		return list.isEmpty() ? null : list.toArray(new IColumnCallout[0]);
	}

}
