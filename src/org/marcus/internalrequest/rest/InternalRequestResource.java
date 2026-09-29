package org.marcus.internalrequest.rest;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.marcus.internalrequest.model.MXXInternalRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Recurso REST JAX-RS customizado para Solicitações Internas
 * (XX_InternalRequest).
 * Caminho base: /api/v1/internal-requests
 * 
 */
@Path("v1/internal-requests")
public class InternalRequestResource {

	private static final CLogger log = CLogger.getCLogger(InternalRequestResource.class);
	private final ObjectMapper mapper = new ObjectMapper();

	public InternalRequestResource() {
	}

	/**
	 * Recupera os detalhes de uma solicitação por ID.
	 * GET /api/v1/internal-requests/{id}
	 */
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequest(@PathParam("id") int id) {
		try {
			MXXInternalRequest request = new MXXInternalRequest(Env.getCtx(), id, null);
			if (request.get_ID() <= 0) {
				ObjectNode err = mapper.createObjectNode();
				err.put("error", "Solicitação interna não encontrada para o ID: " + id);
				return Response.status(Status.NOT_FOUND).entity(err.toString()).build();
			}

			ObjectNode node = mapper.createObjectNode();
			node.put("id", request.getXX_InternalRequest_ID());
			node.put("documentNo", request.getDocumentNo());
			node.put("type", request.getRequestType());
			node.put("priority", request.getPriority());
			node.put("status", request.getStatus());
			node.put("description", request.getDescription());
			node.put("dateRequested",
					request.getDateRequested() != null ? request.getDateRequested().toString() : null);
			node.put("dateNeeded", request.getDateNeeded() != null ? request.getDateNeeded().toString() : null);
			node.put("dateResolved", request.getDateResolved() != null ? request.getDateResolved().toString() : null);
			node.put("assignedToId", request.getAssignedTo_ID());
			node.put("processed", request.isProcessed());

			return Response.ok(node.toString()).build();
		} catch (Exception e) {
			log.severe("Erro ao consultar solicitação REST: " + e.getMessage());
			ObjectNode err = mapper.createObjectNode();
			err.put("error", e.getMessage());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(err.toString()).build();
		}
	}

	/**
	 * Criação rápida de solicitação interna com cálculo automático de prazo.
	 * POST /api/v1/internal-requests
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createQuickRequest(String jsonBody) {
		Trx trx = Trx.get(Trx.createTrxName("REST_CreateReq"), true);
		try {
			JsonNode root = mapper.readTree(jsonBody);

			MXXInternalRequest req = new MXXInternalRequest(Env.getCtx(), 0, trx.getTrxName());
			String summary = root.has("summary") ? root.get("summary").asText() : "";
			req.setEntityType("U");
			req.setName(summary.isEmpty() ? "Solicitação Interna" : summary);
			req.setValue(summary.isEmpty() ? "REQ" : summary);
			req.setRequestType(root.has("type") ? root.get("type").asText() : "OT");
			req.setPriority(root.has("priority") ? root.get("priority").asText() : "M");
			req.setDescription(root.has("description") ? root.get("description").asText() : summary);
			req.setDateRequested(new Timestamp(System.currentTimeMillis()));
			req.setStatus("OP"); // Aberta
			req.setRequestedBy_ID(Env.getAD_User_ID(Env.getCtx()) > 0 ? Env.getAD_User_ID(Env.getCtx()) : 101);

			// Cálculo da data necessária por prioridade (Mesma regra de negócio do Callout)
			int days = 7;
			String priority = req.getPriority();
			days = MXXInternalRequest.getDaysForPriority(priority);
			req.setDateNeeded(Timestamp.valueOf(LocalDate.now().plusDays(days).atStartOfDay()));

			// saveEx executa transação, aciona ModelValidator (Fase 4) e EventDelegate
			// (Fase 7)
			req.saveEx();
			trx.commit();

			ObjectNode res = mapper.createObjectNode();
			res.put("id", req.getXX_InternalRequest_ID());
			res.put("documentNo", req.getDocumentNo());
			res.put("type", req.getRequestType());
			res.put("priority", req.getPriority());
			res.put("status", req.getStatus());
			res.put("dateNeeded", req.getDateNeeded().toString());
			res.put("message", "Solicitação interna criada com sucesso via REST API.");

			return Response.status(Status.CREATED).entity(res.toString()).build();
		} catch (Exception e) {
			trx.rollback();
			log.warning("Falha na criação REST da solicitação: " + e.getMessage());
			ObjectNode err = mapper.createObjectNode();
			err.put("error", e.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(err.toString()).build();
		} finally {
			trx.close();
		}
	}

	/**
	 * Resolução de solicitação por processo via API.
	 * POST /api/v1/internal-requests/{id}/resolve
	 */
	@Path("{id}/resolve")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response resolveRequest(@PathParam("id") int id) {
		Trx trx = Trx.get(Trx.createTrxName("REST_ResolveReq"), true);
		try {
			MXXInternalRequest req = new MXXInternalRequest(Env.getCtx(), id, trx.getTrxName());
			if (req.get_ID() <= 0) {
				ObjectNode err = mapper.createObjectNode();
				err.put("error", "Registro não encontrado para o ID: " + id);
				return Response.status(Status.NOT_FOUND).entity(err.toString()).build();
			}

			// Se não houver responsável, atribui ao usuário atual autenticado
			if (req.getAssignedTo_ID() <= 0) {
				req.setAssignedTo_ID(Env.getAD_User_ID(Env.getCtx()));
			}
			req.setDateResolved(new Timestamp(System.currentTimeMillis()));
			req.setStatus("RS"); // Resolvida
			req.setProcessed(true);

			// saveEx aciona o ModelValidator
			req.saveEx();
			trx.commit();

			ObjectNode res = mapper.createObjectNode();
			res.put("id", req.getXX_InternalRequest_ID());
			res.put("documentNo", req.getDocumentNo());
			res.put("status", req.getStatus());
			res.put("dateResolved", req.getDateResolved().toString());
			res.put("processed", true);
			res.put("message", "Solicitação resolvida com sucesso via REST API.");

			return Response.ok(res.toString()).build();
		} catch (Exception e) {
			trx.rollback();
			log.warning("Falha ao resolver solicitação via REST: " + e.getMessage());
			ObjectNode err = mapper.createObjectNode();
			err.put("error", e.getMessage());
			return Response.status(Status.BAD_REQUEST).entity(err.toString()).build();
		} finally {
			trx.close();
		}
	}
}
