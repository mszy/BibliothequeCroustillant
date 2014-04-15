<%@ page import="bibliothequecroustillant.Livre" %>



<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'qteDispo', 'error')} required">
	<label for="qteDispo">
		<g:message code="livre.qteDispo.label" default="Qte Dispo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="qteDispo" type="number" min="0" value="${livreInstance.qteDispo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'qteTotale', 'error')} required">
	<label for="qteTotale">
		<g:message code="livre.qteTotale.label" default="Qte Totale" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="qteTotale" type="number" min="0" value="${livreInstance.qteTotale}" required=""/>
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

<div class="fieldcontain ${hasErrors(bean: livreInstance, field: 'typeDocument', 'error')} required">
	<label for="typeDocument">
		<g:message code="livre.typeDocument.label" default="Type Document" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="typeDocument" name="typeDocument.id" from="${bibliothequecroustillant.TypeDocument.list()}" optionKey="id" required="" value="${livreInstance?.typeDocument?.id}" class="many-to-one"/>
</div>

