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
	 
	 
	 

}
