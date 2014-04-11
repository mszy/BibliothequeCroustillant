<%@ page import="bibliothequecroutillant.Livre" %>



<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'titre', 'error')} required">
	<label for="titre">
		<g:message code="livre.titre.label" default="Titre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="titre" required="" value="${livreInstance?.titre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'auteur', 'error')} required">
	<label for="auteur">
		<g:message code="livre.auteur.label" default="Auteur" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="auteur" required="" value="${livreInstance?.auteur}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="livre.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" required="" value="${livreInstance?.type}"/>
</div>

