package org.marcus.internalrequest.validator;

import java.sql.Timestamp;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.marcus.internalrequest.model.*;
import org.compiere.util.CLogger;

public class InternalRequestModelValidator implements ModelValidator {
	

    private static final CLogger log = CLogger.getCLogger(InternalRequestModelValidator.class);
    private int m_AD_Client_ID = -1;

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		if (client != null) {
			m_AD_Client_ID = client.getAD_Client_ID()
;		}
		
		// Avisa o iDempiere: "Monitore a tabela XX_InternalRequest com este validador"
		engine.addModelChange(I_XX_InternalRequest.Table_Name, this);
		
		log.info("InternalRequestModelValidator inicializado para Tenant ID: " + m_AD_Client_ID);
	}

	@Override
	public int getAD_Client_ID() {
		return m_AD_Client_ID;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		return null; // Login permitido sem restrições	
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		// 1. Ignora se não for a nossa tabela
		if (!I_XX_InternalRequest.Table_Name.equals(po.get_TableName())) {
			return null;
		}
		
		if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
			
			X_XX_InternalRequest req = (X_XX_InternalRequest) po;
			
			String status = req.getStatus();              // Linha 434 do X_XX_InternalRequest
	        Timestamp dateResolved = req.getDateResolved(); // Linha 234 do X_XX_InternalRequest
	        int assignedToId = req.getAssignedTo_ID();    // Linha 186 do X_XX_InternalRequest
	        
	        // Regra 1: Se status = Resolvida, DateResolved é obrigatória
	        if(X_XX_InternalRequest.STATUS_Resolvida.equals(status)) {
	        	if (dateResolved == null) {
	        		return "Não é possível salvar como 'Resolvida' sem preencher a Data de Resolução.";
	        	}
	        }
	        
	        // Regra 2: Se status = Em Andamento ou Resolvida, AssignedTo_ID é obrigatório
	        if (X_XX_InternalRequest.STATUS_EmAndamento.equals(status) 
                    || X_XX_InternalRequest.STATUS_Resolvida.equals(status)) {
                if (assignedToId <= 0) {
                    return "Para colocar em 'Em Andamento' ou 'Resolvida', é obrigatório selecionar um Responsável.";
                }
            }
		}
		
		 return null; // Retorna null para autorizar a gravação
	}

	@Override
	public String docValidate(PO po, int timing) {
		return null;
	}

}
