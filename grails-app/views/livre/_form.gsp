<%@ page import="bibliothequecroustillant.Livre" %>



<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplairesDispo', 'error')} required">
	<label for="nombreExemplairesDispo">
		<g:message code="livre.nombreExemplairesDispo.label" default="Nombre Exemplaires Dispo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplairesDispo" type="number" min="0" value="${livreInstance.nombreExemplairesDispo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'nombreExemplairesTotal', 'error')} required">
	<label for="nombreExemplairesTotal">
		<g:message code="livre.nombreExemplairesTotal.label" default="Nombre Exemplaires Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombreExemplairesTotal" type="number" min="0" value="${livreInstance.nombreExemplairesTotal}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'typeDocument', 'error')} ">
	<label for="typeDocument">
		<g:message code="livre.typeDocument.label" default="Type Document" />
		
	</label>
	<g:select id="typeDocument" name="typeDocument.id" from="${bibliothequecroustillant.TypeDocument.list()}" optionKey="id" value="${livreInstance?.typeDocument?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'auteurs', 'error')} ">
	<label for="auteurs">
		<g:message code="livre.auteurs.label" default="Auteurs" />
		
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'reservations', 'error')} ">
	<label for="reservations">
		<g:message code="livre.reservations.label" default="Reservations" />
		
	</label>
	<g:select name="reservations" from="${bibliothequecroustillant.Reservation.list()}" multiple="multiple" optionKey="id" size="5" value="${livreInstance?.reservations*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'titre', 'error')} ">
	<label for="titre">
		<g:message code="livre.titre.label" default="Titre" />
		
	</label>
	<g:textField name="titre" value="${livreInstance?.titre}"/>
</div>

