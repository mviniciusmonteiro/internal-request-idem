# Plugin de Solicitações Internas — iDempiere 13

Plugin OSGi para o **iDempiere 13 (Orion)** que implementa um módulo de helpdesk interno para gestão de solicitações de colaboradores (TI, RH, Manutenção e Geral), com prazos automáticos, validações de integridade e fluxo de resolução.

---

## 📌 Informações do Projeto

- **Bundle ID**: `org.marcus.internalrequest`
- **Versão do iDempiere**: 13 (Orion)
- **Java**: OpenJDK 17
- **Banco de Dados**: PostgreSQL
- **Tabela Principal**: `XX_InternalRequest`
- **Janela WebUI**: `Internal Request`
- **Acesso de Teste**: `http://localhost:8080/webui` (Usuário: `GardenAdmin` / `GardenAdmin`)

---

## 🚀 Status das Fases

| Fase | Descrição | Status |
| :--- | :--- | :---: |
| **Fase 1** | Application Dictionary (Tabela, Colunas, Janela e Menu) | ✅ Concluída |
| **Fase 2** | Plugin OSGi & Modelo Java (`MXXInternalRequest`, ModelFactory) | ✅ Concluída |
| **Fase 3** | Callout de Prazo Automático por Prioridade | ⏳ Em Andamento |
| **Fase 4** | Model Validator (Regras de Integridade e Validações) | ⏳ Pendente |
| **Fase 5** | Process Java (`SvrProcess` — Resolver Solicitação) | ⏳ Pendente |
| **Fase 6** | Relatórios (Nativo do Dicionário + JasperReports) | ⏳ Pendente |
| **Fase 7** | Diferenciais (Event Handler OSGi & Exportação 2Pack) | ⏳ Pendente |

---

## 🛠️ Como Executar Localmente no Eclipse

1. Importe o projeto no Eclipse com o target platform do iDempiere configurado.
2. Abra as configurações de execução: **Run → Run Configurations...** → **`server.product`**.
3. Na aba **Plug-ins**, localize e marque `org.marcus.internalrequest`.
4. Configure na mesma linha:
   - **Auto-Start**: `true`
   - **Start Level**: `5`
5. Inicie o servidor e valide no Console digitando:
   ```bash
   ss org.marcus.internalrequest
   ```
   *(O bundle deve exibir o estado `ACTIVE`)*.
