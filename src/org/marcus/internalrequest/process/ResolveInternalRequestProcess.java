package org.marcus.internalrequest.process;


import java.sql.Timestamp;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.marcus.internalrequest.model.MXXInternalRequest;
import org.marcus.internalrequest.model.X_XX_InternalRequest;


public class ResolveInternalRequestProcess extends SvrProcess{
	
    private Timestamp p_DateResolved = null;
    
    @Override
    protected void prepare() {
        // Itera sobre os parâmetros recebidos da tela
        ProcessInfoParameter[] para = getParameter();
        for (ProcessInfoParameter p : para) {
            String name = p.getParameterName();
            if (p.getParameter() == null) {
                continue;
            } else if ("DateResolved".equals(name)) {
                p_DateResolved = p.getParameterAsTimestamp();
            } else {
                log.warning("Parâmetro desconhecido: " + name);
            }
        }
        
        // Se a data de resolução não foi informada no diálogo, assume a data/hora atual
        if (p_DateResolved == null) {
            p_DateResolved = new Timestamp(System.currentTimeMillis());
        }
    }
    
    
    @Override
    protected String doIt() throws Exception {
        // 1. Obtém o ID do registro que está aberto na janela
        int recordId = getRecord_ID();
        if (recordId <= 0) {
            throw new AdempiereException("Nenhum chamado selecionado para resolução.");
        }
        // 2. Carrega o modelo de dados na transação corrente
        MXXInternalRequest req = new MXXInternalRequest(getCtx(), recordId, get_TrxName());
        // 3. Validação prévia de integridade
        if (X_XX_InternalRequest.STATUS_Resolvida.equals(req.getStatus())) {
            throw new AdempiereException("Esta solicitação já está com status 'Resolvida'.");
        }
        // 4. Se não havia responsável selecionado, atribui ao usuário que está resolvendo
        if (req.getAssignedTo_ID() <= 0) {
            req.setAssignedTo_ID(getAD_User_ID());
        }
        // 5. Atualiza os dados de resolução e finalização
        req.setDateResolved(p_DateResolved);
        req.setStatus(X_XX_InternalRequest.STATUS_Resolvida);
        req.setProcessed(true);
        // 6. Salva o registro acionando a transação e os validadores (Fase 4)
        req.saveEx(get_TrxName());
        return "Solicitação " + req.getDocumentNo() + " resolvida com sucesso!";
    }
    
    
}
