# Plugin de Solicitações Internas — iDempiere 13 (Orion)

[![iDempiere Version](https://img.shields.io/badge/iDempiere-13%20Orion-blue.svg)](https://www.idempiere.org/)
[![Java](https://img.shields.io/badge/Java-17%20Temurin-orange.svg)](https://adoptium.net/)
[![OSGi](https://img.shields.io/badge/OSGi-Declarative%20Services-green.svg)](https://docs.osgi.org/)
[![Database](https://img.shields.io/badge/PostgreSQL-15%2B-blue.svg)](https://www.postgresql.org/)
[![REST API](https://img.shields.io/badge/REST%20API-JAX--RS%20%2F%20CXF-informational.svg)](https://bxservice.github.io/idempiere-rest-docs/)

Plugin OSGi corporativo para o **iDempiere 13 (Orion)** que implementa um módulo completo de helpdesk interno para controle de solicitações de colaboradores (TI, RH, Manutenção, etc.), com responsável, prioridades, prazos automáticos, validações de integridade, relatórios gerenciais e **exposição de endpoints REST customizados**.

---

## 📌 Informações do Projeto & Ambiente

* **Plugin Symbolic Name (Bundle ID)**: `org.marcus.internalrequest`
* **Versão do iDempiere**: 13 (Orion)
* **JDK**: Java 17 (OpenJDK Temurin)
* **Banco de Dados**: PostgreSQL
* **IDE**: Eclipse IDE for Enterprise Java and Web Developers (PDE)
* **Tabela Principal**: `XX_InternalRequest`
* **Janela WebUI**: `Internal Request` (Menu Principal)
* **Acesso WebUI**: `http://localhost:8080/webui`
  * Usuário de Teste: `GardenAdmin` / `GardenAdmin` (Client: `GardenWorld`)
  * Usuário Administrador: `SuperUser` / `System` (System Administrator)

---

## 🏛️ Arquitetura do Plugin

O plugin foi desenhado seguindo os princípios de **Clean Architecture**, **SOLID** e as melhores práticas modernas do iDempiere 13:

```
c:\Users\vinic\idempiere-plugins\internalrequest-plugin\
├── META-INF/
│   └── MANIFEST.MF                    # Metadados OSGi, dependências e exportações
├── OSGI-INF/                          # Componentes Declarative Services (SCR)
│   ├── model-factory.xml              # Registro do IModelFactory
│   ├── callout-factory.xml            # Registro do IColumnCalloutFactory
│   ├── validator-factory.xml          # Registro do IModelValidatorFactory
│   ├── process-factory.xml            # Registro do IProcessFactory
│   ├── event-manager.xml              # Registro do AnnotationBasedEventManager
│   └── rest-resource.xml              # Registro do ResourceExtension (REST)
├── reports/
│   └── InternalRequestReport.jrxml    # Template corporativo JasperReports (o .jasper é gerado localmente)
└── src/org/marcus/internalrequest/
    ├── callout/                       # Callouts de cálculo de prazo (Fase 3)
    ├── event/                         # Event Handlers assíncronos (Fase 7)
    ├── factory/                       # Model Factory OSGi (Fase 2)
    ├── model/                         # Active Record (MXXInternalRequest) (Fase 2)
    ├── process/                       # Processo Java de Resolução (Fase 5)
    ├── rest/                          # Endpoints e Recursos JAX-RS (Fase 8)
    └── validator/                     # Model Validator de integridade (Fase 4)
```

---

## 🚀 Módulos Implementados (Por Fases)

### 🔹 Fase 1: Application Dictionary & Modelagem de Dados
* Criação das List References (`XX_RequestType`, `XX_RequestPriority`, `XX_RequestStatus`).
* Criação da tabela física `XX_InternalRequest` no PostgreSQL via Application Dictionary.
* Configuração da Janela, Abas e Campos na WebUI e atribuição de permissão para a role `GardenAdmin`.

### 🔹 Fase 2: Estrutura OSGi & Modelo de Domínio
* Implementação da classe base `X_XX_InternalRequest` e da classe de domínio `MXXInternalRequest`.
* Registro do componente OSGi `InternalRequestModelFactory` via Declarative Services (`OSGI-INF/model-factory.xml`).

### 🔹 Fase 3: Callout de Cálculo de Prazo por Prioridade
* Preenchimento imediato da data necessária (`DateNeeded`) ao alterar a prioridade na tela, sem sobrescrever prazos manuais:
  * **Urgente (`U`)**: `DateRequested + 1 dia`
  * **Alta (`H`)**: `DateRequested + 3 dias`
  * **Média (`M`)**: `DateRequested + 7 dias`
  * **Baixa (`L`)**: `DateRequested + 15 dias`

### 🔹 Fase 4: Model Validator (Regras de Integridade)
* Interceptação nos eventos `TYPE_BEFORE_NEW` e `TYPE_BEFORE_CHANGE`:
  * **Regra 1**: Bloqueia salvar solicitação como `Status = Resolvida` sem informar `DateResolved`.
  * **Regra 2**: Bloqueia colocar como `Em Andamento` ou `Resolvida` sem um responsável atribuído (`AssignedTo_ID`).

### 🔹 Fase 5: Process Java (`SvrProcess`) — Resolver Solicitação
* Ação corporativa via botão na tela e no menu do iDempiere:
  * Atribuição automática ao usuário logado caso `AssignedTo_ID` esteja vazio.
  * Atualização da data de resolução para a data/hora atual.
  * Marcação atômica de `Status = "RS"` e `Processed = true` com transação `saveEx(get_TrxName())`.

### 🔹 Fase 6: Relatórios Gerenciais (Nativo e JasperReports)
* **Relatório Nativo**: Print Format e Report View com filtros de data, tipo, prioridade e status.
* **Relatório Jasper**: Template profissional `InternalRequestReport.jrxml` com agrupamento por prioridade, contadores de chamados e layout corporativo. *(O binário `.jasper` é gerado localmente na compilação do relatório)*.

### 🔹 Fase 7: Event Handler OSGi Moderno (Monitoramento de Urgência)
* Arquitetura reativa baseada nas anotações modernas do iDempiere 13 (`@EventTopicDelegate` + `@ModelEventTopic`).
* Escuta desacoplada dos eventos `@AfterNew` e `@AfterChange` com emissão de alerta no console (`[URGENT REQUEST ALERT]`) para solicitações urgentes.

### 🔹 Fase 8: API REST Customizada (JAX-RS & ResourceExtension)
* Extensão oficial do ecossistema `idempiere-rest` da BX Service (`com.trekglobal.idempiere.rest.api`).
* **Endpoints Criados**:
  * `POST /api/v1/internal-requests`: Criação ágil (*Quick Create*) com cálculo de prazo e disparo do Event Handler.
  * `GET /api/v1/internal-requests/{id}`: Consulta formatada em JSON limpo e desacoplado.
  * `POST /api/v1/internal-requests/{id}/resolve`: Resolução remota do chamado via API.
* > ⚠️ **Nota de Dependência REST**: O plugin funciona de forma autônoma para todas as operações da WebUI (Callouts, Validadores, Processos, Relatórios e Eventos). Para habilitar os endpoints REST acima, é necessário que o plugin base [idempiere-rest](https://github.com/bxservice/idempiere-rest) (`com.trekglobal.idempiere.rest.api`) esteja instalado e ativo no servidor.

---

## 🧪 Como Executar os Testes

1. Faça login como `GardenAdmin` / `GardenAdmin` em `http://localhost:8080/webui`.
2. Abra a janela **Internal Request** no menu principal.
3. **Teste do Callout**: Crie um novo chamado, selecione Prioridade = `Urgente` e veja a `DateNeeded` ser preenchida automaticamente para amanhã.
4. **Teste do Validador**: Tente mudar o status para `Resolvida` sem preencher a data de resolução ou o responsável; o sistema exibirá uma mensagem de erro na tela e bloqueará a gravação.
5. **Teste do Botão Resolver**: Abra um chamado aberto e clique no botão na barra de ferramentas; o status mudará para `Resolvida` e o registro será travado para edição.
6. **Teste do Relatório Jasper**: Abra a janela de processos, execute o relatório com os filtros desejados e visualize o PDF gerado.

---

## 🛠️ Como Importar e Rodar no Eclipse PDE

1. Clone este repositório para o seu computador:
   ```bash
   git clone https://github.com/mviniciusmonteiro/internal-request-idem.git
   ```
2. No Eclipse IDE:
   * **File → Import... → General → Existing Projects into Workspace**.
   * Selecione a pasta do repositório clonado e clique em **Finish**.
3. Certifique-se de que a Target Platform do **iDempiere 13** está ativa no Eclipse.
4. Na Run Configuration (`server.product`), certifique-se de adicionar o bundle `org.marcus.internalrequest` com:
   * **Auto-Start**: `true`
   * **Start Level**: `2` *(Recomendado para que o serviço do Model Validator esteja ativo antes da inicialização do ModelValidationEngine e do Jetty no nível 4)*
   *(Opcional: Caso deseje utilizar a API REST da Fase 8, inclua também os bundles do `idempiere-rest` na Target Platform / Run Configuration).*
5. Inicie o servidor. No console Gogo, confirme que o bundle está ativo:
   ```text
   ss internalrequest
   ```
   *(O status exibido deve ser `ACTIVE`)*.

---
