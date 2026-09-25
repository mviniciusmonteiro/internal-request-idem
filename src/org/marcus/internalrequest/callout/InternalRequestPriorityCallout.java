package org.marcus.internalrequest.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.TimeUtil;
import org.marcus.internalrequest.model.I_XX_InternalRequest;
import org.marcus.internalrequest.model.X_XX_InternalRequest;

/**
 * Callout executado quando o usuário altera a Prioridade na tela.
 * Calcula e preenche automaticamente o campo DateNeeded (Prazo).
 */
public class InternalRequestPriorityCallout implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		// 1. Se o valor for nulo (usuário limpou a prioridade), não faz nada
		if (value == null || value.toString().trim().isEmpty()) {
			return null;
		}
		
		  // 2. Data base: DateRequested (se nula, assume o momento atual)
        Timestamp dateRequested = (Timestamp) mTab.getValue(I_XX_InternalRequest.COLUMNNAME_DateRequested);
        if (dateRequested == null) {
            dateRequested = new Timestamp(System.currentTimeMillis());
        }
			
	    // 3. Obtém a Data da Solicitação (DateRequested). Se estiver vazia, assume a data atual
		Timestamp dateRequested = (Timestamp) mTab.getValue(I_XX_InternalRequest.COLUMNNAME_DateRequested);
			if (dateRequested == null) {
				dateRequested = new Timestamp(System.currentTimeMillis());
			}
		
		// 4. Determina quantos dias somar com base na prioridade selecionada
		String priority = value.toString();
		int daysToAdd = 0;
		
		if (X_XX_InternalRequest.PRIORITY_Urgente.equals(priority)) {      // 'U'
            daysToAdd = 1;
        } else if (X_XX_InternalRequest.PRIORITY_Alta.equals(priority)) {  // 'H'
            daysToAdd = 3;
        } else if ("M".equals(priority)) {                                 // 'M' (Média)
            daysToAdd = 7;
        } else if (X_XX_InternalRequest.PRIORITY_Baixa.equals(priority)) { // 'L'
            daysToAdd = 15;
        } else {
            return null; // Prioridade desconhecida
        }
		
		// 5. Calcula a nova data truncada (00:00:00) usando o utilitário nativo TimeUtil
		Timestamp calculatedDate = TimeUtil.addDays(dateRequested, daysToAdd);
			
		// 6. Atualiza o campo DateNeeded na tela do usuário
        mTab.setValue(I_XX_InternalRequest.COLUMNNAME_DateNeeded, calculatedDate);
			
		
		return null;  // Sucesso
		
		
	}

}
