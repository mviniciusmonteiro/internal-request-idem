package org.marcus.internalrequest.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.marcus.internalrequest.model.I_XX_InternalRequest;
import org.marcus.internalrequest.model.MXXInternalRequest;
/**
 * Fábrica de Modelos para o Plugin de Solicitações Internas.
 * Implementa o padrão Factory Method (IModelFactory) do iDempiere.
 */

public class InternalRequestModelFactory implements IModelFactory {
	
	/**
	 * Informa ao iDempiere qual classe Java representa a tabela
	 */
	@Override
	public Class<?> getClass(String tableName) {
		if (I_XX_InternalRequest.Table_Name.equals(tableName)) {
			return MXXInternalRequest.class;
		}
		return null;
	}
	
	/**
	 * Instancia o PO a partir do ID da tabela (ou ID = 0 para novo registro)
	 */
	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		if (I_XX_InternalRequest.Table_Name.equals(tableName)) {
			return new MXXInternalRequest(Env.getCtx(), Record_ID, trxName);
		}
		return null;
	}

	/**
	 * Instancia o PO a partir de um ResultSet (consultas em lote de alta performance)
	 */
	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		if (I_XX_InternalRequest.Table_Name.equals(tableName)) {
			return new MXXInternalRequest(Env.getCtx(), rs, trxName);
		}
		return null;
	}

}
