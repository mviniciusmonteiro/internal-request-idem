package org.marcus.internalrequest.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Classe de Negócio para Solicitação Interna (Internal Request)
 */
public class MXXInternalRequest extends X_XX_InternalRequest {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padrão para carregar registro por ID ou criar novo (ID = 0)
	 */
	public MXXInternalRequest(Properties ctx, int XX_InternalRequest_ID, String trxName) {
		super(ctx, XX_InternalRequest_ID, trxName);
	}

	/**
	 * Construtor para carregar registro a partir de um ResultSet SQL
	 */
	public MXXInternalRequest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public static final String PRIORITY_MEDIA = "M";

	public static int getDaysForPriority(String priority) {
		if (PRIORITY_Urgente.equals(priority))
			return 1;
		if (PRIORITY_Alta.equals(priority))
			return 3;
		if (PRIORITY_MEDIA.equals(priority))
			return 7;
		if (PRIORITY_Baixa.equals(priority))
			return 15;
		return 7; // default
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (getEntityType() == null || getEntityType().trim().isEmpty()) {
			setEntityType("U");
		}
		if (getName() == null || getName().trim().isEmpty()) {
			setName(getDescription() != null && !getDescription().trim().isEmpty() ? getDescription()
					: "Solicitação Interna");
		}
		if (getValue() == null || getValue().trim().isEmpty()) {
			setValue(getDocumentNo() != null && !getDocumentNo().trim().isEmpty() ? getDocumentNo() : getName());
		}
		if (getRequestedBy_ID() <= 0) {
			int userId = org.compiere.util.Env.getAD_User_ID(getCtx());
			setRequestedBy_ID(userId > 0 ? userId : 101);
		}
		return super.beforeSave(newRecord);
	}

}
